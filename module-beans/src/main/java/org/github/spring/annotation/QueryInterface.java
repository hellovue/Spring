package org.github.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * QueryInterface.
 *
 * @author JYD_XL
 * @version 0.0.7-SNAPSHOT
 * @see java.lang.annotation.Annotation
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryInterface {}