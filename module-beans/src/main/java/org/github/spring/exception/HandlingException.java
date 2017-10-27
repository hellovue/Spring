package org.github.spring.exception;

/**
 * 该异常的作用是将受检查异常转为运行时异常.
 *
 * @author JYD_XL
 * @see java.lang.RuntimeException
 */
@SuppressWarnings("serial")
public class HandlingException extends RunException {
  /** Constructor. */
  public HandlingException() {}

  /** Constructor. */
  public HandlingException(String message) {
    super(message);
  }

  /** Constructor. */
  public HandlingException(Throwable cause) {
    super(cause);
  }

  /** Constructor. */
  public HandlingException(String message, Throwable cause) {
    super(message, cause);
  }

  /** Constructor. */
  public HandlingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}