package org.github.spring.enumeration;

import java.util.function.Supplier;

import org.springframework.http.MediaType;

/**
 * Enum of content value,include custom value and standard value{@link MediaType}.
 * Enum of content type, include custom type and standard type {@link MediaType}.
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @since 0.0.1-SNAPSHOT
 */
public enum ContentType implements Supplier<String> {
  /** standard return flag from spring. */
  XML("application/xml"), JSON("application/json"), TEXT("text/plain"), FILE("application/octet-stream"),

  /** custom return type. */
  JSON_P("application/javascript");

  /** return type. */
  private final String _type;

  /** Constructor. */
  ContentType(String type) {
    _type = type;
  }

  @Override
  public String get() {
    return _type;
  }
}
