package org.github.spring.restful;

import org.github.spring.enumeration.ContentType;

import lombok.NonNull;

/**
 * XML返回类型顶层接口.
 *
 * <pre>
 *   return XML.of();
 * </pre>
 *
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @since 0.0.1-SNAPSHOT
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
        return of(XML.toXMLString(xml));
    }

    /** Generator. */
    static XML of() {
        return of(EMPTY_XML);
    }
}