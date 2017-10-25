package org.github.spring.restful;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;

import org.github.spring.enumeration.ContentType;
import org.github.spring.footstone.Constants;

/**
 * 所有可返回类型顶层接口.
 *
 * 1.该接口的核心方法为 {@link Supplier#get}.
 * 2.该接口默认返回字符串 {@code text/plain; charset=UTF-8}.
 * 3.该接口为函数接口 {@link FunctionalInterface}, 推荐使用lambda表达式.
 *
 * <pre>
 *     return Returnable.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @since 0.0.1-SNAPSHOT
 */
@FunctionalInterface
public interface Returnable extends Constants, Serializable, Supplier<String> {
  /** 通过字符流 {@link Writer} 消费数据. */
  default void accept(@NonNull Writer writer) throws IOException {
    writer.write(this.get());
  }

  /** 通过字节流 {@link OutputStream} 消费数据. */
  default void accept(@NonNull OutputStream output) throws IOException {
    throw new UnsupportedOperationException();
  }

  /** 通过请求 {@link HttpServletRequest} 和响应 {@link HttpServletResponse} 处理数据. */
  default void collect(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws IOException {
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.setContentType(this.contentType().get());
    if (this.functional()) {
      this.accept(response.getWriter());
    } else {
      this.accept(response.getOutputStream());
    }
  }

  /** 获取返回类型. */
  default ContentType contentType() {
    return ContentType.TEXT;
  }

  /** 是否直接通过函数接口 {@link Supplier#get} 传递数据. */
  default boolean functional() {
    return true;
  }

  /** 清空数据. */
  default void release() {
    // 接口没有属性,直接通过函数接口传递的数据无法保存,因而无法执行清理操作,此处的默认实现为空...
  }

  /** 请求是否已被完全处理, 未被完全处理的请求将继续被Spring处理. */
  default boolean terminal() {
    return true;
  }

  /** Generator. */
  static Returnable of(@NonNull Object data) {
    return of(data.toString());
  }

  /** Generator. */
  static Returnable of(@NonNull String data) {
    return of(data::toString);
  }

  /** Generator. */
  static Returnable of(@NonNull Returnable data) {
    return data;
  }

  /** Generator. */
  static Returnable of() {
    return of(EMPTY);
  }
}