package org.github.spring.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.github.spring.annotation.Column;
import org.github.spring.enumeration.Flag;
import org.github.spring.exception.DataException;
import org.github.spring.exception.RunException;

import com.google.common.annotations.Beta;

import lombok.NonNull;
import lombok.val;

import static java.util.Objects.isNull;
import static org.github.spring.enumeration.Flag.BETWEEN_HEAD;
import static org.github.spring.enumeration.Flag.BETWEEN_TAIL;
import static org.github.spring.enumeration.Flag.NOT_BETWEEN_HEAD;
import static org.github.spring.enumeration.Flag.NOT_BETWEEN_TAIL;

/**
 * CrudHelper.
 *
 * @author JYD_XL
 */
@Beta
public abstract class CrudHelper {
  /** MethodDescription----AND. */
  private static final String AND = "and";

  /** MethodDescription----GREATER_THAN_OR_EQUAL_TO. */
  private static final String GREATER_THAN_OR_EQUAL_TO = "GREATER_THAN_OR_EQUAL_TO";

  /** MethodDescription----GREATER_THAN. */
  private static final String GREATER_THAN = "GREATER_THAN";

  /** MethodDescription----LESS_THAN_OR_EQUAL_TO. */
  private static final String LESS_THAN_OR_EQUAL_TO = "LESS_THAN_OR_EQUAL_TO";

  /** MethodDescription----LESS_THAN. */
  private static final String LESS_THAN = "LESS_THAN";

  /** MethodDescription----Between. */
  private static final String BETWEEN = "Between";

  /** MethodDescription----NotBetween. */
  private static final String NOT_BETWEEN = "NotBetween";

  /** like. */
  private static final String LIKE = "%";

  /** Flag----Head. */
  private static final String FLAG_HEAD = "Head";

  /** Flag----Tail. */
  private static final String FLAG_TAIL = "Tail";

  /** Flag----UnderLine. */
  private static final String UNDERLINE = "_";

  /** Flag----LIKE. */
  private static final String FLAG_LIKE = "LIKE";

  /** SPECIAL_TAG_SET. */
  private static final List<Flag> FLAG_LIST = Arrays.asList(BETWEEN_HEAD, BETWEEN_TAIL, NOT_BETWEEN_HEAD, NOT_BETWEEN_TAIL);

  /**
   * Start CRUD.
   *
   * @param condModel Object
   * @param criteria  Object
   */
  public static void startCrud(Object condModel, Object criteria) {
    start(condModel, criteria, null);
  }

  /**
   * Start CRUDIgnore.
   *
   * @param condModel Object
   * @param criteria  Object
   * @param ignore    String...
   */
  public static void startIgnore(Object condModel, Object criteria, String... ignore) {
    start(condModel, criteria, Status.IGNORE, ignore);
  }

  /**
   * Start CRUDTarget.
   *
   * @param condModel Object
   * @param criteria  Object
   * @param target    String...
   */
  public static void startTarget(Object condModel, Object criteria, String... target) {
    start(condModel, criteria, Status.TARGET, target);
  }

  /**
   * start.
   *
   * @param condModel Object
   * @param criteria  Object
   * @param status    Status
   * @param param     String...
   */
  private static void start(@NonNull Object condModel, @NonNull Object criteria, Status status, String... param) {
    try {
      turn(condModel, criteria, status, param);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | IntrospectionException | DataException e) {
      throw new RunException("CRUDTarget-Exception:" + e.getMessage(), e);
    }
  }

  /**
   * CURD core function.
   *
   * @param condModel Object
   * @param criteria  Object
   * @param status    Status
   * @throws IntrospectionException    Exception
   * @throws IllegalAccessException    Exception
   * @throws IllegalArgumentException  Exception
   * @throws InvocationTargetException Exception
   * @throws NoSuchMethodException     Exception
   * @throws SecurityException         Exception
   * @throws DataException             Exception
   */
  private static void turn(Object condModel, Object criteria, Status status, String... param) throws IntrospectionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, DataException {
    // GET criteria class.
    val criteriaClass = criteria.getClass();
    // GET condition model class.
    val condModelClass = condModel.getClass();
    // GET fields.
    val fields = new ArrayList<Field>(Arrays.asList(condModelClass.getDeclaredFields()));

    // GET params.
    val params = Arrays.asList(param);

    getFields(condModelClass, fields);

    // clean.
    clear(fields, params, status, condModelClass);
    // init.
    val betweenMap = new HashMap<String, Between>();

    // start.
    for (val field : fields) {
      // 设置属性访问权限.
      field.setAccessible(true);
      //获取属性值.
      Object value = field.get(condModel);
      if (StringUtil.isEmpty(value) || (value instanceof Collection<?> && ((Collection<?>) value).isEmpty())) {
        // 跳过空属性和空的容器.
        continue;
      } else if (value instanceof String) {
        // 去除字符串左右空格.
        value = value.toString().trim();
      }

      Flag flag;
      String goal;
      if (field.isAnnotationPresent(Column.class)) {
        // GET flag.
        flag = field.getAnnotation(Column.class).flag();
        // GET goal.
        goal = field.getAnnotation(Column.class).goal().trim();
      } else {
        val propertyDescriptor = new PropertyDescriptor(field.getName(), condModelClass);
        Method getMethod = propertyDescriptor.getReadMethod();
        // GET flag.
        flag = getMethod.getAnnotation(Column.class).flag();
        // GET goal.
        goal = getMethod.getAnnotation(Column.class).goal().trim();
      }

      // is between?
      if (FLAG_LIST.contains(flag)) {
        if (betweenMap.containsKey(goal)) {
          // FLAG_LIST.
          betweenMap.get(goal).setValue(flag, value);
        } else {
          // NEW.
          betweenMap.put(goal, new Between(flag, value));
        }
      } else {
        // startInvoke，TARGET first，field second.
        switch (flag) {
          case IS_NULL:
          case NOT_NULL:
            invokeMethod(criteriaClass, mark(field, goal), flag.toString(), criteria);
            break;
          case EQUAL_TO:
          case NOT_EQUAL_TO:
          case GREATER_THAN:
          case GREATER_THAN_OR_EQUAL_TO:
          case LESS_THAN:
          case LESS_THAN_OR_EQUAL_TO:
          case IN:
          case NOT_IN:
            invokeMethod(criteriaClass, mark(field, goal), flag.toString(), criteria, value);
            break;
          case LIKE:
          case NOT_LIKE:
            invokeMethod(criteriaClass, mark(field, goal), flag.toString(), criteria, valueLikeNormal(value));
            break;
          case LIKE_BEHIND:
            invokeMethod(criteriaClass, mark(field, goal), FLAG_LIKE, criteria, valueLikeBehind(value));
            break;
          default:
            break;
        }
      }
    }

    // start between.
    for (Map.Entry<String, Between> temp : betweenMap.entrySet()) {
      invokeBetween(temp.getKey(), temp.getValue(), criteriaClass, criteria);
    }
  }

  /**
   * 通过递归获取查询数据模型类的所有属性信息.
   *
   * @param condModelClass 数据模型类类型
   * @param fields         属性信息存储容器
   */
  private static void getFields(Class<?> condModelClass, List<Field> fields) {
    condModelClass = condModelClass.getSuperclass();
    if (condModelClass.isAssignableFrom(Object.class)) {return;}
    fields.addAll(Arrays.asList(condModelClass.getDeclaredFields()));
    getFields(condModelClass, fields);
  }

  /**
   * Method Invoke.
   *
   * @param criteriaClass Class<?>
   * @param target        String
   * @param detail        String
   * @param criteria      Object
   * @param value         Object...
   * @throws IllegalAccessException    Exception
   * @throws IllegalArgumentException  Exception
   * @throws InvocationTargetException Exception
   * @throws NoSuchMethodException     Exception
   * @throws SecurityException         Exception
   */
  private static void invokeMethod(Class<?> criteriaClass, String target, String detail, Object criteria, Object... value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    if (value.length == 0) {
      criteriaClass.getDeclaredMethod(AND + headUp(target) + detail).invoke(criteria);
    } else if (value.length == 1) {
      // invoke list.
      if (Collection.class.isAssignableFrom(value[0].getClass())) {
        criteriaClass.getDeclaredMethod(AND + headUp(target) + detail, List.class).invoke(criteria, value[0]);
      } else {
        criteriaClass.getDeclaredMethod(AND + headUp(target) + detail, value[0].getClass()).invoke(criteria, value[0]);
      }
    }
  }

  /**
   * Between Invoke.
   *
   * @param target        String
   * @param between       Between
   * @param criteriaClass Class<?>
   * @param criteria      Object
   * @throws IllegalAccessException    Exception
   * @throws IllegalArgumentException  Exception
   * @throws InvocationTargetException Exception
   * @throws NoSuchMethodException     Exception
   * @throws SecurityException         Exception
   */
  private static void invokeBetween(String target, Between between, Class<?> criteriaClass, Object criteria) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    // GET Type.
    switch (between.getType()) {
      case HEAD:
        criteriaClass.getDeclaredMethod(AND + headUp(target) + (between.method.equals(BETWEEN) ? GREATER_THAN_OR_EQUAL_TO : LESS_THAN), between.clazz).invoke(criteria, between.head);
        break;
      case TAIL:
        criteriaClass.getDeclaredMethod(AND + headUp(target) + (between.method.equals(NOT_BETWEEN) ? GREATER_THAN : LESS_THAN_OR_EQUAL_TO), between.clazz).invoke(criteria, between.tail);
        break;
      case BOTH:
        criteriaClass.getDeclaredMethod(AND + headUp(target) + between.method, between.clazz, between.clazz).invoke(criteria, between.head, between.tail);
    }
  }

  /**
   * GET TARGET.
   *
   * @param field Field
   * @param goal  String
   * @return String
   */
  private static String mark(Field field, String goal) {
    return StringUtil.isEmpty(goal) ? field.getName() : goal;
  }

  /**
   * FieldName headUp.
   *
   * @param name String
   * @return String
   */
  private static String headUp(String name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  /**
   * LikeNormal.
   *
   * @param value Object
   * @return String
   */
  private static String valueLikeNormal(Object value) {
    return LIKE + value + LIKE;
  }

  /**
   * LIKE_BEHIND.
   *
   * @param value Object
   * @return String
   */
  private static String valueLikeBehind(Object value) {
    return value + LIKE;
  }

  /**
   * Field clean.
   *
   * @param fields List
   * @param params List
   * @param status Status
   * @throws DataException Exception
   */
  private static void clear(List<Field> fields, List<String> params, Status status, Class<?> condModelClass) throws DataException, IntrospectionException {
    val temp = new ArrayList<Field>();
    for (val field : fields) {
      if (! field.isAnnotationPresent(Column.class)) {
        val propertyDescriptor = new PropertyDescriptor(field.getName(), condModelClass);
        Method getMethod = propertyDescriptor.getReadMethod();
        if (! getMethod.isAnnotationPresent(Column.class)) {
          temp.add(field);
        }
      }
    }
    fields.removeAll(temp);

    // default.
    if (isNull(status)) {return;}

    // check.
    if (StringUtil.isEmpty(params)) {throw new DataException("When the status is not null,goal can not be null!");}

    // clean.
    switch (status) {
      case IGNORE:
        fields.removeIf(field -> params.contains(field.getName()) || params.contains(field.getAnnotation(Column.class).goal()));
        break;
      case TARGET:
        fields.removeIf(field -> ! params.contains(field.getName()) && ! params.contains(field.getAnnotation(Column.class).goal()));
    }
  }

  /**
   * status.
   *
   * @author JYD_XL
   */
  private enum Status {
    HEAD, TAIL, BOTH, TARGET, IGNORE
  }

  /**
   * Between.
   *
   * @author JYD_XL
   */
  private static class Between {
    /** method. */
    String method;

    /** HEAD. */
    Object head;

    /** TAIL. */
    Object tail;

    /** class. */
    Class<?> clazz;

    /**
     * GET status.
     *
     * @return Status
     */
    Status getType() {
      if (head != null && tail == null) {
        return Status.HEAD;
      } else if (head == null && tail != null) {
        return Status.TAIL;
      } else {
        return Status.BOTH;
      }
    }

    /**
     * GET Method name.
     *
     * @param flag Flag
     * @return String
     */
    String getMethod(Flag flag) {
      return flag.toString().split(UNDERLINE)[0];
    }

    /**
     * GET Field name.
     *
     * @param flag Flag
     * @return String
     */
    String getDetail(Flag flag) {
      return flag.toString().split(UNDERLINE)[1];
    }

    /**
     * FLAG_LIST value.
     *
     * @param flag  Flag
     * @param value Object
     * @throws DataException Exception
     */
    void setValue(Flag flag, Object value) throws DataException {
      if (method == null) {
        // INIT.
        method = getMethod(flag);
      } else if (! method.equals(getMethod(flag))) {
        throw new DataException("Between AND NotBetween，use one of them!");
      } else if (! value.getClass().equals(clazz)) {
        throw new DataException("Class must be same when use between!");
      }

      // INIT.
      String type = getDetail(flag);
      if (type.equals(FLAG_HEAD)) {
        head = value;
      } else if (type.equals(FLAG_TAIL)) {
        tail = value;
      }
    }

    /**
     * Constructor.
     *
     * @param flag  Flag
     * @param value Object
     * @throws DataException Exception
     */
    Between(Flag flag, Object value) throws DataException {
      // INIT.
      clazz = value.getClass();
      setValue(flag, value);
    }
  }

  //TODO 属性信息的包装.
  private static class FieldWapper {}
}
