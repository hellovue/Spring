package org.github.spring.restful;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.spring.bootstrap.ServletResourceLoader;
import org.github.spring.enumeration.ContentType;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.io.ByteStreams;

import lombok.NonNull;

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
 * @since 0.0.1-SNAPSHOT
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
    this.setFileName(response);
    Returnable.super.collect(request, response);
  }

  default void setFileName(@NonNull HttpServletResponse response) throws IOException {
    int lastIndex = this.get().lastIndexOf("/");
    response.addHeader("Content-Disposition", "attachment;fileName=".concat(this.get().substring(lastIndex == - 1 ? 0 : lastIndex)));
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

  final class TempFileReturn implements FileReturn {
    /** file name. */
    private final String _name;

    /** input stream. */
    private final InputStream _input;

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
    public void setFileName(HttpServletResponse response) throws IOException {
      response.addHeader("Content-Disposition", "attachment;fileName=".concat(_name));
    }

    @Override
    public Resource resource() throws IOException {
      throw new UnsupportedOperationException();
    }

    @Override
    public String get() {
      return JOINER.join("The file of", "[", _name, "].");
    }

    @Override
    public Resource resource(Supplier<ResourceLoader> loader) {
      throw new UnsupportedOperationException();
    }
  }

  final class PathFileReturn implements FileReturn {
    /** resource loader. */
    private final ResourceLoader _loader;

    private final String _path;

    /** Constructor. */
    private PathFileReturn(@NonNull String path, @NonNull ResourceLoader loader) {
      this._loader = loader;
      this._path = path;
    }

    @Override
    public Resource resource() {
      return this.resource(() -> _loader);
    }

    @Override
    public String get() {
      return _path;
    }
  }
}
