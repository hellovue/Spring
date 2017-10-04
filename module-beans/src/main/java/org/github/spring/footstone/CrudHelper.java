package org.github.spring.footstone;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static java.util.Objects.isNull;
import static org.github.spring.footstone.ConstInterface.LIKE;
import static org.github.spring.footstone.CrudHelper.Status.IGNORE;
import static org.github.spring.footstone.CrudHelper.Status.TARGET;

/**
 * CrudHelper.
 *
 * @author JYD_XL
 */
@Slf4j
abstract class CrudHelper {

  /**
   * status.
   *
   * @author JYD_XL
   */
  enum Status {
    TARGET, IGNORE
  }

  public static void startCrud(Object criteria, CrudHelperModel helper) {
    start(criteria, helper, null);
  }

  public static void startIgnore(Object criteria, CrudHelperModel helper, String... ignore) {
    start(criteria, helper, IGNORE, ignore);
  }

  public static void startTarget(Object criteria, CrudHelperModel helper, String... target) {
    start(criteria, helper, TARGET, target);
  }

  private static void start(Object criteria, CrudHelperModel helper, Status status, String... param) {
    try {
      main(criteria, helper, status, param);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      log.error("exception-crud ==> " + e.getMessage() + "  NoSuchMethod", e);
    }
  }

  /** CURD core function. */
  private static void main(Object criteria, CrudHelperModel helper, Status status, String... param) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    val fieldWrappers = helper.getAttributes();
    val criteriaClass = criteria.getClass();
    val optional = Optional.ofNullable(status);

    optional.filter(IGNORE::equals).ifPresent(v -> fieldWrappers.removeIf(e -> e.in(param)));
    optional.filter(TARGET::equals).ifPresent(v -> fieldWrappers.removeIf(e -> e.notIn(param)));

    for (val wrapper : fieldWrappers) {
      val data = wrapper.getData();
      val flag = wrapper.getFlag();
      val type = wrapper.getType();

      val method = wrapper.getMethod();

      switch (flag) {
        case IS_NULL:
        case NOT_NULL:
          criteriaClass.getMethod(method).invoke(criteria);
          break;
        case EQUAL_TO:
        case NOT_EQUAL_TO:
        case GREATER_THAN:
        case GREATER_THAN_OR_EQUAL_TO:
        case LESS_THAN:
        case LESS_THAN_OR_EQUAL_TO:
        case IN:
        case NOT_IN:
          criteriaClass.getMethod(method, type).invoke(criteria, data);
          break;
        case LIKE:
        case NOT_LIKE:
          criteriaClass.getMethod(method, type).invoke(criteria, valueLikeNormal(data));
          break;
        case LIKE_FULL:
        case NOT_LIKE_FULL:
          criteriaClass.getMethod(method, type).invoke(criteria, valueLikeFull(data));
          break;
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
    return isNull(value) ? null : LIKE.concat(value.toString());
  }

  /**
   * LIKE_BEHIND.
   *
   * @param value Object
   * @return String
   */
  private static String valueLikeFull(Object value) {
    return isNull(value) ? null : LIKE.concat(value.toString().concat(LIKE));
  }
}
