package org.github.spring.restful.xml;

import java.io.IOException;
import java.io.OutputStream;

import org.github.spring.footstone.AbstractEntity;
import org.github.spring.footstone.XMLMapperHolder;
import org.github.spring.restful.XMLReturn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.NonNull;

/**
 * XMLReturn of response.
 * <p>
 * <pre>
 *   return XMLResponse.of();
 * </pre>
 *
 * @param <T> type
 * @author JYD_XL
 * @see java.io.Serializable
 * @see java.util.function.Supplier
 * @see org.github.spring.footstone.ConstInterface
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.XMLReturn
 */
@SuppressWarnings("serial")
@JacksonXmlRootElement(localName = "root")
public class XMLResponse<T> extends AbstractEntity implements XMLReturn {
  /** data. */
  private T data;

  /** Constructor. */
  public XMLResponse() {}

  /**
   * Constructor.
   *
   * @param data T
   */
  public XMLResponse(T data) {
    this.data = data;
  }

  @Override
  public boolean functional() {
    return false;
  }

  @Override
  public void accept(@NonNull OutputStream outputStream) throws IOException {
    this.createMapper().writeValueWithIOE(outputStream, this);
  }

  @Override
  public String get() {
    return this.createMapper().toXMLString(this);
  }

  /**
   * GET data.
   *
   * @return data
   */
  public T getData() {
    return data;
  }

  /**
   * SET data.
   *
   * @param data T
   */
  public void setData(T data) {
    this.data = data;
  }

  private XMLMapperHolder createMapper() {
    return XMLMapperHolder.getWebXMLMapper();
  }
}
