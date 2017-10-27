package org.github.spring.footstone;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 属性路径获取接口,请定义属性路径枚举并实现此接口.
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface PropertyPathLocation extends Serializable, Supplier<String> {}