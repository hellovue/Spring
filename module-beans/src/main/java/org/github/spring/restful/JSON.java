package org.github.spring.restful;

import lombok.NonNull;

import org.github.spring.enumeration.ContentType;

/**
 * JSON返回类型顶层接口.
 *
 * <pre>
 *   return JSON_HOLDER.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 */
@FunctionalInterface
public interface JSON extends Returnable {
  @Override
  default ContentType contentType() {
    return ContentType.JSON;
  }

  /** Generator. */
  static JSON of(@NonNull JSON json) {
    return json;
  }

  /** Generator. */
  static JSON of(@NonNull String json) {
    return of(json::toString);
  }

  /** Generator. */
  static JSON of(@NonNull Object json) {
    return of(JSON_HOLDER.toJSONString(json));
  }

  /** Generator. */
  static JSON of() {
    return of(EMPTY_JSON);
  }
}