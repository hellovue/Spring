package org.github.spring.exception;

/**
 * RunException.
 *
 * @author JYD_XL
 * @see java.lang.RuntimeException
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public class RunException extends RuntimeException {
  /** Constructor. */
  public RunException() {}

  /** Constructor. */
  public RunException(String message) {
    super(message);
  }

  /** Constructor. */
  public RunException(Throwable cause) {
    super(cause);
  }

  /** Constructor. */
  public RunException(String message, Throwable cause) {
    super(message, cause);
  }

  /** Constructor. */
  public RunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}