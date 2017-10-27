package org.github.spring.restful.json;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import lombok.NonNull;

import org.github.spring.annotation.PrimaryMethod;
import org.github.spring.restful.JSON;
import org.github.spring.util.BeansUtil;

import com.github.pagehelper.Page;

/**
 * JSON_HOLDER of page.
 *
 * <pre>
 *   return JSONPage.of();
 * </pre>
 *
 * @param <E> element
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSON
 * @see org.github.spring.footstone.AbstractEntity
 * @see JSONBasic
 * @see JSONArray
 */
@SuppressWarnings("serial")
public class JSONPage<E> extends JSONArray<E> implements JSON {
  /** total. */
  private long total = COUNT;

  /** Constructor. */
  public JSONPage() {}

  /** Constructor. */
  @SafeVarargs
  public JSONPage(long total, E... data) {
    this.withTotal(total).withData(data);
  }

  /** Constructor. */
  public JSONPage(long total, Collection<? extends E> data) {
    this.withTotal(total).withData(data);
  }

  /** Constructor. */
  public JSONPage(long total, Stream<? extends E> data) {
    this.withTotal(total).withData(data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  public JSONPage(@NonNull List<? extends E> page) {
    this((Page<? extends E>) page);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  public JSONPage(@NonNull List<? super E> page, @NonNull Class<? extends E> data) {
    this((Page<? super E>) page, data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  public JSONPage(@NonNull List<? super E> page, E... data) {
    this((Page<? super E>) page, data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  public JSONPage(@NonNull List<? super E> page, Stream<? extends E> data) {
    this((Page<? super E>) page, data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  public JSONPage(@NonNull List<? super E> page, Collection<? extends E> data) {
    this((Page<? super E>) page, data);
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  private JSONPage(@NonNull Page<? extends E> page) {
    this(page.getTotal(), (E[]) page.toArray());
  }

  /** Constructor. */
  @SuppressWarnings("unchecked")
  private JSONPage(@NonNull Page<? super E> page, @NonNull Class<? extends E> data) {
    this(page.getTotal(), (E[]) BeansUtil.copyOfArray(page, data));
  }

  /** Constructor. */
  @SafeVarargs
  private JSONPage(@NonNull Page<? super E> page, E... data) {
    this(page.getTotal(), data);
  }

  /** Constructor. */
  private JSONPage(@NonNull Page<? super E> page, Stream<? extends E> data) {
    this(page.getTotal(), data);
  }

  /** Constructor. */
  private JSONPage(@NonNull Page<? super E> page, Collection<? extends E> data) {
    this(page.getTotal(), data);
  }

  @Override
  public void release() {
    total = COUNT;
    super.release();
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
  public JSONPage<E> withTotal(long total) {
    this.setTotal(total);
    return this;
  }

  /** Generator. */
  public static JSONPage of() {
    return new JSONPage();
  }

  /** Generator. */
  @PrimaryMethod
  public static <V> JSONPage<V> of(@NonNull List<? extends V> page) {
    return new JSONPage<>(page);
  }

  /** Generator. */
  @PrimaryMethod
  public static <V> JSONPage<V> of(@NonNull List<? super V> page, @NonNull Class<? extends V> data) {
    return new JSONPage<>(page, data);
  }

  /** Generator. */
  @SafeVarargs
  public static <V> JSONPage<V> of(@NonNull List<? super V> page, V... data) {
    return new JSONPage<>(page, data);
  }

  /** Generator. */
  public static <V> JSONPage<V> of(@NonNull List<? super V> page, Stream<? extends V> data) {
    return new JSONPage<>(page, data);
  }

  /** Generator. */
  public static <V> JSONPage<V> of(@NonNull List<? super V> page, Collection<? extends V> data) {
    return new JSONPage<>(page, data);
  }
}