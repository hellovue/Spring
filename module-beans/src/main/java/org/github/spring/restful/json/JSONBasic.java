package org.github.spring.restful.json;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;

import org.github.spring.footstone.AbstractEntity;
import org.github.spring.restful.JSON;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JSON_HOLDER of basic.
 *
 * <pre>
 *   return JSONBasic.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSON
 * @see org.github.spring.footstone.AbstractEntity
 */
@SuppressWarnings("serial")
public class JSONBasic extends AbstractEntity implements JSON {
  /** 返回的状态码. */
  private int retCode = RET_OK_CODE;

  /** 返回的信息. */
  private String retMsg = RET_OK_MSG;

  /** HTTP状态码. */
  @JsonIgnore
  private int status = HttpServletResponse.SC_OK;

  /** Constructor. */
  public JSONBasic() {}

  /** Constructor. */
  public JSONBasic(int retCode, @NonNull String retMsg) {
    this.withRetCode(retCode).withRetMsg(retMsg);
  }

  /** Constructor. */
  public JSONBasic(int status, int retCode, @NonNull String retMsg) {
    this.withStatus(status).withRetCode(retCode).withRetMsg(retMsg);
  }

  @Override
  public void accept(@NonNull OutputStream output) throws IOException {
    JSON_HOLDER.writeValue(output, this);
  }

  @Override
  public void collect(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws IOException {
    if (HttpServletResponse.SC_OK != status) response.sendError(status, retMsg);
    JSON.super.collect(request, response);
  }

  @Override
  public boolean functional() {
    return false;
  }

  @Override
  public void release() {
    this.withStatus(HttpServletResponse.SC_OK).withRetCode(RET_OK_CODE).withRetMsg(RET_OK_MSG);
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
    this.retMsg = retMsg;
  }

  /** GET status. */
  public int getStatus() {
    return status;
  }

  /** SET status. */
  public void setStatus(int status) {
    this.status = status;
  }

  /** GET version. */
  public String getVersion() {
    return System.getProperty(VERSION, UNKNOWN);
  }

  /** WITH retCode. */
  public JSONBasic withRetCode(int retCode) {
    this.setRetCode(retCode);
    return this;
  }

  /** WITH retMsg. */
  public JSONBasic withRetMsg(@NonNull String retMsg) {
    this.setRetMsg(retMsg);
    return this;
  }

  /** WITH status. */
  public JSONBasic withStatus(int status) {
    this.setStatus(status);
    return this;
  }

  /** Generator. */
  public static JSONBasic of() {
    return new JSONBasic();
  }

  /** Generator. */
  public static JSONBasic of(int code, @NonNull String msg) {
    return new JSONBasic(code, msg);
  }

  /** Generator. */
  public static JSONBasic of(int status, int code, @NonNull String msg) {
    return new JSONBasic(status, code, msg);
  }

  /** Generator. */
  public static JSONBasic error() {
    return of(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, RET_ERROR_CODE, RET_ERROR_MSG);
  }

  /** Generator. */
  public static JSONBasic errorOfNullParams(String... param) {
    return of(HttpServletResponse.SC_BAD_REQUEST, RET_ERROR_CODE, JOINER.join("[", param, "],", "上述参数不能为空..."));
  }
}