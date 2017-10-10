package org.github.spring.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.github.spring.enumeration.Flag;

import static org.github.spring.enumeration.Flag.EQUAL_TO;
import static org.github.spring.footstone.ConstInterface.EMPTY;

/**
 * 增删改查条件注入标记注解.
 *
 * @author JYD_XL
 * @see Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Repeatable(Columns.class)
public @interface Column {
  /**
   * GET flag.
   *
   * @return flag
   */
  Flag flag() default EQUAL_TO;

  /**
   * GET goal.
   *
   * @return goal
   */
  String goal() default EMPTY;
}
