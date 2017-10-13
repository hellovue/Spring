package org.github.spring.restful;

import org.github.spring.enumeration.ContentType;

import lombok.NonNull;

/**
 * Top interface of view.
 *
 * <pre>
 *   return VIEWReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 0.0.7-SNAPSHOT
 */
@FunctionalInterface
public interface VIEWReturn extends Returnable {
  @Override
  default ContentType contentType() {
    throw new UnsupportedOperationException();
  }

  @Override
  default boolean terminal() {
    return false;
  }

  /** Generator. */
  static VIEWReturn of(@NonNull VIEWReturn view) {
    return view;
  }

  /** Generator. */
  static VIEWReturn of(@NonNull String view) {
    return of(view::toString);
  }
}
