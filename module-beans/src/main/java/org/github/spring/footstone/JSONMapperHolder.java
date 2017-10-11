package org.github.spring.footstone;

import java.io.IOException;
import java.util.Collection;
import java.util.TimeZone;

import org.github.spring.exception.HandlingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_INVALID_SUBTYPE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS;
import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.github.spring.footstone.Constants.DEFAULT_TIME_ZONE;

public final class JSONMapperHolder extends ObjectMapper {
  /** Constructor. */
  public JSONMapperHolder() {}

  /**
   * GET instance.
   *
   * @return JSONMapper
   */
  public static JSONMapperHolder getMobileJSONMapper() {
    return Holder._mobile_json_instance;
  }

  /**
   * GET instance.
   *
   * @return JSONMapper
   */
  public static JSONMapperHolder getWebJSONMapper() {
    return Holder._web_json_instance;
  }

  /**
   * GET instance.
   *
   * @return JSONMapper
   */
  public static JSONMapperHolder getJSONMapper() {
    return Holder._json_instance;
  }

  /**
   * JSONString->JAVABean.
   *
   * @param json  JSONString
   * @param clazz class
   * @return JAVABean
   */
  public <T> T toJAVABean(@NonNull String json, @NonNull Class<T> clazz) throws HandlingException {
    try {
      return super.readValue(json, clazz);
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /**
   * JSONString->JAVABeanCollection.
   *
   * @param json  JSONString
   * @param clazz class...
   * @return Collection
   */
  public <T> Collection<T> toCollection(@NonNull String json, @NonNull Class<? extends Collection<T>> collection, Class<?>... clazz) throws UnsupportedOperationException, HandlingException {
    try {
      return super.readValue(json, super.getTypeFactory().constructParametricType(collection, clazz));
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /**
   * JAVABean->JSONString.
   *
   * @param bean JAVABean
   * @return JSONString
   */
  public String toJSONString(@NonNull Object bean) throws HandlingException {
    try {
      return super.writeValueAsString(bean);
    } catch (JsonProcessingException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /**
   * static inner class of holder.
   *
   * @author JYD_XL
   */
  private static class Holder {
    /** instance. */
    private static final JSONMapperHolder _web_json_instance = new JSONMapperHolder();
    /** instance. */
    private static final JSONMapperHolder _mobile_json_instance = new JSONMapperHolder();
    /** instance. */
    private static final JSONMapperHolder _json_instance = new JSONMapperHolder();

    /** Constructor. */
    private Holder() {}

    static {
      _web_json_instance.setDateFormat(ThreadLocal.withInitial(NaiveDateFormat::new).get()).setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE)).setSerializationInclusion(NON_NULL).disable(FAIL_ON_UNKNOWN_PROPERTIES).disable(FAIL_ON_INVALID_SUBTYPE).disable(FAIL_ON_UNRESOLVED_OBJECT_IDS).disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY).disable(FAIL_ON_UNRESOLVED_OBJECT_IDS).disable(FAIL_ON_EMPTY_BEANS).enable(SORT_PROPERTIES_ALPHABETICALLY);
    }
  }
}
