package org.github.spring.footstone;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.TimeZone;

import lombok.NonNull;

import org.github.spring.exception.HandlingException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JSONMapperHolder.
 *
 * @author JYD_XL
 * @see com.fasterxml.jackson.databind.ObjectMapper
 */
public final class JSONMapperHolder extends ObjectMapper {
  /** Constructor. */
  JSONMapperHolder() {}

  /** JSONString -> Collection. */
  public <T> Collection<T> toCollection(@NonNull String json, @NonNull Class<? extends Collection<T>> collection, Class<?>... clazz) throws UnsupportedOperationException, HandlingException {
    try {
      return super.readValue(json, this.getTypeFactory().constructParametricType(collection, clazz));
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /** JSONString -> JAVABean. */
  public <T> T toJAVABean(@NonNull String json, @NonNull Class<T> clazz) throws HandlingException {
    try {
      return super.readValue(json, clazz);
    } catch (IOException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /** JAVABean -> JSONString. */
  public String toJSONString(@NonNull Object bean) throws HandlingException {
    try {
      return super.writeValueAsString(bean);
    } catch (JsonProcessingException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /** GET instance. */
  public static JSONMapperHolder getMobileJSONMapper() {
    return Holder._mobile_json_instance;
  }

  /** GET instance. */
  public static JSONMapperHolder getWebJSONMapper() {
    return Holder._web_json_instance;
  }

  /** GET instance. */
  public static JSONMapperHolder getJSONMapper() {
    return Holder._json_instance;
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
    private static final JSONMapperHolder _web_json_instance = new JSONMapperHolder();

    /** instance. */
    private static final JSONMapperHolder _mobile_json_instance = new JSONMapperHolder();

    /** instance. */
    private static final JSONMapperHolder _json_instance = new JSONMapperHolder();

    static {
      // 初始化.
      _web_json_instance
        .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS)
        .disable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)
        .setTimeZone(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE))
        .setSerializationInclusion(Include.NON_NULL)
        .setDateFormat(ThreadLocal.withInitial(() -> new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT)).get());
    }
  }
}