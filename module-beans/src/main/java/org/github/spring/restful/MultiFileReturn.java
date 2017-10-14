package org.github.spring.restful;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import org.github.spring.bootstrap.ServletResourcePatternResolver;
import org.github.spring.footstone.ZipResources;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

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
 * @since 0.0.7-SNAPSHOT
 */
@FunctionalInterface
public interface MultiFileReturn extends FileReturn {
  /** Generator. */
  static MultiFileReturn of(@NonNull MultiFileReturn multiFile) {
    return multiFile;
  }

  /** Generator. */
  static MultiFileReturn of(@NonNull String multiFile) {
    return of(multiFile::toString);
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
  @AllArgsConstructor
  final class CustomMultiFileReturn implements MultiFileReturn {
    /** 打包文件名. */
    @NonNull
    final String name;

    /** 资源路径匹配模式. */
    @NonNull
    final String pattern;

    /** 资源路径解析器. */
    @NonNull
    final ResourcePatternResolver resolver;

    @Override
    public String get() {
      return pattern;
    }

    @Override
    public HttpServletResponse withFileName(HttpServletResponse response) throws IOException {
      response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(name).concat(SUFFIX_ZIP));
      return response;
    }

    @Override
    public Resource[] resources() throws IOException {
      return this.resources(() -> resolver);
    }
  }

  @Override
  default void accept(OutputStream output) throws IOException {
    ZipResources.zipServletContextResources(output, this.resources());
  }

  @Override
  default HttpServletResponse withFileName(@NonNull HttpServletResponse response) throws IOException {
    response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME.concat(FILE_NAME_ZIP));
    return response;
  }

  default Resource[] resources() throws IOException {
    return resources(ServletResourcePatternResolver::new);
  }

  default Resource[] resources(@NonNull Supplier<ResourcePatternResolver> resolver) throws IOException {
    return resolver.get().getResources(this.get());
  }
}