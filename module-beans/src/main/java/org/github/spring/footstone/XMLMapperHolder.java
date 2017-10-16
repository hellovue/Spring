package org.github.spring.footstone;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.TimeZone;

import lombok.Getter;
import lombok.NonNull;

import org.github.spring.exception.HandlingException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_INVALID_SUBTYPE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS;
import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

/**
 * XMLMapperHolder.
 *
 * @author JYD_XL
 * @see com.fasterxml.jackson.dataformat.xml.XmlMapper
 * @since 0.0.1-SNAPSHOT
 */
@Getter
public final class XMLMapperHolder extends XmlMapper {
  /** Constructor. */
  private XMLMapperHolder() {}

  /** XMLString -> Collection. */
  public <T> Collection<T> toCollection(@NonNull String xml, @NonNull Class<? extends Collection<T>> collection, Class<?>... clazz) throws UnsupportedOperationException, HandlingException {
    try {
      return this.readValue(xml, this.getTypeFactory().constructParametricType(collection, clazz));
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /** XMLString -> JAVABean. */
  public <T> T toJAVABean(@NonNull String xml, @NonNull Class<T> clazz) throws UnsupportedOperationException, HandlingException {
    try {
      return this.readValue(xml, clazz);
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /** JAVABean -> XMLString. */
  public String toXMLString(@NonNull Object bean) throws UnsupportedOperationException, HandlingException {
    try {
      return this.writeValueAsString(bean);
    } catch (JsonProcessingException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /** GET instance. */
  public static XMLMapperHolder getMobileXMLMapper() {
    return Holder._mobile_xml_instance;
  }

  /** GET instance. */
  public static XMLMapperHolder getWebXMLMapper() {
    return Holder._web_xml_instance;
  }

  /** GET instance. */
  public static XMLMapperHolder getXMLMapper() {
    return Holder._xml_instance;
  }

  /**
   * Holder.
   *
   * @author JYD_XL
   * @since 0.0.1-SNAPSHOT
   */
  private static class Holder {
    /** Constructor. */
    private Holder() {}

    /** instance. */
    private static final XMLMapperHolder _web_xml_instance = new XMLMapperHolder();
    /** instance. */
    private static final XMLMapperHolder _mobile_xml_instance = new XMLMapperHolder();
    /** instance. */
    private static final XMLMapperHolder _xml_instance = new XMLMapperHolder();

    static {
      _web_xml_instance
        .setDateFormat(ThreadLocal.withInitial(() -> new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT)).get())
        .setTimeZone(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE))
        .setSerializationInclusion(Include.NON_NULL)
        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(FAIL_ON_INVALID_SUBTYPE)
        .disable(FAIL_ON_UNRESOLVED_OBJECT_IDS)
        .disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)
        .disable(FAIL_ON_UNRESOLVED_OBJECT_IDS)
        .disable(FAIL_ON_EMPTY_BEANS)
        .enable(SORT_PROPERTIES_ALPHABETICALLY);
    }
  }
}