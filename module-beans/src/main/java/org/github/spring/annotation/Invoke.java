package org.github.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数注入标记注解.
 *
 * @author JYD_XL
 * @see java.lang.annotation.Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
public @interface Invoke {
  /** GET 注入的参数类型. */
  Format style() default Format.JSON;

  /** GET 注入的参数名称. */
  String value() default "";

  /** 参数注入类型. */
  enum Format {
    JSON, XML
  }
}