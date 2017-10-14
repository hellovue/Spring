package org.github.spring.exception;

/**
 * DataException.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public class DataException extends Exception {
  /** Constructor. */
  public DataException() {}

  /**
   * Constructor.
   *
   * @param message String
   */
  public DataException(String message) {
    super(message);
  }

  /**
   * Constructor.
   *
   * @param cause Throwable
   */
  public DataException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor.
   *
   * @param message String
   * @param cause   Throwable
   */
  public DataException(String message, Throwable cause) {
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
  public DataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
