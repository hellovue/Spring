package org.github.spring.restful;

import lombok.NonNull;

import org.github.spring.enumeration.ContentType;

/**
 * XML返回类型顶层接口.
 *
 * <pre>
 *   return XMLReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 0.0.1-SNAPSHOT
 */
@FunctionalInterface
public interface XMLReturn extends Returnable {
  @Override
  default ContentType contentType() {
    return ContentType.XML;
  }

  /** Generator. */
  static XMLReturn of(@NonNull XMLReturn xml) {
    return xml;
  }

  /** Generator. */
  static XMLReturn of(@NonNull String xml) {
    return of(xml::toString);
  }

  /** Generator. */
  static XMLReturn of(@NonNull Object xml) {
    return of(XML.toXMLString(xml));
  }

  /** Generator. */
  static XMLReturn of() {
    return of(EMPTY_XML);
  }
}