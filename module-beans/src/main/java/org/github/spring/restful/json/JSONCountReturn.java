package org.github.spring.restful.json;

import lombok.NonNull;

import org.github.spring.restful.JSONReturn;

/**
 * JSONReturn of count.
 *
 * <pre>
 *   return JSONCountReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSONReturn
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.github.spring.restful.json.JSONBasicReturn
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public class JSONCountReturn extends JSONBasicReturn implements JSONReturn {
  /** data. */
  private transient long data = COUNT;

  /** Constructor. */
  public JSONCountReturn() {}

  /** Constructor. */
  public JSONCountReturn(long data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCountReturn(int data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCountReturn(byte data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCountReturn(short data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCountReturn(@NonNull Long data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCountReturn(@NonNull Integer data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCountReturn(@NonNull Byte data) {
    this.withData(data);
  }

  /** Constructor. */
  public JSONCountReturn(@NonNull Short data) {
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
  public JSONCountReturn withData(long data) {
    this.setData(data);
    return this;
  }

  /** Generator. */
  public static JSONCountReturn of() {
    return new JSONCountReturn();
  }

  /** Generator. */
  public static JSONCountReturn of(long data) {
    return new JSONCountReturn(data);
  }

  /** Generator. */
  public static JSONCountReturn of(int data) {
    return new JSONCountReturn(data);
  }

  /** Generator. */
  public static JSONCountReturn of(byte data) {
    return new JSONCountReturn(data);
  }

  /** Generator. */
  public static JSONCountReturn of(short data) {
    return new JSONCountReturn(data);
  }

  /** Generator. */
  public static JSONCountReturn of(@NonNull Long data) {
    return new JSONCountReturn(data);
  }

  /** Generator. */
  public static JSONCountReturn of(@NonNull Integer data) {
    return new JSONCountReturn(data);
  }

  /** Generator. */
  public static JSONCountReturn of(@NonNull Short data) {
    return new JSONCountReturn(data);
  }

  /** Generator. */
  public static JSONCountReturn of(@NonNull Byte data) {
    return new JSONCountReturn(data);
  }
}