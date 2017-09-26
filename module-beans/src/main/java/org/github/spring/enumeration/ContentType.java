package org.github.spring.enumeration;

import java.util.function.Supplier;

import org.springframework.http.MediaType;

/**
 * Enum of content type,include custom type and standard type{@link MediaType}.
 *
 * @author JYD_XL
 * @see java.lang.Enum
 * @see java.lang.Comparable
 * @see java.io.Serializable
 * @see java.util.function.Supplier
 */
public enum ContentType implements Supplier<String> {
  /** standard return type from spring. */
  XML("application/xml"), JSON("application/json"), TEXT("text/plain"), HTML("text/html"), FILE("application/octet-stream"),

  /** custom return type. */
  JSON_P("application/javascript"), PAGE(""), JSP("jsp/");

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
