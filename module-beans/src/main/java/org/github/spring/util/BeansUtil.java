package org.github.spring.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.val;

import org.github.spring.exception.HandlingException;

import org.springframework.beans.BeanUtils;

public abstract class BeansUtil {
  /** COPY data. */
  public static <T> T copy(@NonNull T source, @NonNull Class<? extends T> target) {
    try {
      val instance = target.newInstance();
      BeanUtils.copyProperties(source, instance);
      return instance;
    } catch (IllegalAccessException | InstantiationException e) {
      throw new HandlingException(e.getMessage(), e);
    }
  }

  /** COPY data. */
  @SuppressWarnings("unchecked")
  public static <T> T[] copy(@NonNull T[] source, @NonNull Class<? extends T> target) {
    return (T[]) Stream.of(source).parallel().map(each -> copy(each, target)).toArray();
  }

  /** COPY data. */
  public static <T> List<T> copy(@NonNull List<T> source, @NonNull Class<? extends T> target) {
    return source.parallelStream().map(each -> copy(each, target)).collect(Collectors.toList());
  }

  /** COPY data. */
  @SuppressWarnings("unchecked")
  public static <T> T[] copyOfArray(@NonNull List<T> source, @NonNull Class<? extends T> target) {
    return (T[]) source.parallelStream().map(each -> copy(each, target)).toArray();
  }

  /** COPY data. */
  @SuppressWarnings("unchecked")
  public static <T> T[] copyOfArray(@NonNull Stream<T> source, @NonNull Class<? extends T> target) {
    return (T[]) source.parallel().map(each -> copy(each, target)).toArray();
  }

  /** COPY data. */
  public static <T> List<T> copyOfList(@NonNull T[] source, @NonNull Class<? extends T> target) {
    return Stream.of(source).parallel().map(each -> copy(each, target)).collect(Collectors.toList());
  }

  /** COPY data. */
  public static <T> List<T> copyOfList(@NonNull Stream<T> source, @NonNull Class<? extends T> target) {
    return source.parallel().map(each -> copy(each, target)).collect(Collectors.toList());
  }
}