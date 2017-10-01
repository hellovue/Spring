package org.github.spring.footstone;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.github.spring.annotation.Column;
import org.github.spring.enumeration.Flag;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * 分页查询数据模型.
 * <p>
 * 该类的作用是辅助分页及帮助分页时进行排序操作.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
@Slf4j
public final class CrudHelperModel implements ConstInterface {

  @Getter
  private final List<FieldWrapper> attributes;

  CrudHelperModel(@NonNull Object condModel) {
    val condModelClass = condModel.getClass();
    val fields = new ArrayList<Field>(Arrays.asList(condModelClass.getDeclaredFields()));
    this.findAllFields(condModelClass, fields);
    attributes = fields.parallelStream().map(v -> this.wrap(v, condModel, condModelClass)).filter(Objects:: nonNull).collect(Collectors.toList());
  }

  public void startCrud(@NonNull Object criteria) {
    CrudHelper.startCrud(criteria, this);
  }

  public void startCrudWithIgnore(@NonNull Object criteria, String... ignore) {
    CrudHelper.startIgnore(criteria, this, ignore);
  }

  public void startCrudWithTarget(@NonNull Object criteria, String... target) {
    CrudHelper.startTarget(criteria, this, target);
  }

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
        val getMethod = propertyDescriptor.getReadMethod();
        if (! getMethod.isAnnotationPresent(Column.class)) return null;
        column = getMethod.getAnnotation(Column.class);
      }

      val flag = column.flag();
      val type = field.getType();
      var data = field.get(condModel);
      var goal = isBlank(column.goal()) ? field.getName() : column.goal();
      goal = this.headUp(goal);
      goal = AND.concat(goal).concat(flag.get());

      return new FieldWrapper(data, goal, flag, type);
    } catch (IllegalAccessException | IntrospectionException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  private String headUp(String name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  /** MethodDescription----AND. */
  private static final String AND = "and";

  @Data
  static final class FieldWrapper {
    final Object data;

    @NonNull
    final String goal;

    @NonNull
    final Flag flag;

    @NonNull
    final Class<?> type;

    boolean In(@NonNull List<String> param) {
      return param.contains(goal);
    }

    boolean noIn(@NonNull List<String> param) {
      return ! this.In(param);
    }
  }
}
