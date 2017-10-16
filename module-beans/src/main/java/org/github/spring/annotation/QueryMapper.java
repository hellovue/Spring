package org.github.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyBatis mapper标记注解, 被该注解标记的接口为MyBatis的组件之一.
 *
 * @author JYD_XL
 * @see java.lang.annotation.Annotation
 * @since 0.0.1-SNAPSHOT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface QueryMapper {}