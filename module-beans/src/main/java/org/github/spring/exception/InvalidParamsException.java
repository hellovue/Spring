package org.github.spring.exception;

import com.google.common.collect.ImmutableList;
import org.github.spring.footstone.ConstInterface;

import java.util.List;

/**
 * InvalidParamsException.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public class InvalidParamsException extends RunException implements ConstInterface {
  /** params. */
  private List<String> params;

  /** Constructor. */
  public InvalidParamsException(String... param) {
    params = ImmutableList.copyOf(param);
  }

  @Override
  public String getMessage() {
    return JOINER.join(params, "invalid param of them. ");
  }

  /**
   * Constructor.
   *
   * @param message String
   */
  public InvalidParamsException(String message) {
    super(message);
  }

  /**
   * Constructor.
   *
   * @param cause Throwable
   */
  public InvalidParamsException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor.
   *
   * @param message String
   * @param cause   Throwable
   */
  public InvalidParamsException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor.
   *
   * @param message            String
   * @param cause              Throwable
   * @param enableSuppression  boolean
   * @param writableStackTrace boolean
   */
  public InvalidParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
