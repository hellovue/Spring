package org.github.spring.enumeration;

import java.util.function.Supplier;

/**
 * CRUD反射方法枚举.
 *
 * @author JYD_XL
 * @see java.lang.Enum
 * @see java.util.function.Supplier
 * @since 0.0.1-SNAPSHOT
 */
public enum Method implements Supplier<String> {
  IN("In"), NOT_IN("NotIn"),
  IS_NULL("IsNull"), NOT_NULL("IsNotNull"),
  EQUAL_TO("EqualTo"), NOT_EQUAL_TO("NotEqualTo"),
  LESS_THAN("LessThan"), LESS_THAN_OR_EQUAL_TO("LessThanOrEqualTo"),
  GREATER_THAN("GreaterThan"), GREATER_THAN_OR_EQUAL_TO("GreaterThanOrEqualTo"),
  LIKE("Like"), LIKE_FULL("Like"), NOT_LIKE("NotLike"), NOT_LIKE_FULL("NotLike");

  /** 反射方法名. */
  private final String _method;

  /** Constructor. */
  Method(String method) {
    this._method = method;
  }

  @Override
  public String get() {
    return this._method;
  }
}