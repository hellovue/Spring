package org.github.spring.restful;

import lombok.NonNull;

/**
 * VIEW返回类型顶层接口.
 *
 * <pre>
 *   return VIEWReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 0.0.1-SNAPSHOT
 */
@FunctionalInterface
public interface VIEWReturn extends Returnable {
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

  /** Generator. */
  static VIEWReturn of(Object... path) {
    return of(JOINER_EMPTY.join(path));
  }

  /** Generator. */
  static VIEWReturn of() {
    return of(ROOT);
  }
}