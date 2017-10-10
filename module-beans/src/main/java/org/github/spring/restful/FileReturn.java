package org.github.spring.restful;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.spring.bootstrap.ServletResourceLoader;
import org.github.spring.enumeration.ContentType;
import org.github.spring.footstone.ExcelGenerator;
import org.github.spring.util.StringUtil;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.io.ByteStreams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static org.github.spring.enumeration.ContentType.FILE;

/**
 * Top interface of file.
 *
 * <pre>
 *   return FileReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 0.0.7-SNAPSHOT
 */
public interface FileReturn extends Returnable {
  @Override
  default ContentType returnType() {
    return FILE;
  }

  @Override
  default boolean functional() {
    return false;
  }

  @Override
  default void accept(OutputStream output) throws IOException {
    ByteStreams.copy(this.resource().getInputStream(), output);
  }

  @Override
  default void collect(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Returnable.super.collect(request, this.withFileName(response));
  }

  default HttpServletResponse withFileName(@NonNull HttpServletResponse response) throws IOException {
    int lastIndex = this.get().lastIndexOf("/");
    response.addHeader("Content-Disposition", "attachment;fileName=".concat(this.get().substring(lastIndex + 1)));
    return response;
  }

  default Resource resource() throws IOException {
    return this.resource(ServletResourceLoader::new);
  }

  default Resource resource(@NonNull Supplier<ResourceLoader> loader) {
    return loader.get().getResource(this.get());
  }

  /** Generator. */
  static FileReturn of(@NonNull FileReturn file) {
    return file;
  }

  /** Generator. */
  static FileReturn of(@NonNull String file) {
    return of(file:: toString);
  }

  /** Generator. */
  static FileReturn of(@NonNull String path, @NonNull ResourceLoader loader) {
    return new PathFileReturn(path, loader);
  }

  /** Generator. */
  static FileReturn of(@NonNull String name, @NonNull InputStream input) {
    return new TempFileReturn(name, input);
  }

  /** Generator. */
  static FileReturn of(@NonNull String name, @NonNull List<String> title, @NonNull List<String> field, @NonNull List<?> data) {
    return new ExcelFileReturn(name, title, field, data);
  }

  final class TempFileReturn implements FileReturn {
    /** file name. */
    final String _name;

    /** input stream. */
    final InputStream _input;

    /** Constructor. */
    private TempFileReturn(@NonNull String name, @NonNull InputStream input) {
      this._input = input;
      this._name = name;
    }

    @Override
    public void accept(OutputStream output) throws IOException {
      ByteStreams.copy(_input, output);
    }

    @Override
    public String get() {
      return JOINER.join("The file of", "[", _name, "].");
    }

    @Override
    public HttpServletResponse withFileName(HttpServletResponse response) throws IOException {
      response.addHeader("Content-Disposition", "attachment;fileName=".concat(_name));
      return response;
    }

    @Override
    public Resource resource() throws IOException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Resource resource(Supplier<ResourceLoader> loader) {
      throw new UnsupportedOperationException();
    }
  }

  @AllArgsConstructor
  final class PathFileReturn implements FileReturn {
    /** resource path. */
    @NonNull
    private final String path;

    /** resource loader. */
    @NonNull
    private final ResourceLoader loader;

    @Override
    public Resource resource() {
      return this.resource(() -> loader);
    }

    @Override
    public String get() {
      return path;
    }
  }

  @Slf4j
  @AllArgsConstructor
  final class ExcelFileReturn implements FileReturn {
    private static final String[][] DATA_INIT = {};

    @NonNull
    private final String name;

    @Getter
    @NonNull
    private final List<String> title;

    @NonNull
    private final List<String> field;

    @NonNull
    private final List data;

    public String[][] getWrappedData() {
      if (data.isEmpty()) return DATA_INIT;
      val dataClass = this.getType();
      if (Objects.isNull(dataClass)) return DATA_INIT;

      val wrappedData = new String[data.size()][field.size()];
      for (int i = 0; i < data.size(); i++) {
        val object = data.get(i);
        for (int j = 0; j < field.size(); j++) {
          val target = field.get(j);
          try {
            val value = dataClass.getMethod("get".concat(this.headUp(target))).invoke(object);
            wrappedData[i][j] = Objects.isNull(value) ? "" : value.toString();
          } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            wrappedData[i][j] = "";
            log.error("exception-excel ==> " + e.getMessage() + " NoSuchMethod", e);
          }
        }
      }
      return wrappedData;
    }

    private Class<?> getType() {
      for (Object item : data) {if (Objects.nonNull(item)) return item.getClass();}
      return null;
    }

    private String headUp(String field) {
      if (StringUtil.isBlank(field)) throw new IllegalArgumentException("field");
      return field.substring(0, 1).toUpperCase().concat(field.substring(1));
    }

    @Override
    public String get() {
      return null;
    }

    @Override
    public void accept(OutputStream output) throws IOException {
      val workBook = ExcelGenerator.generate(this);
      workBook.write(output);
      workBook.close();
    }

    @Override
    public HttpServletResponse withFileName(HttpServletResponse response) throws IOException {
      response.addHeader("Content-Disposition", "attachment;fileName=".concat(name).concat(SUFFIX_EXCEL));
      return response;
    }
  }
}
