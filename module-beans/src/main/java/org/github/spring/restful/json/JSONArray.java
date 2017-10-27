package org.github.spring.restful.json;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.github.spring.restful.JSON;

/**
 * JSON_HOLDER of array.
 *
 * <pre>
 *   return JSONArray.of();
 * </pre>
 *
 * @param <E> element
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSON
 * @see org.github.spring.footstone.AbstractEntity
 * @see JSONBasic
 */
@SuppressWarnings("serial")
public class JSONArray<E> extends JSONBasic implements JSON {
  /** data. */
  private transient Object[] data = ARRAY;

  /** Constructor. */
  public JSONArray() {}

  /** Constructor. */
  @SafeVarargs
  public JSONArray(E... data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONArray(Stream<? extends E> data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONArray(Collection<? extends E> data) {
    this.withData(data);
  }

  @Override
  public void release() {
    data = ARRAY;
    super.release();
  }

  /** GET data. */
  public Object[] getData() {
    return data;
  }

  /** SET data. */
  public void setData(Object[] data) {
    Optional.ofNullable(data).ifPresent(v -> this.data = v);
  }

  /** GET data. */
  @SuppressWarnings("unchecked")
  public E[] toArray() {
    return (E[]) this.getData();
  }

  /** GET data. */
  @SuppressWarnings("unchecked")
  public List<E> toList() {
    return (List<E>) Arrays.asList(data);
  }

  /** GET data. */
  @SuppressWarnings("unchecked")
  public Stream<E> toStream() {
    return (Stream<E>) Arrays.stream(data);
  }

  /** WITH data. */
  @SuppressWarnings("unchecked")
  public JSONArray<E> withData(E... data) {
    this.setData(data);
    return this;
  }

  /** WITH data. */
  public JSONArray<E> withData(Stream<? extends E> data) {
    Optional.ofNullable(data).ifPresent(v -> this.data = v.toArray());
    return this;
  }

  /** WITH data. */
  public JSONArray<E> withData(Collection<? extends E> data) {
    Optional.ofNullable(data).ifPresent(v -> this.data = v.toArray());
    return this;
  }

  /** Generator. */
  public static JSONArray of() {
    return new JSONArray();
  }

  /** Generator. */
  @SafeVarargs
  public static <V> JSONArray<V> of(V... data) {
    return new JSONArray<>(data);
  }

  /** Generator. */
  public static <V> JSONArray<V> of(Stream<? extends V> data) {
    return new JSONArray<>(data);
  }

  /** Generator. */
  public static <V> JSONArray<V> of(Collection<? extends V> data) {
    return new JSONArray<>(data);
  }
}