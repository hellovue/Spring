package org.github.spring.exception;

/**
 * TransactionException.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public class TransactionException extends Exception {
  /** Constructor. */
  public TransactionException() {}

  /**
   * Constructor.
   *
   * @param message String
   */
  public TransactionException(String message) {
    super(message);
  }

  /**
   * Constructor.
   *
   * @param cause Throwable
   */
  public TransactionException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor.
   *
   * @param message String
   * @param cause   Throwable
   */
  public TransactionException(String message, Throwable cause) {
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
  public TransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
