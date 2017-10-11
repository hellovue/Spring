package org.github.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * INVOKE mark annotation.
 *
 * @author JYD_XL
 * @version 0.0.7-SNAPSHOT
 * @see java.lang.annotation.Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
public @interface Invoke {
  /** GET invoke target. */
  String value() default "";

  /** GET invoke schema. */
  Style style() default Style.JSON;

  /** invoke mode. */
  enum Style {
    JSON, XML
  }
}
