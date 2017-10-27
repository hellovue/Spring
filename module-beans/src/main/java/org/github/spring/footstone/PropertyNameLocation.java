package org.github.spring.footstone;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 属性名称获取接口,请定义属性名称枚举并实现此接口.
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface PropertyNameLocation extends Serializable, Supplier<String> {}