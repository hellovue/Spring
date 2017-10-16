package org.github.spring.exception;

/**
 * DataException.
 *
 * @author JYD_XL
 * @see java.lang.Exception
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public class DataException extends Exception {
  /** Constructor. */
  public DataException() {}

  /** Constructor. */
  public DataException(String message) {
    super(message);
  }

  /** Constructor. */
  public DataException(Throwable cause) {
    super(cause);
  }

  /** Constructor. */
  public DataException(String message, Throwable cause) {
    super(message, cause);
  }

  /** Constructor. */
  public DataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}