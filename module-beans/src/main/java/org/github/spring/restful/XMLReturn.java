package org.github.spring.restful;

import org.github.spring.enumeration.ContentType;

import lombok.NonNull;

import static org.github.spring.enumeration.ContentType.XML;

/**
 * Top interface of xml.
 *
 * <pre>
 *   return XMLReturn.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 1.0.0GA
 */
@FunctionalInterface
public interface XMLReturn extends Returnable {
  @Override
  default ContentType returnType() {
    return XML;
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
  static XMLReturn of() {
    return of(EMPTY_XML);
  }
}
