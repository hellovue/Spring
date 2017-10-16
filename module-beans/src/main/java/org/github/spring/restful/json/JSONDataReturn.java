package org.github.spring.restful.json;

import org.github.spring.restful.JSONReturn;

/**
 * JSONReturn of data.
 *
 * <pre>
 *   return JSONDataReturn.of();
 * </pre>
 *
 * @param <T> data
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSONReturn
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.github.spring.restful.json.JSONBasicReturn
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public class JSONDataReturn<T> extends JSONBasicReturn implements JSONReturn {
  /** data. */
  private transient T data;

  /** Constructor. */
  public JSONDataReturn() {}

  /** Constructor. */
  public JSONDataReturn(T data) {
    this.withData(data);
  }

  @Override
  public void release() {
    data = null;
    super.release();
  }

  @Override
  public JSONDataReturn clone() {
    return (JSONDataReturn) super.clone();
  }

  /** GET data. */
  public T getData() {
    return data;
  }

  /** SET data. */
  public void setData(T data) {
    this.data = data;
  }

  /** WITH data. */
  public JSONDataReturn<T> withData(T data) {
    this.setData(data);
    return this;
  }

  /** Generator. */
  public static JSONDataReturn of() {
    return new JSONDataReturn();
  }

  /** Generator. */
  public static <V> JSONDataReturn<V> of(V data) {
    return new JSONDataReturn<>(data);
  }

  /** Generator. */
  @SafeVarargs
  public static <V> JSONDataReturn<V[]> of(V... data) {
    return new JSONDataReturn<>(data);
  }
}