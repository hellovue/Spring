package org.github.spring.restful;

import lombok.NonNull;

import org.github.spring.enumeration.ContentType;

/**
 * XML返回类型顶层接口.
 *
 * <pre>
 *   return XML_HOLDER.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 */
@FunctionalInterface
public interface XML extends Returnable {
  @Override
  default ContentType contentType() {
    return ContentType.XML;
  }

  /** Generator. */
  static XML of(@NonNull XML xml) {
    return xml;
  }

  /** Generator. */
  static XML of(@NonNull String xml) {
    return of(xml::toString);
  }

  /** Generator. */
  static XML of(@NonNull Object xml) {
    return of(XML_HOLDER.toXMLString(xml));
  }

  /** Generator. */
  static XML of() {
    return of(EMPTY_XML);
  }
}