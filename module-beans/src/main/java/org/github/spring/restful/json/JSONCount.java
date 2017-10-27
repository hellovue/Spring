package org.github.spring.restful.json;

import lombok.NonNull;

/**
 * JSON of count.
 *
 * <pre>
 *   return JSONCount.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSON
 * @see org.github.spring.footstone.AbstractEntity
 * @see JSONBasic
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public class JSONCount extends JSONBasic implements org.github.spring.restful.JSON {
  /** data. */
  private transient long data = COUNT;

  /** Constructor. */
  public JSONCount() {}

  /** Constructor. */
  public JSONCount(long data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCount(int data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCount(byte data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCount(short data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCount(@NonNull Long data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCount(@NonNull Integer data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCount(@NonNull Byte data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCount(@NonNull Short data) {
    this.withData(data);
  }

  @Override
  public void release() {
    data = COUNT;
    super.release();
  }

  /** GET data. */
  public long getData() {
    return data;
  }

  /** SET data. */
  public void setData(long data) {
    this.data = data;
  }

  /** WITH data. */
  public JSONCount withData(long data) {
    this.setData(data);
    return this;
  }

  /** Generator. */
  public static JSONCount of() {
    return new JSONCount();
  }

  /** Generator. */
  public static JSONCount of(long data) {
    return new JSONCount(data);
  }

  /** Generator. */
  public static JSONCount of(int data) {
    return new JSONCount(data);
  }

  /** Generator. */
  public static JSONCount of(byte data) {
    return new JSONCount(data);
  }

  /** Generator. */
  public static JSONCount of(short data) {
    return new JSONCount(data);
  }

  /** Generator. */
  public static JSONCount of(@NonNull Long data) {
    return new JSONCount(data);
  }

  /** Generator. */
  public static JSONCount of(@NonNull Integer data) {
    return new JSONCount(data);
  }

  /** Generator. */
  public static JSONCount of(@NonNull Short data) {
    return new JSONCount(data);
  }

  /** Generator. */
  public static JSONCount of(@NonNull Byte data) {
    return new JSONCount(data);
  }
}