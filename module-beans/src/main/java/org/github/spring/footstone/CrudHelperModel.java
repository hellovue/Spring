package org.github.spring.footstone;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.github.spring.annotation.Column;
import org.github.spring.enumeration.Flag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.github.spring.footstone.ConstInterface.AND;

@Slf4j
@Getter
public final class CrudHelperModel {
  private final List<FieldWrapper> attributes;

  private final Object condModel;

  CrudHelperModel(@NonNull Object condModel) {
    val condModelClass = condModel.getClass();
    val fields = new ArrayList<Field>(Arrays.asList(condModelClass.getDeclaredFields()));

    this.findAllFields(condModelClass, fields);
    this.condModel = condModel;
    this.attributes = fields.parallelStream().map(this::wrap).filter(Objects::nonNull).collect(Collectors.toList());
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

  private FieldWrapper wrap(Field field) {
    try {
      field.setAccessible(true);
      var column = field.getAnnotation(Column.class);
      if (isNull(column)) {
        val propertyDescriptor = new PropertyDescriptor(field.getName(), condModel.getClass());
        val getMethod = propertyDescriptor.getReadMethod();
        if (! getMethod.isAnnotationPresent(Column.class)) return null;
        column = getMethod.getAnnotation(Column.class);
      }

      val flag = column.flag();
      val type = field.getType();
      val data = field.get(condModel);
      val origin = isBlank(column.goal()) ? field.getName() : column.goal();
      val method = AND.concat(this.headUp(origin)).concat(flag.get());

      return new FieldWrapper(data, flag, type, origin, method);
    } catch (IllegalAccessException | IntrospectionException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  private String headUp(String name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }


  @Getter
  @AllArgsConstructor
  static final class FieldWrapper {
    final Object data;

    @NonNull
    final Flag flag;

    @NonNull
    final Class<?> type;

    @NonNull
    final String origin;

    @NonNull
    final String method;

    public boolean in(String... param) {
      return Arrays.asList(param).contains(origin);
    }

    public boolean notIn(String... param) {
      return ! this.in(param);
    }
  }
}
