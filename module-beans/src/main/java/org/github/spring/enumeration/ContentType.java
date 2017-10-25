package org.github.spring.enumeration;

import java.util.function.Supplier;

/**
 * 返回类型枚举.
 *
 * @author JYD_XL
 * @see java.lang.Enum
 * @see java.util.function.Supplier
 * @since 0.0.1-SNAPSHOT
 */
public enum ContentType implements Supplier<String> {
  XML("application/xml"), JSON("application/json"), TEXT("text/plain"), FILE("application/octet-stream"), JSONP("application/javascript");

  /** 返回类型. */
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