package org.github.spring.exception;

/**
 * 该异常的作用是将受检查异常转为运行时异常.
 *
 * 1.很多异常在开发过程中可能会出现,但在生产环境中基本不会出现或者说出现了也没什么好的解决方法.
 * 2.对会产生上述异常的类进行包装时,可以用HandlingException来进行描述,从而提高代码清晰度.
 *
 * @author JYD_XL
 * @see java.lang.RuntimeException
 * @since 0.0.1-SNAPSHOT
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