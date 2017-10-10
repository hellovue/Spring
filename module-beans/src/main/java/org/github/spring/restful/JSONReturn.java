package org.github.spring.restful;

import lombok.NonNull;

import org.github.spring.enumeration.ContentType;

import static org.github.spring.enumeration.ContentType.JSON;

/**
 * Top interface of json.
 *
 * <pre>
 *   return JSONReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 0.0.7-SNAPSHOT
 */
@FunctionalInterface
public interface JSONReturn extends Returnable {
  @Override
  default ContentType returnType() {
    return JSON;
  }

  /** Generator. */
  static JSONReturn of(@NonNull JSONReturn json) {
    return json;
  }

  /** Generator. */
  static JSONReturn of(@NonNull String json) {
    return of(json::toString);
  }

  /** Generator. */
  static JSONReturn of() {
    return of(EMPTY_JSON);
  }
}
