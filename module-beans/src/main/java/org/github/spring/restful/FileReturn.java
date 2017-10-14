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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.github.spring.bootstrap.ServletResourceLoader;
import org.github.spring.enumeration.ContentType;
import org.github.spring.footstone.ExcelGenerator;
import org.github.spring.util.StringUtil;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.io.ByteStreams;

/**
 * FILE返回类型顶层接口.
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
@FunctionalInterface
public interface FileReturn extends Returnable {
  /** Generator. */
  static FileReturn of(@NonNull FileReturn file) {
    return file;
  }

  /** Generator. */
  static FileReturn of(@NonNull String file) {
    return of(file::toString);
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

  /** Generator. */
  static FileReturn of(@NonNull List<String> title, @NonNull List<String> field, @NonNull List<?> data) {
    return new ExcelFileReturn(PREFIX_EXCEL, title, field, data);
  }

  /** 临时文件. */
  @AllArgsConstructor
  final class TempFileReturn implements FileReturn {
    /** 文件名. */
    @NonNull
    final String fileName;

    /** 输入流. */
    @NonNull
    final InputStream inputStream;

    @Override
    public void accept(OutputStream output) throws IOException {
      ByteStreams.copy(inputStream, output);
    }

    @Override
    public String get() {
      return JOINER.join("The file of", "[", fileName, "].");
    }

    @Override
    public HttpServletResponse withFileName(HttpServletResponse response) throws IOException {
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(fileName));
      return response;
    }
  }

  /** Path File. */
  @AllArgsConstructor
  final class PathFileReturn implements FileReturn {
    /** resource path. */
    @NonNull
    final String path;

    /** resource loader. */
    @NonNull
    final ResourceLoader loader;

    @Override
    public Resource resource() {
      return this.resource(() -> loader);
    }

    @Override
    public String get() {
      return path;
    }
  }

  /** Excel导出文件. */
  @Slf4j
  @AllArgsConstructor
  final class ExcelFileReturn implements FileReturn {
    /** 文件名. */
    @NonNull
    final String name;

    /** 标题集合. */
    @Getter
    @NonNull
    final List<String> title;

    /** 属性集合. */
    @NonNull
    final List<String> field;

    /** 原始数据. */
    @NonNull
    final List data;

    /** 对原始数据进行包装, 为导出到excel做准备. */
    public String[][] getWrappedData() {
      if (data.isEmpty()) return INIT;
      val dataClass = this.getType();
      if (Objects.isNull(dataClass)) return INIT;

      val wrappedData = new String[data.size()][field.size()];
      for (int i = 0; i < data.size(); i++) {
        val object = data.get(i);
        for (int j = 0; j < field.size(); j++) {
          val target = field.get(j);
          try {
            val value = dataClass.getMethod(GET.concat(this.headUp(target))).invoke(object);
            wrappedData[i][j] = Objects.isNull(value) ? EMPTY : value.toString();
          } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            wrappedData[i][j] = EMPTY;
            log.error("exception-excel ==> " + e.getMessage() + " NoSuchMethod", e);
          }
        }
      }
      return wrappedData;
    }

    /** GET 数据类型. */
    Class<?> getType() {
      for (Object item : data) {if (Objects.nonNull(item)) return item.getClass();}
      return null;
    }

    /** 修正属性名称, 为调用GET方法做准备. */
    String headUp(String field) {
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
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(name).concat(SUFFIX_EXCEL));
      return response;
    }
  }

  @Override
  default ContentType contentType() {
    return ContentType.FILE;
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

  /** 设置下载文件名. */
  default HttpServletResponse withFileName(@NonNull HttpServletResponse response) throws IOException {
    response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(this.get().substring(this.get().lastIndexOf(ROOT) + 1)));
    return response;
  }

  /** 获取下载文件资源. */
  default Resource resource() throws IOException {
    return this.resource(ServletResourceLoader::new);
  }

  /** 根据自定义文件加载器获取文件资源. */
  default Resource resource(@NonNull Supplier<ResourceLoader> loader) {
    return loader.get().getResource(this.get());
  }
}