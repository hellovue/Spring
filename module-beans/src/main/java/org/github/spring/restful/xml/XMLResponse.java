package org.github.spring.restful.xml;

import java.io.IOException;
import java.io.OutputStream;

import lombok.NonNull;

import org.github.spring.footstone.AbstractEntity;
import org.github.spring.restful.XML;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * XML_HOLDER of response.
 *
 * <pre>
 *   return XMLResponse.of();
 * </pre>
 *
 * @param <T> type
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.XML
 * @see org.github.spring.footstone.AbstractEntity
 */
@JacksonXmlRootElement(localName = "root")
@SuppressWarnings("serial")
public class XMLResponse<T> extends AbstractEntity implements XML {
  /** data. */
  private transient T data;

  /** Constructor. */
  public XMLResponse() {}

  /** Constructor. */
  public XMLResponse(T data) {
    this.data = data;
  }

  @Override
  public void accept(@NonNull OutputStream output) throws IOException {
    XML_HOLDER.writeValue(output, this);
  }

  @Override
  public boolean functional() {
    return false;
  }

  @Override
  public String get() {
    return XML_HOLDER.toXMLString(this);
  }

  /** GET data. */
  public T getData() {
    return data;
  }

  /** SET data. */
  public void setData(T data) {
    this.data = data;
  }

  /** WITH data. */
  public XMLResponse withData(T data) {
    this.setData(data);
    return this;
  }

  /** Generator. */
  public static XMLResponse of() {
    return new XMLResponse();
  }

  /** Generator. */
  public static <T> XMLResponse<T> of(T data) {
    return new XMLResponse<>(data);
  }

  /** Generator. */
  @SafeVarargs
  public static <T> XMLResponse<T[]> of(T... data) {
    return new XMLResponse<>(data);
  }
}