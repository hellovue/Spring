package org.github.spring.exception;

/**
 * TransactionException.
 *
 * @author JYD_XL
 * @see java.lang.Exception
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public class TransactionException extends Exception {
  /** Constructor. */
  public TransactionException() {}

  /** Constructor. */
  public TransactionException(String message) {
    super(message);
  }

  /** Constructor. */
  public TransactionException(Throwable cause) {
    super(cause);
  }

  /** Constructor. */
  public TransactionException(String message, Throwable cause) {
    super(message, cause);
  }

  /** Constructor. */
  public TransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}