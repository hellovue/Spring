package org.github.spring.restful.json;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.spring.exception.HandlingException;
import org.github.spring.footstone.AbstractEntity;
import org.github.spring.footstone.JSONMapperHolder;
import org.github.spring.restful.JSONReturn;
import org.github.spring.util.StringUtil;
import org.springframework.beans.BeanUtils;

import lombok.NonNull;
import lombok.val;

/**
 * JSONReturn of basic.
 * <p>
 * <pre>
 *   return JSONBasicReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSONReturn
 * @see org.github.spring.footstone.AbstractEntity
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class JSONBasicReturn extends AbstractEntity implements JSONReturn {
  /** result code. */
  private int retCode = RET_OK_CODE;

  /** result message. */
  private String retMsg = RET_OK_MSG;

  /** Constructor. */
  public JSONBasicReturn() {}

  /** Constructor. */
  public JSONBasicReturn(int retCode, @NonNull String retMsg) {
    this.withRetCode(retCode).withRetMsg(retMsg);
  }

  @Override
  public void release() {
    this.withRetCode(RET_OK_CODE).withRetMsg(RET_OK_MSG);
  }

  @Override
  public boolean functional() {
    return false;
  }

  @Override
  public void accept(@NonNull OutputStream output) throws IOException {
    JSONMapperHolder.getWebJSONMapper().writeValue(output, this);
  }

  @Override
  public void collect(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws IOException {
    response.setStatus(retCode);
    JSONReturn.super.collect(request, response);
  }

  @Override
  public String get() {
    return this.toString();
  }

  /** GET retCode. */
  public int getRetCode() {
    return retCode;
  }

  /** SET retCode. */
  public void setRetCode(int retCode) {
    this.retCode = retCode;
  }

  /** GET retMsg. */
  public String getRetMsg() {
    return retMsg;
  }

  /** SET retMsg. */
  public void setRetMsg(@NonNull String retMsg) {
    Optional.of(retMsg).filter(StringUtil::isNotBlank).ifPresent(v -> this.retMsg = v);
  }

  /** WITH retMsg. */
  public JSONBasicReturn withRetMsg(@NonNull String retMsg) {
    this.setRetMsg(retMsg);
    return this;
  }

  /** WITH retCode. */
  public JSONBasicReturn withRetCode(int retCode) {
    this.setRetCode(retCode);
    return this;
  }

  /** Generator. */
  public static JSONBasicReturn of() {
    return new JSONBasicReturn();
  }

  /** Generator. */
  public static JSONBasicReturn error(@NonNull String errorMsg) {
    return of(RET_ERROR_CODE, errorMsg);
  }

  /** Generator. */
  public static JSONBasicReturn of(int code, @NonNull String msg) {
    return new JSONBasicReturn(code, msg);
  }

  /** Generator. */
  public static JSONBasicReturn error() {
    return of(RET_ERROR_CODE, RET_ERROR_MSG);
  }

  /** Generator. */
  public static JSONBasicReturn errorOfNullParam(@NonNull String param) {
    return of(RET_ERROR_CODE, JOINER.join("[", param, "],", "the above parameters cannot be null..."));
  }

  /** COPY data. */
  @SuppressWarnings("unchecked")
  public static <T> T[] copy(@NonNull T[] source, @NonNull Class<? extends T> target) {
    return (T[]) Stream.of(source).parallel().map(each -> copy(each, target)).toArray();
  }

  /** COPY data. */
  public static <T> T copy(@NonNull T source, @NonNull Class<? extends T> target) {
    try {
      val instance = target.newInstance();
      BeanUtils.copyProperties(source, instance);
      return instance;
    } catch (Exception e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /** COPY data. */
  public static <T> List<T> copy(@NonNull List<T> source, @NonNull Class<? extends T> target) {
    return source.parallelStream().map(each -> copy(each, target)).collect(Collectors.toList());
  }

  /** COPY data. */
  @SuppressWarnings("unchecked")
  public static <T> T[] copyOfArray(@NonNull List<T> source, @NonNull Class<? extends T> target) {
    return (T[]) source.parallelStream().map(each -> copy(each, target)).toArray();
  }

  /** COPY data. */
  @SuppressWarnings("unchecked")
  public static <T> T[] copyOfArray(@NonNull Stream<T> source, @NonNull Class<? extends T> target) {
    return (T[]) source.parallel().map(each -> copy(each, target)).toArray();
  }

  /** COPY data. */
  public static <T> List<T> copyOfList(@NonNull T[] source, @NonNull Class<? extends T> target) {
    return Stream.of(source).parallel().map(each -> copy(each, target)).collect(Collectors.toList());
  }

  /** COPY data. */
  public static <T> List<T> copyOfList(@NonNull Stream<T> source, @NonNull Class<? extends T> target) {
    return source.parallel().map(each -> copy(each, target)).collect(Collectors.toList());
  }
}
