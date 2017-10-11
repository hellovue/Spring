package org.github.spring.enumeration;

import java.util.function.Supplier;

/**
 * CRUDFlag.
 *
 * @author JYD_XL
 */
public enum Flag implements Supplier<String> {
  IS_NULL("IsNull"), NOT_NULL("IsNotNull"), LIKE("Like"), LIKE_FULL("Like"), NOT_LIKE("NotLike"), NOT_LIKE_FULL("NotLike"), IN("In"), NOT_IN("NotIn"), EQUAL_TO("EqualTo"), NOT_EQUAL_TO("NotEqualTo"), LESS_THAN("LessThan"), LESS_THAN_OR_EQUAL_TO("LessThanOrEqualTo"), GREATER_THAN("GreaterThan"), GREATER_THAN_OR_EQUAL_TO("GreaterThanOrEqualTo");

  private final String name;

  Flag(String name) {
    this.name = name;
  }

  @Override
  public String get() {
    return this.name;
  }
}
