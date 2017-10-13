package org.github.spring.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import lombok.NonNull;

import org.github.spring.exception.RunException;
import org.github.spring.footstone.PropertyNameLocation;
import org.github.spring.footstone.PropertyPathLocation;

/**
 * PropertiesUtil.
 *
 * @author JYD_XL
 */
public abstract class PropertiesUtil {
  /** Constructor. */
  protected PropertiesUtil() {}

  /**
   * ADD props.
   *
   * @param path String
   */
  private static void addProperties(String path) {
    if (! cacheMap.containsKey(path)) load(path);
  }

  /**
   * LOAD.
   *
   * @param path String
   */
  private static void load(String path) {
    try {
      Properties property = new Properties();
      property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
      cacheMap.put(path, property);
    } catch (IOException e) {
      throw new RunException("exception-propertiesLoading ==> " + e.getMessage(), e);
    }
  }

  /**
   * GET Map.
   *
   * @param path String
   * @return Map
   */
  private static Map<String, String> getMap(String path) {
    Map<String, String> map = new HashMap<>();
    Properties property = getProperties(path);
    property.stringPropertyNames().forEach(name -> map.put(name, property.getProperty(name)));
    return map;
  }

  /**
   * GET Map.
   *
   * @param path PropertyPathLocation
   * @return Map
   */
  public static Map<String, String> getMap(PropertyPathLocation path) {
    return getMap(path.get());
  }

  /**
   * GET realPath.
   *
   * @param path String
   * @return String
   */
  private static String getRealPath(@NonNull String path) {
    if (StringUtil.isBlank(path)) {
      throw new RunException("The property [ " + path + " ] can not be is empty!");
    } else if (path.startsWith("/")) {
      return path.substring(1);
    } else {
      return path;
    }
  }

  /**
   * GET Properties.
   *
   * @param path String
   * @return Properties
   */
  private static Properties getProperties(String path) {
    String realPath = getRealPath(path);
    addProperties(realPath);
    return cacheMap.getOrDefault(realPath, null);
  }

  /**
   * GET Properties.
   *
   * @param path PropertyPathLocation
   * @return Properties
   */
  public static Properties getProperties(PropertyPathLocation path) {
    return getProperties(path.get());
  }

  /**
   * GET target.
   *
   * @param path String
   * @param key  String
   * @return String
   */
  private static String getValue(String path, String key) {
    return StringUtil.isEmpty(key) || StringUtil.isEmpty(path) ? null : getProperties(path).getProperty(key);
  }

  /**
   * GET StringValue.
   *
   * @param path PropertyPathLocation
   * @param name PropertyNameLocation
   * @return String
   */
  public static String getStringValue(PropertyPathLocation path, PropertyNameLocation name) {
    return getValue(path.get(), name.get());
  }

  /**
   * GET LongValue.
   *
   * @param path PropertyPathLocation
   * @param name PropertyNameLocation
   * @return long
   */
  public static long getLongValue(PropertyPathLocation path, PropertyNameLocation name) {
    return StringUtil.isEmpty(getStringValue(path, name)) ? 0L : Long.parseLong(getStringValue(path, name));
  }

  /**
   * GET IntValue.
   *
   * @param path PropertyPathLocation
   * @param name PropertyNameLocation
   * @return integer
   */
  public static int getIntValue(PropertyPathLocation path, PropertyNameLocation name) {
    return StringUtil.isEmpty(getStringValue(path, name)) ? 0 : Integer.parseInt(getStringValue(path, name));
  }

  /** cache. */
  private static final Map<String, Properties> cacheMap = new ConcurrentHashMap<>();
}
