package org.github.spring.restful.json;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import lombok.NonNull;

import org.github.spring.annotation.PrimaryMethod;
import org.github.spring.restful.JSONReturn;

import com.github.pagehelper.Page;

/**
 * JSONReturn of PAGE.
 * <p>
 * <pre>
 *   return JSONPageReturn.of();
 * </pre>
 *
 * @param <E> element
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSONReturn
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.github.spring.restful.json.JSONBasicReturn
 * @see org.github.spring.restful.json.JSONArrayReturn
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class JSONPageReturn<E> extends JSONArrayReturn<E> implements JSONReturn {
  /** total. */
  private long total = COUNT;

  /** Constructor. */
  public JSONPageReturn() {}

  /** Constructor. */
  public JSONPageReturn(long total, Collection<? extends E> data) {
    this.withTotal(total).withData(data);
  }

  /** Constructor. */
  @SafeVarargs
  public JSONPageReturn(long total, E... data) {
    this.withTotal(total).withData(data);
  }

  /** Constructor. */
  public JSONPageReturn(long total, Stream<? extends E> data) {
    this.withTotal(total).withData(data);
  }

  /** Constructor. */
  @PrimaryMethod
  @SuppressWarnings("unchecked")
  public JSONPageReturn(@NonNull List<? extends E> page) {
    this((Page<? extends E>) page);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  public JSONPageReturn(@NonNull List<? super E> page, Collection<? extends E> data) {
    this((Page<? super E>) page, data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  public JSONPageReturn(@NonNull List<? super E> page, E... data) {
    this((Page<? super E>) page, data);
  }

  /** Constructor. */
  @PrimaryMethod
  @SuppressWarnings("unchecked")
  public JSONPageReturn(@NonNull List<? super E> page, @NonNull Class<? extends E> data) {
    this((Page<? super E>) page, data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  public JSONPageReturn(@NonNull List<? super E> page, Stream<? extends E> data) {
    this((Page<? super E>) page, data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  private JSONPageReturn(@NonNull Page<? extends E> page) {
    this(page.getTotal(), (E[]) page.toArray());
  }

  /** Constructor. */
  private JSONPageReturn(@NonNull Page<? super E> page, Collection<? extends E> data) {
    this(page.getTotal(), data);
  }

  /** Constructor. */
  @SafeVarargs
  private JSONPageReturn(@NonNull Page<? super E> page, E... data) {
    this(page.getTotal(), data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  private JSONPageReturn(@NonNull Page<? super E> page, @NonNull Class<? extends E> data) {
    this(page.getTotal(), (E[]) copyOfArray(page, data));
  }

  /** Constructor. */
  private JSONPageReturn(@NonNull Page<? super E> page, Stream<? extends E> data) {
    this(page.getTotal(), data);
  }

  @Override
  public void release() {
    total = COUNT;
    super.release();
  }

  @Override
  public JSONPageReturn clone() {
    return (JSONPageReturn) super.clone();
  }

  /** GET total. */
  public long getTotal() {
    return total;
  }

  /** SET total. */
  public void setTotal(long total) {
    this.total = total;
  }

  /** WITH total. */
  public JSONPageReturn<E> withTotal(long total) {
    this.setTotal(total);
    return this;
  }

  /** Generator. */
  @PrimaryMethod
  public static <V> JSONPageReturn<V> of(@NonNull List<? extends V> page) {
    return new JSONPageReturn<>(page);
  }

  /** Generator. */
  public static <V> JSONPageReturn<V> of(@NonNull List<? super V> page, Collection<? extends V> data) {
    return new JSONPageReturn<>(page, data);
  }

  /** Generator. */
  @SafeVarargs
  public static <V> JSONPageReturn<V> of(@NonNull List<? super V> page, V... data) {
    return new JSONPageReturn<>(page, data);
  }

  /** Generator. */
  @PrimaryMethod
  public static <V> JSONPageReturn<V> of(@NonNull List<? super V> page, @NonNull Class<? extends V> data) {
    return new JSONPageReturn<>(page, data);
  }

  /** Generator. */
  public static <V> JSONPageReturn<V> of(@NonNull List<? super V> page, Stream<? extends V> data) {
    return new JSONPageReturn<>(page, data);
  }

  /** Generator. */
  public static JSONPageReturn of() {
    return new JSONPageReturn();
  }
}
