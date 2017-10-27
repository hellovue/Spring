package org.github.spring.restful;

import lombok.NonNull;

/**
 * VIEW返回类型顶层接口.
 *
 * <pre>
 *   return View.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 0.0.1-SNAPSHOT
 */
@FunctionalInterface
public interface View extends Returnable {
  @Override
  default boolean terminal() {
    return false;
  }

  /** Generator. */
  static View of(@NonNull View view) {
    return view;
  }

  /** Generator. */
  static View of(@NonNull String view) {
    return of(view::toString);
  }

  /** Generator. */
  static View of(Object... path) {
    return of(JOINER_EMPTY.join(path));
  }

  /** Generator. */
  static View of() {
    return of(ROOT);
  }
}