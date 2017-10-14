package org.github.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Primary method标记注解, 被该注解标记的方法为对应的类中的主要方法.
 *
 * @author JYD_XL
 * @see java.lang.annotation.Annotation
 * @since 0.0.7-SNAPSHOT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Documented
public @interface PrimaryMethod {}
