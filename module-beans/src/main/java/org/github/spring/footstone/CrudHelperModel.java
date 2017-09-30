package org.github.spring.footstone;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.github.spring.annotation.Column;
import org.github.spring.enumeration.Flag;
import org.github.spring.util.StringUtil;

import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.var;
import lombok.val;

import static java.util.Objects.isNull;

/**
 * 分页查询数据模型.
 * <p>
 * 该类的作用是辅助分页及帮助分页时进行排序操作.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public final class CrudHelperModel implements ConstInterface {

  private final List<FieldWrapper> attributes;

  public CrudHelperModel(@NonNull Object condModel) {
    val condModelClass = condModel.getClass();
    val fields = new ArrayList<Field>(Arrays.asList(condModelClass.getDeclaredFields()));
    this.findAllFields(condModelClass, fields);
    attributes = fields.parallelStream().map(v -> this.wrap(v, condModel, condModelClass)).filter(Objects:: nonNull).collect(Collectors.toList());
  }

  public void startCrud(@NonNull Object criteria) {}

  public void startCrudWithIgnore(@NonNull Object criteria, String... ignore) {}

  public void startCrudWithTarget(@NonNull Object criteria, String... target) {}

  /**
   * 通过递归获取查询数据模型类的所有属性信息.
   *
   * @param condModelClass 数据模型类类型
   * @param fields         属性信息存储容器
   */
  private void findAllFields(Class<?> condModelClass, List<Field> fields) {
    condModelClass = condModelClass.getSuperclass();
    if (condModelClass.isAssignableFrom(Object.class)) {return;}
    fields.addAll(Arrays.asList(condModelClass.getDeclaredFields()));
    findAllFields(condModelClass, fields);
  }

  private FieldWrapper wrap(Field field, Object condModel, Class<?> condModelClass) {
    try {
      field.setAccessible(true);
      var column = field.getAnnotation(Column.class);
      if (isNull(column)) {
        val propertyDescriptor = new PropertyDescriptor(field.getName(), condModelClass);
        Method getMethod = propertyDescriptor.getReadMethod();
        if (! getMethod.isAnnotationPresent(Column.class)) return null;
        column = getMethod.getAnnotation(Column.class);
      }

      val data = field.get(condModel);
      val type = field.getType();
      val flag = column.flag();
      val goal = StringUtil.isBlank(column.goal()) ? field.getName() : column.goal();

      if (isNull(data)) return null;
      if (isNull(goal)) return null;

      return new FieldWrapper(data, goal, flag, type);
    } catch (IllegalAccessException | IntrospectionException e) {
      LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
    }
    return null;
  }

  @Data
  private static final class FieldWrapper {
    @NonNull
    final Object data;

    @NonNull
    final String goal;

    @NonNull
    final Flag flag;

    @NonNull
    final Class<?> type;

    public boolean contains(String... collection) {
      return true;
    }

    public boolean notContains(String... collection) {
      return false;
    }
  }
}
