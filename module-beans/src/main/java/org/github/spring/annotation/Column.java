package org.github.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.github.spring.enumeration.Flag;

/**
 * CRUD mark annotation.
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
  /** GET flag. */
  Flag flag() default Flag.EQUAL_TO;

  /** GET goal. */
  String goal() default "";
}
