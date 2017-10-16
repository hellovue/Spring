package org.github.spring.restful.json;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;

import org.github.spring.footstone.AbstractEntity;
import org.github.spring.restful.JSONReturn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * JSONReturn of basic.
 *
 * <pre>
 *   return JSONBasicReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSONReturn
 * @see org.github.spring.footstone.AbstractEntity
 * @since 0.0.1-SNAPSHOT
 */
@JsonIgnoreProperties("status")
@SuppressWarnings("serial")
public class JSONBasicReturn extends AbstractEntity implements JSONReturn {
  /** 当前系统版本. */
  private final String version = System.getProperty(VERSION, UNKNOWN);

  /** 返回的状态码. */
  private int retCode = RET_OK_CODE;

  /** 返回的信息. */
  private String retMsg = RET_OK_MSG;

  /** HTTP状态码. */
  private int status = HttpServletResponse.SC_OK;

  /** Constructor. */
  public JSONBasicReturn() {}

  /** Constructor. */
  public JSONBasicReturn(int retCode, @NonNull String retMsg) {
    this.withRetCode(retCode).withRetMsg(retMsg);
  }

  /** Constructor. */
  public JSONBasicReturn(int status, int retCode, @NonNull String retMsg) {
    this.withStatus(status).withRetCode(retCode).withRetMsg(retMsg);
  }

  @Override
  public void accept(@NonNull OutputStream output) throws IOException {
    JSON.writeValue(output, this);
  }

  @Override
  public void collect(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws IOException {
    if (HttpServletResponse.SC_OK != status) response.sendError(status, retMsg);
    JSONReturn.super.collect(request, response);
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
  public JSONBasicReturn clone() {
    return (JSONBasicReturn) super.clone();
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
    return version;
  }

  /** WITH retCode. */
  public JSONBasicReturn withRetCode(int retCode) {
    this.setRetCode(retCode);
    return this;
  }

  /** WITH retMsg. */
  public JSONBasicReturn withRetMsg(@NonNull String retMsg) {
    this.setRetMsg(retMsg);
    return this;
  }

  /** WITH status. */
  public JSONBasicReturn withStatus(int status) {
    this.setStatus(status);
    return this;
  }

  /** Generator. */
  public static JSONBasicReturn of() {
    return new JSONBasicReturn();
  }

  /** Generator. */
  public static JSONBasicReturn of(int code, @NonNull String msg) {
    return new JSONBasicReturn(code, msg);
  }

  /** Generator. */
  public static JSONBasicReturn of(int status, int code, @NonNull String msg) {
    return new JSONBasicReturn(status, code, msg);
  }

  /** Generator. */
  public static JSONBasicReturn error() {
    return of(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, RET_ERROR_CODE, RET_ERROR_MSG);
  }

  /** Generator. */
  public static JSONBasicReturn errorOfNullParams(String... param) {
    return of(HttpServletResponse.SC_BAD_REQUEST, RET_ERROR_CODE, JOINER.join("[", param, "],", "上述参数不能为空..."));
  }
}