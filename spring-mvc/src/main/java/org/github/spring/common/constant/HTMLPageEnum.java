package org.github.spring.common.constant;

import org.github.spring.restful.VIEWReturn;

import lombok.NonNull;

/**
 * HTML page enum.
 *
 * @author JYD_XL
 */
public enum HTMLPageEnum implements VIEWReturn {
  INDEX("index"), HOME("home"), MAIN("main"), STAR("star");

  /** path. */
  private final String _path;

  /** Constructor. */
  HTMLPageEnum(@NonNull String path) {
    _path = PREFIX_HTML.concat(path);
  }

  @Override
  public String get() {
    return _path;
  }
}
