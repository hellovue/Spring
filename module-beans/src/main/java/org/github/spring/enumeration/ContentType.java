package org.github.spring.enumeration;

import java.util.function.Supplier;

import org.springframework.http.MediaType;

/**
 * Enum of content flag,include custom flag and standard flag{@link MediaType}.
 *
 * @author JYD_XL
 * @see java.lang.Enum
 * @see java.lang.Comparable
 * @see java.io.Serializable
 * @see java.util.function.Supplier
 */
public enum ContentType implements Supplier<String> {
  /** standard return flag from spring. */
  XML("application/xml"), JSON("application/json"), TEXT("text/plain"), FILE("application/octet-stream"),

  /** custom return flag. */
  JSON_P("application/javascript");

  /** return flag. */
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
