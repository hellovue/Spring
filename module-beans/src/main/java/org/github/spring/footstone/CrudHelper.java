package org.github.spring.footstone;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * CrudHelper.
 *
 * @author JYD_XL
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
abstract class CrudHelper {
  /** 调用方法, 默认模式. */
  static void startCrud(Object criteria, CrudHelperModel helper) {
    start(criteria, helper, null);
  }

  /** 调用方法, 忽略模式. */
  static void startIgnore(Object criteria, CrudHelperModel helper, String... ignore) {
    start(criteria, helper, Status.IGNORE, ignore);
  }

  /** 调用方法, 目标模式. */
  static void startTarget(Object criteria, CrudHelperModel helper, String... target) {
    start(criteria, helper, Status.TARGET, target);
  }

  /** 核心方法. */
  private static void start(Object criteria, CrudHelperModel helper, Status status, String... param) {
    val fieldWrappers = helper.getAttributes();
    val criteriaClass = criteria.getClass();
    val optional = Optional.ofNullable(status);

    optional.filter(Status.IGNORE::equals).ifPresent(v -> fieldWrappers.removeIf(e -> e.in(param)));
    optional.filter(Status.TARGET::equals).ifPresent(v -> fieldWrappers.removeIf(e -> e.notIn(param)));

    for (val wrapper : fieldWrappers) {
      val flag = wrapper.getFlag();
      val data = wrapper.getValues();

      val methodName = wrapper.getMethod();
      val parameterType = wrapper.getType();
      try {
        switch (flag) {
          case IS_NULL:
          case NOT_NULL:
            criteriaClass.getMethod(methodName).invoke(criteria);
            break;
          case EQUAL_TO:
          case NOT_EQUAL_TO:
          case GREATER_THAN:
          case GREATER_THAN_OR_EQUAL_TO:
          case LESS_THAN:
          case LESS_THAN_OR_EQUAL_TO:
          case IN:
          case NOT_IN:
            criteriaClass.getMethod(methodName, parameterType).invoke(criteria, data);
            break;
          case LIKE:
          case NOT_LIKE:
            criteriaClass.getMethod(methodName, parameterType).invoke(criteria, valueLikeNormal(data));
            break;
          case LIKE_FULL:
          case NOT_LIKE_FULL:
            criteriaClass.getMethod(methodName, parameterType).invoke(criteria, valueLikeFull(data));
            break;
        }
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        log.error("exception-crud ==> " + e.getMessage() + " NoSuchMethod", e);
      }
    }
  }

  /**
   * LikeNormal.
   *
   * @param value Object
   * @return String
   */
  private static String valueLikeNormal(Object value) {
    return Objects.isNull(value) ? null : LIKE.concat(value.toString());
  }

  /**
   * LIKE_BEHIND.
   *
   * @param value Object
   * @return String
   */
  private static String valueLikeFull(Object value) {
    return Objects.isNull(value) ? null : LIKE.concat(value.toString().concat(LIKE));
  }

  /** 模糊查询附加字符. */
  private static final String LIKE = "%";

  /**
   * status.
   *
   * @author JYD_XL
   */
  enum Status {
    TARGET, IGNORE
  }
}