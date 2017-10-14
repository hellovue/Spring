package org.github.spring.footstone;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Supplier;

import org.github.spring.exception.HandlingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.Getter;
import lombok.NonNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_INVALID_SUBTYPE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS;
import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

/**
 * Created by JYD_XL on 2017/7/17.
 */
@Getter
public final class XMLMapperHolder extends XmlMapper implements Constants {
  /** jackson mapper. */
  private final ObjectMapper _mapper;

  /** Constructor. */
  public XMLMapperHolder(@NonNull ObjectMapper objectMapper) {
    this._mapper = objectMapper;
  }
  
  /** Generator. */
  public static XMLMapperHolder of(@NonNull Supplier<ObjectMapper> supplier) {
    return new XMLMapperHolder(supplier.get());
  }
  
  /**
   * GET instance.
   *
   * @return JSONMapper
   */
  public static XMLMapperHolder getMobileJSONMapper() {
    return Holder._mobile_json_instance;
  }
  
  /**
   * GET instance.
   *
   * @return JSONMapper
   */
  public static XMLMapperHolder getWebJSONMapper() {
    return Holder._web_json_instance;
  }
  
  /**
   * GET instance.
   *
   * @return JSONMapper
   */
  public static XMLMapperHolder getJSONMapper() {
    return Holder._json_instance;
  }
  
  /**
   * GET instance.
   *
   * @return XMLMapper
   */
  public static XMLMapperHolder getMobileXMLMapper() {
    return Holder._mobile_xml_instance;
  }
  
  /**
   * GET instance.
   *
   * @return XMLMapper
   */
  public static XMLMapperHolder getWebXMLMapper() {
    return Holder._web_xml_instance;
  }
  
  /**
   * GET instance.
   *
   * @return XMLMapper
   */
  public static XMLMapperHolder getXMLMapper() {
    return Holder._xml_instance;
  }
  
  /**
   * JSONString->JAVABean.
   *
   * @param json  JSONString
   * @param clazz class
   *
   * @return JAVABean
   */
  public <T> T jsonToJAVABean(@NonNull String json, @NonNull Class<T> clazz) throws UnsupportedOperationException, HandlingException {
    try {
      return this.takeJSONMapper().readValue(json, clazz);
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }
  
  /**
   * XMLString->JAVABean.
   *
   * @param xml   String
   * @param clazz class
   *
   * @return JAVABean
   */
  public <T> T xmlToJAVABean(@NonNull String xml, @NonNull Class<T> clazz) throws UnsupportedOperationException, HandlingException {
    try {
      return this.takeXMLMapper().readValue(xml, clazz);
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }
  
  /**
   * JSONString->JAVABeanCollection.
   *
   * @param json  JSONString
   * @param clazz class...
   *
   * @return Collection
   */
  public <T> Collection<T> jsonToCollection(@NonNull String json, @NonNull Class<? extends Collection<T>> collection, Class<?>... clazz) throws UnsupportedOperationException, HandlingException {
    try {
      return this.takeJSONMapper().readValue(json, _mapper.getTypeFactory().constructParametricType(collection, clazz));
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }
  
  /**
   * XMLString->JAVABeanCollection.
   *
   * @param xml   String
   * @param clazz class...
   *
   * @return Collection
   */
  public <T> Collection<T> xmlToCollection(@NonNull String xml, @NonNull Class<? extends Collection<T>> collection, Class<?>... clazz) throws UnsupportedOperationException, HandlingException {
    try {
      return this.takeXMLMapper().readValue(xml, _mapper.getTypeFactory().constructParametricType(collection, clazz));
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }
  
  /**
   * JAVABean->JSONString.
   *
   * @param bean JAVABean
   *
   * @return JSONString
   */
  public String toJSONString(@NonNull Object bean) throws UnsupportedOperationException, HandlingException {
    try {
      return this.takeJSONMapper().writeValueAsString(bean);
    } catch (JsonProcessingException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }
  
  /**
   * JAVABean->XMLString.
   *
   * @param bean JAVABean
   *
   * @return String
   */
  public String toXMLString(@NonNull Object bean) throws UnsupportedOperationException, HandlingException {
    try {
      return this.takeXMLMapper().writeValueAsString(bean);
    } catch (JsonProcessingException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }
  
  public void writeValue(@NonNull OutputStream outputStream, @NonNull Object bean) throws HandlingException {
    try {
      _mapper.writeValue(outputStream, bean);
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }
  
  public void writeValueWithIOE(@NonNull OutputStream outputStream, @NonNull Object bean) throws IOException {
    _mapper.writeValue(outputStream, bean);
  }
  
  private ObjectMapper takeJSONMapper() {
    return Optional.of(_mapper).filter(v -> ! (v instanceof XmlMapper)).orElseThrow(UnsupportedOperationException::new);
  }
  
  private ObjectMapper takeXMLMapper() {
    return Optional.of(_mapper).filter(v -> v instanceof XmlMapper).orElseThrow(UnsupportedOperationException::new);
  }
  
  /**
   * static inner class of holder.
   *
   * @author JYD_XL
   */
  private static class Holder {
    /** instance. */
    private static final XMLMapperHolder _web_xml_instance = XMLMapperHolder.of(XmlMapper::new);
    /** instance. */
    private static final XMLMapperHolder _web_json_instance = XMLMapperHolder.of(ObjectMapper::new);
    /** instance. */
    private static final XMLMapperHolder _mobile_xml_instance = XMLMapperHolder.of(XmlMapper::new);
    /** instance. */
    private static final XMLMapperHolder _mobile_json_instance = XMLMapperHolder.of(ObjectMapper::new);
    /** instance. */
    private static final XMLMapperHolder _xml_instance = XMLMapperHolder.of(XmlMapper::new);
    /** instance. */
    private static final XMLMapperHolder _json_instance = XMLMapperHolder.of(ObjectMapper::new);
    
    static {
      _web_json_instance
        ._mapper
        .setDateFormat(ThreadLocal.withInitial(NaiveDateFormat::new).get())
        .setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE))
        .setSerializationInclusion(NON_NULL)
        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(FAIL_ON_INVALID_SUBTYPE)
        .disable(FAIL_ON_UNRESOLVED_OBJECT_IDS)
        .disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)
        .disable(FAIL_ON_UNRESOLVED_OBJECT_IDS)
        .disable(FAIL_ON_EMPTY_BEANS)
        .enable(SORT_PROPERTIES_ALPHABETICALLY);
    }
    
    /** Constructor. */
    private Holder() {}
  }
}
