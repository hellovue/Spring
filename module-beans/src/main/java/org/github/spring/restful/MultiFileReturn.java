package org.github.spring.restful;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletResponse;

import org.github.spring.bootstrap.ServletResourcePatternResolver;
import org.github.spring.util.ZipResources;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;

import lombok.NonNull;

/**
 * Top interface of files.
 *
 * <pre>
 *   return MultiFileReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.FileReturn
 * @since 1.0.0GA
 */
public interface MultiFileReturn extends FileReturn {
  @Override
  default void accept(OutputStream output) throws IOException {
    ZipResources.zipServletContextResources(output, this.resources());
  }

  @Override
  default void setFileName(@NonNull HttpServletResponse response) throws IOException {
    response.addHeader("Content-Disposition", "attachment;fileName=".concat(FILE_NAME_ZIP));
  }

  @Override
  default Resource resource() throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  default Resource resource(Supplier<ResourceLoader> loader) {
    throw new UnsupportedOperationException();
  }

  default Resource[] resources() throws IOException {
    return resources(ServletResourcePatternResolver::new);
  }

  default Resource[] resources(@NonNull Supplier<ResourcePatternResolver> resolver) throws IOException {
    return resolver.get().getResources(this.get());
  }

  static MultiFileReturn of(@NonNull MultiFileReturn multiFile) {
    return multiFile;
  }

  static MultiFileReturn of(@NonNull String multiFile) {
    return of(multiFile::toString);
  }

  static MultiFileReturn of(@NonNull String pattern, @NonNull ResourcePatternResolver resolver) {
    return new CustomMultiFileReturn(pattern, resolver);
  }

  final class CustomMultiFileReturn implements MultiFileReturn {
    /** resource pattern resolver. */
    private final ResourcePatternResolver _resolver;

    private final String _pattern;

    /** Constructor. */
    private CustomMultiFileReturn(@NonNull String pattern, @NonNull ResourcePatternResolver resolver) {
      this._resolver = resolver;
      this._pattern = pattern;
    }

    @Override
    public Resource[] resources() throws IOException {
      return this.resources(() -> _resolver);
    }

    @Override
    public String get() {
      return _pattern;
    }
  }
}
