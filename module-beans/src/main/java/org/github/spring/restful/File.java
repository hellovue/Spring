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
import lombok.Cleanup;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.github.spring.bootstrap.ServletContextHolder;
import org.github.spring.enumeration.ContentType;
import org.github.spring.footstone.ExcelGenerator;
import org.github.spring.util.StringUtil;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.support.ServletContextResourceLoader;

import com.google.common.io.ByteStreams;

/**
 * File返回类型顶层接口.
 *
 * <pre>
 *   return File.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 */
@FunctionalInterface
public interface File extends Returnable {
  @Override
  default void accept(@NonNull OutputStream output) throws IOException {
    @Cleanup val input = this.resource().getInputStream();
    ByteStreams.copy(input, output);
  }

  @Override
  default void collect(HttpServletRequest request, HttpServletResponse response) throws IOException {
    this.setFileName(response);
    Returnable.super.collect(request, response);
  }

  @Override
  default ContentType contentType() {
    return ContentType.FILE;
  }

  @Override
  default boolean functional() {
    return false;
  }

  /** 获取下载文件资源. */
  default Resource resource() throws IOException {
    return this.resource(() -> new ServletContextResourceLoader(ServletContextHolder.getServletContext()));
  }

  /** 根据自定义文件加载器获取文件资源. */
  default Resource resource(@NonNull Supplier<ResourceLoader> loader) {
    return loader.get().getResource(this.get());
  }

  /** 设置下载文件名. */
  default void setFileName(@NonNull HttpServletResponse response) throws IOException {
    response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(this.get().substring(this.get().lastIndexOf(ROOT) + 1)));
  }

  /** Generator. */
  static File of(@NonNull File file) {
    return file;
  }

  /** Generator. */
  static File of(@NonNull String file) {
    return of(file::toString);
  }

  /** Generator. */
  static File of(@NonNull String path, @NonNull ResourceLoader loader) {
    return new PathFile(path, loader);
  }

  /** Generator. */
  static File of(@NonNull String name, @NonNull InputStream input) {
    return new TempFile(name, input);
  }

  /** Generator. */
  static File of(@NonNull String name, @NonNull List<String> title, @NonNull List<String> field, @NonNull List<?> data) {
    return new ExcelFile(name, title, field, data);
  }

  /** Generator. */
  static File of(@NonNull List<String> title, @NonNull List<String> field, @NonNull List<?> data) {
    return new ExcelFile(PREFIX_EXCEL, title, field, data);
  }

  /** 临时文件. */
  @AllArgsConstructor
  final class TempFile implements File {
    /** 文件名. */
    @NonNull
    final String fileName;

    /** 输入流. */
    @NonNull
    final InputStream inputStream;

    @Override
    public void accept(OutputStream output) throws IOException {
      ByteStreams.copy(inputStream, output);
      inputStream.close();
    }

    @Override
    public String get() {
      return JOINER.join("The file of", "[", fileName, "].");
    }

    @Override
    public void setFileName(HttpServletResponse response) throws IOException {
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(fileName));
    }
  }

  /** Path File. */
  @Value
  class PathFile implements File {
    /** resource path. */
    String path;

    /** resource loader. */
    ResourceLoader loader;

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
  @Value
  class ExcelFile implements File {
    /** 文件名. */
    String name;

    /** 标题集合. */
    @Getter
    List<String> title;

    /** 属性集合. */
    List<String> field;

    /** 原始数据. */
    List data;

    /** GET 数据类型. */
    Class<?> getType() {
      for (val item : data) {if (Objects.nonNull(item)) return item.getClass();}
      return null;
    }

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
      @Cleanup val workBook = ExcelGenerator.generate(this);
      workBook.write(output);
    }

    @Override
    public void setFileName(HttpServletResponse response) throws IOException {
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(name).concat(SUFFIX_EXCEL));
    }
  }
}