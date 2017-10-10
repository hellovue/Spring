package org.github.spring.restful.json;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.github.spring.restful.JSONReturn;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * JSONReturn of array.
 *
 * <pre>
 *   return JSONArrayReturn.of();
 * </pre>
 *
 * @param <E> element
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSONReturn
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.github.spring.restful.json.JSONBasicReturn
 * @since 0.0.4-SNAPSHOT
 */
@SuppressWarnings("serial")
public class JSONArrayReturn<E> extends JSONBasicReturn implements JSONReturn {
  /** data. */
  private transient Object[] data = ARRAY;

  /** Constructor. */
  public JSONArrayReturn() {}

  /** Constructor. */
  @SafeVarargs
  public JSONArrayReturn(E... data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONArrayReturn(Stream<? extends E> data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONArrayReturn(Collection<? extends E> data) {
    this.withData(data);
  }

  @Override
  public void release() {
    data = ARRAY;
    super.release();
  }

  @Override
  public JSONArrayReturn clone() {
    return (JSONArrayReturn) super.clone();
  }

  /** GET data. */
  public Object[] getData() {
    return data;
  }

  /** SET data. */
  public void setData(Object[] data) {
    Optional.ofNullable(data).ifPresent(v -> this.data = v);
  }

  /** WITH data. */
  @CanIgnoreReturnValue
  @SuppressWarnings("unchecked")
  public JSONArrayReturn<E> withData(E... data) {
    this.setData(data);
    return this;
  }

  /** WITH data. */
  @CanIgnoreReturnValue
  public JSONArrayReturn<E> withData(Stream<? extends E> data) {
    Optional.ofNullable(data).ifPresent(v -> this.data = v.toArray());
    return this;
  }

  /** WITH data. */
  @CanIgnoreReturnValue
  public JSONArrayReturn<E> withData(Collection<? extends E> data) {
    Optional.ofNullable(data).ifPresent(v -> this.data = v.toArray());
    return this;
  }

  /** GET data. */
  @SuppressWarnings("unchecked")
  public E[] toArray() {
    return (E[]) this.getData();
  }

  /** GET data. */
  @SuppressWarnings("unchecked")
  public Stream<E> toStream() {
    return (Stream<E>) Arrays.stream(data);
  }

  /** GET data. */
  @SuppressWarnings("unchecked")
  public List<E> toList() {
    return (List<E>) Arrays.asList(data);
  }

  /** Generator. */
  public static JSONArrayReturn of() {
    return new JSONArrayReturn();
  }

  /** Generator. */
  @SafeVarargs
  public static <V> JSONArrayReturn<V> of(V... data) {
    return new JSONArrayReturn<>(data);
  }

  /** Generator. */
  public static <V> JSONArrayReturn<V> of(Stream<? extends V> data) {
    return new JSONArrayReturn<>(data);
  }

  /** Generator. */
  public static <V> JSONArrayReturn<V> of(Collection<? extends V> data) {
    return new JSONArrayReturn<>(data);
  }
}
