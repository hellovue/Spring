package org.github.spring.restful;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;
import lombok.Value;

import org.github.spring.bootstrap.ApplicationContextHolder;
import org.github.spring.footstone.ZipResources;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

/**
 * MultiFile返回类型顶层接口.
 *
 * <pre>
 *   return MultiFileReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.FileReturn
 * @since 0.0.1-SNAPSHOT
 */
@FunctionalInterface
public interface MultiFileReturn extends FileReturn {
  @Override
  default void accept(OutputStream output) throws IOException {
    ZipResources.zipServletContextResources(output, this.resources());
  }

  @Override
  default void setFileName(@NonNull HttpServletResponse response) throws IOException {
    response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(FILE_NAME_ZIP));
  }

  default Resource[] resources() throws IOException {
    return resources(() -> new ServletContextResourcePatternResolver(ApplicationContextHolder.getApplicationContext()));
  }

  default Resource[] resources(@NonNull Supplier<ResourcePatternResolver> resolver) throws IOException {
    return resolver.get().getResources(this.get());
  }

  /** Generator. */
  static MultiFileReturn of(@NonNull MultiFileReturn multiFile) {
    return multiFile;
  }

  /** Generator. */
  static MultiFileReturn of(@NonNull String pattern) {
    return of(pattern::toString);
  }

  /** Generator. */
  static MultiFileReturn of(@NonNull String name, @NonNull String pattern) {
    return new CustomMultiFileReturn(name, pattern, new ServletContextResourcePatternResolver(ApplicationContextHolder.getApplicationContext()));
  }

  /** Generator. */
  static MultiFileReturn of(@NonNull String name, @NonNull String pattern, @NonNull ResourcePatternResolver resolver) {
    return new CustomMultiFileReturn(name, pattern, resolver);
  }

  /** Generator. */
  static MultiFileReturn of(@NonNull String pattern, @NonNull ResourcePatternResolver resolver) {
    return new CustomMultiFileReturn(PREFIX_ZIP, pattern, resolver);
  }

  /** 自定义MultiFile类型. */
  @Value
  class CustomMultiFileReturn implements MultiFileReturn {
    /** 打包文件名. */
    String name;

    /** 资源路径匹配模式. */
    String pattern;

    /** 资源路径解析器. */
    ResourcePatternResolver resolver;

    @Override
    public String get() {
      return pattern;
    }

    @Override
    public void setFileName(HttpServletResponse response) throws IOException {
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(name).concat(SUFFIX_ZIP));
    }

    @Override
    public Resource[] resources() throws IOException {
      return this.resources(() -> resolver);
    }
  }
}