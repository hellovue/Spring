package org.github.spring.footstone;

import lombok.NonNull;

import java.text.SimpleDateFormat;

/**
 * NaiveDateFormat.
 *
 * @author JYD_XL
 * @see java.text.Format
 * @see java.lang.Cloneable
 * @see java.io.Serializable
 * @see java.text.DateFormat
 * @see java.text.SimpleDateFormat
 * @see org.github.spring.footstone.ConstInterface
 */
@SuppressWarnings("serial")
public class NaiveDateFormat extends SimpleDateFormat implements ConstInterface {
  /** Constructor. */
  public NaiveDateFormat() {
    this(DEFAULT_DATE_FORMAT);
  }

  /** Constructor. */
  public NaiveDateFormat(@NonNull String pattern) {
    super(pattern);
  }
}
