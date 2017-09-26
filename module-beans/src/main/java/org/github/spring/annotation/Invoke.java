package org.github.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.github.spring.annotation.Invoke.Style.JSON;
import static org.github.spring.footstone.ConstInterface.EMPTY;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
public @interface Invoke {
  String value() default EMPTY;

  Style style() default JSON;

  enum Style {
    JSON, XML
  }
}
