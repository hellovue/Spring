package org.github.spring.exception;

/**
 * RunException.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public class RunException extends RuntimeException {
  /** Constructor. */
  public RunException() {}

  /**
   * Constructor.
   *
   * @param message String
   */
  public RunException(String message) {
    super(message);
  }

  /**
   * Constructor.
   *
   * @param cause Throwable
   */
  public RunException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor.
   *
   * @param message String
   * @param cause   Throwable
   */
  public RunException(String message, Throwable cause) {
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
  public RunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
