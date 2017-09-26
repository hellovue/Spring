package org.github.spring.common.constant;

import org.github.spring.restful.VIEWReturn;

/**
 * JSPPageEnum.
 *
 * @author JYD_XL
 */
public enum JSPPageEnum implements VIEWReturn {
    LOGIN("login"), MAIN("main");

    /** path. */
    private String _path;

    /** Constructor. */
    JSPPageEnum(String path) {
        _path = path;
    }

    @Override
    public String get() {
        return PREFIX_JSP.concat(_path);
    }
}
