/** Created by JYD_XL on 2017/5/25. */
package org.github.spring.util;



import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public abstract class StringUtil extends StringUtils {
  public static String getUUID() {
    return UUID.randomUUID().toString();
  }

  public static String getUUID(byte[] name) {
    return UUID.nameUUIDFromBytes(name).toString();
  }

  public static String getUUID(String name) {
    return UUID.fromString(name).toString();
  }

  /**
   * Collection is empty.
   *
   * @param collection Collection
   * @return boolean
   */
  public static <E> boolean isEmpty(Collection<E> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * Map is empty.
   *
   * @param map Map
   * @return boolean
   */
  public static <K, V> boolean isEmpty(Map<K, V> map) {
    return map == null || map.isEmpty();
  }

  /**
   * String-Object is empty.
   *
   * @param string Object
   * @return boolean
   */
  public static boolean isEmpty(Object string) {
    return string == null || "".equals(string.toString().trim());
  }

  /**
   * Array is empty.
   *
   * @param array E[]
   * @return boolean
   */
  public static <E> boolean isEmpty(E[] array) {
    return array == null || array.length == 0;
  }

  /**
   * Stream is empty.
   *
   * @param stream Stream
   * @return boolean
   */
  public static <E> boolean isEmpty(Stream<E> stream) {
    return stream == null;
  }

  /**
   * Collection is not empty.
   *
   * @param collection Collection
   * @return boolean
   */
  public static <E> boolean isNotEmpty(Collection<E> collection) {
    return !isEmpty(collection);
  }

  /**
   * Map is not empty.
   *
   * @param map Map
   * @return boolean
   */
  public static <K, V> boolean isNotEmpty(Map<K, V> map) {
    return !isEmpty(map);
  }

  /**
   * String-Object is not empty.
   *
   * @param string Object
   * @return boolean
   */
  public static boolean isNotEmpty(Object string) {
    return !isEmpty(string);
  }

  /**
   * Array is not empty.
   *
   * @param array E[]
   * @return boolean
   */
  public static <E> boolean isNotEmpty(E[] array) {
    return !isEmpty(array);
  }

  /**
   * Stream is not empty.
   *
   * @param stream Stream
   * @return boolean
   */
  public static <E> boolean isNotEmpty(Stream<E> stream) {
    return !isEmpty(stream);
  }
}
