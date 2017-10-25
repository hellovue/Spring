package org.github.spring.restful.json;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;

import org.github.spring.enumeration.ContentType;
import org.github.spring.restful.JSONReturn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * JSONReturn of jsonp.
 *
 * <pre>
 *   return JSONPReturn.of();
 * </pre>
 *
 * @param <T> data
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSONReturn
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.github.spring.restful.json.JSONBasicReturn
 * @see org.github.spring.restful.json.JSONDataReturn
 */
@JsonIgnoreProperties("callback")
@SuppressWarnings("serial")
public class JSONPReturn<T> extends JSONDataReturn<T> implements JSONReturn {
  /** callback. */
  private String callback = CALL_BACK;

  /** Constructor. */
  public JSONPReturn() {}

  /** Constructor. */
  public JSONPReturn(T data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONPReturn(String callback, T data) {
    this.withCallback(callback).withData(data);
  }

  @Override
  public void collect(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws IOException {
    Optional.of(callback).filter(CALL_BACK::equals).ifPresent(v -> this.setCallback(request.getParameter(CALL_BACK)));
    super.collect(request, response);
  }

  @Override
  public boolean functional() {
    return true;
  }

  @Override
  public String get() {
    return JOINER_EMPTY.join(callback, "(", super.get(), ")", ";");
  }

  @Override
  public ContentType contentType() {
    return ContentType.JSONP;
  }

  @Override
  public void release() {
    callback = CALL_BACK;
    super.release();
  }

  /** GET callback. */
  public String getCallback() {
    return callback;
  }

  /** SET callback. */
  public void setCallback(String callback) {
    Optional.ofNullable(callback).filter(v -> PATTERN_PARAM.matcher(v).matches()).ifPresent(v -> this.callback = v);
  }

  /** WITH callback. */
  public JSONPReturn<T> withCallback(String callback) {
    this.setCallback(callback);
    return this;
  }

  /** Generator. */
  public static JSONPReturn of() {
    return new JSONPReturn();
  }

  /** Generator. */
  public static <V> JSONPReturn<V> of(V data) {
    return new JSONPReturn<>(data);
  }

  /** Generator. */
  @SafeVarargs
  public static <V> JSONPReturn<V[]> of(V... data) {
    return new JSONPReturn<>(data);
  }
}