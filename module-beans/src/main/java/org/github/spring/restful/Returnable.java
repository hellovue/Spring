package org.github.spring.restful;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;

import org.github.spring.enumeration.ContentType;
import org.github.spring.footstone.ConstInterface;

import org.springframework.http.MediaType;

import static org.github.spring.enumeration.ContentType.TEXT;

/**
 * Top interface of all.
 *
 * 1.The core function of this interface is {@link Supplier#get}.
 * 2.This interface returns a string {@code text/plain; charset=UTF-8} by default.
 * 3.This interface is a functional interface {@link FunctionalInterface}, recommended to use lambda expressions.
 *
 * <pre>
 *     return Returnable.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @since 0.0.7-SNAPSHOT
 */
@FunctionalInterface
public interface Returnable extends Serializable, ConstInterface, Supplier<String> {
  /** Get content flag {@link MediaType} of return data. */
  default ContentType returnType() {
    return TEXT;
  }

  /** Whether the request has been handled fully within the handler. */
  default boolean terminal() {
    return true;
  }

  /** Cleanup data. */
  default void release() {
    //The interface has no attributes,so it can not perform cleanup operations. The default implementation here is empty...
  }

  /** Transmit data through the core function {@link Supplier#get} directly. */
  default boolean functional() {
    return true;
  }

  /** Consume data by writer {@link Writer}. */
  default void accept(@NonNull Writer writer) throws IOException {
    writer.write(this.get());
  }

  /** Consume data by outputStream {@link OutputStream}. */
  default void accept(@NonNull OutputStream output) throws IOException {
    throw new UnsupportedOperationException();
  }

  /** Collect data by request {@link HttpServletRequest} and response {@link HttpServletResponse}. */
  default void collect(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws IOException {
    response.setCharacterEncoding(UTF8);
    response.setContentType(this.returnType().get());
    if (this.functional()) {
      this.accept(response.getWriter());
    } else {
      this.accept(response.getOutputStream());
    }
  }

  /** Generator. */
  static Returnable of(@NonNull Returnable data) {
    return data;
  }

  /** Generator. */
  static Returnable of(@NonNull String data) {
    return of(data::toString);
  }

  /** Generator. */
  static Returnable of() {
    return of(EMPTY);
  }
}
