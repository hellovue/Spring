package org.github.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.github.spring.enumeration.Method;

/**
 * CRUD标记注解.
 *
 * @author JYD_XL
 * @version 0.0.7-SNAPSHOT
 * @see java.lang.annotation.Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Repeatable(Columns.class)
public @interface Column {
  /** GET field. */
  String field() default "";

  /** GET value. */
  Method value() default Method.EQUAL_TO;
}
