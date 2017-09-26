package org.github.spring.common.constant;

import org.github.spring.restful.VIEWReturn;

/**
 * CACHE_PAGE_HTML page enum.
 *
 * @author JYD_XL
 */
public enum HTMLPageEnum implements VIEWReturn {
  INDEX("index"), HOME("home"), MAIN("main"), STAR("star");

  /** path. */
  private final String _path;

  /** Constructor. */
  HTMLPageEnum(String path) {
    _path = path;
  }

  @Override
  public String get() {
    return PREFIX_HTML.concat(_path);
  }
}
