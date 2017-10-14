package org.github.spring.footstone;

import java.text.SimpleDateFormat;

/**
 * NaiveDateFormat.
 *
 * @author JYD_XL
 * @see java.text.SimpleDateFormat
 */
@SuppressWarnings("serial")
class NaiveDateFormat extends SimpleDateFormat implements Constants {
  /** Constructor. */
  NaiveDateFormat() {
    super(DEFAULT_DATE_FORMAT);
  }
}
