package org.github.spring.restful;

import lombok.NonNull;

import org.github.spring.enumeration.ContentType;

/**
 * JSON返回类型顶层接口.
 *
 * <pre>
 *   return JSONReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 0.0.1-SNAPSHOT
 */
@FunctionalInterface
public interface JSONReturn extends Returnable {
  @Override
  default ContentType contentType() {
    return ContentType.JSON;
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
  static JSONReturn of(@NonNull Object json) {
    return of(JSON.toJSONString(json));
  }

  /** Generator. */
  static JSONReturn of() {
    return of(EMPTY_JSON);
  }
}