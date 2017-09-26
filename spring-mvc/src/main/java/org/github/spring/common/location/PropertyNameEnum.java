package org.github.spring.common.location;


import org.github.spring.footstone.PropertyNameLocation;

/**
 * 属性名称枚举.
 *
 * @author JYD_XL
 * @see java.lang.Enum
 * @see java.io.Serializable
 * @see java.lang.Comparable
 * @see java.util.function.Supplier
 * @see org.github.spring.footstone.PropertyNameLocation
 */
public enum PropertyNameEnum implements PropertyNameLocation {
    driver("hikariDataSource.driver");

    /** name. */
    private final String _name;

    /**
     * 构造方法.
     *
     * @param name 属性名称
     */
    PropertyNameEnum(String name) {
        this._name = name;
    }

    @Override
    public String get() {
        return _name;
    }
}