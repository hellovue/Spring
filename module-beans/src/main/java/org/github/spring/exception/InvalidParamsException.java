package org.github.spring.exception;

import java.util.Arrays;
import java.util.List;

import org.github.spring.footstone.Constants;

import com.google.common.base.Joiner;

/**
 * InvalidParamsException.
 *
 * @author JYD_XL
 * @see java.lang.RuntimeException
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public class InvalidParamsException extends RunException implements Constants {
  /** params. */
  private final List<String> params;

  /** Constructor. */
  public InvalidParamsException(String... param) {
    params = Arrays.asList(param);
  }

  @Override
  public String getMessage() {
    return JOINER_SPACE.join("[", Joiner.on(", ").skipNulls().join(params), "],", "上述参数非法...");
  }
}