package org.github.spring.restful.json;

/**
 * JSON of data.
 *
 * <pre>
 *   return JSONData.of();
 * </pre>
 *
 * @param <T> data
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSON
 * @see org.github.spring.footstone.AbstractEntity
 * @see JSONBasic
 */
@SuppressWarnings("serial")
public class JSONData<T> extends JSONBasic implements org.github.spring.restful.JSON {
  /** data. */
  private transient T data;

  /** Constructor. */
  public JSONData() {}

  /** Constructor. */
  public JSONData(T data) {
    this.withData(data);
  }

  @Override
  public void release() {
    data = null;
    super.release();
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
  public JSONData<T> withData(T data) {
    this.setData(data);
    return this;
  }

  /** Generator. */
  public static JSONData of() {
    return new JSONData();
  }

  /** Generator. */
  public static <V> JSONData<V> of(V data) {
    return new JSONData<>(data);
  }

  /** Generator. */
  @SafeVarargs
  public static <V> JSONData<V[]> of(V... data) {
    return new JSONData<>(data);
  }
}