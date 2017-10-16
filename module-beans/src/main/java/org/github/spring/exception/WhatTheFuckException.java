package org.github.spring.exception;

/**
 * WhatTheFuckException.
 *
 * @author JYD_XL
 * @see java.lang.RuntimeException
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public class WhatTheFuckException extends RunException {
  /** Constructor. */
  public WhatTheFuckException() {
    super("What the hell are you coding?");
  }
}