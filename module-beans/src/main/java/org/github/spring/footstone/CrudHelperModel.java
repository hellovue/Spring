package org.github.spring.footstone;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.github.spring.annotation.Column;
import org.github.spring.annotation.Columns;
import org.github.spring.enumeration.Flag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
public final class CrudHelperModel extends AbstractEntity {
  /** MethodDescription----AND. */
  private static final String AND = "and";
  @Getter
  private final List<Wrapper> attributes;
  private final Object condModel;

  @Override
  public String toString() {
    return JSONMapperHolder.getWebJSONMapper().toJSONString(attributes);
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

  @SuppressWarnings("all")
  private List<Wrapper> findAllColumnOnMethod() {
    val wrappers = new ArrayList<Wrapper>();
    val condModelClass = condModel.getClass();
    for (Method each : condModelClass.getMethods()) {
      try {
        if (! each.getName().startsWith("get")) continue;

        val column = each.getAnnotation(Column.class);
        val columns = each.getAnnotation(Columns.class);
        if (Objects.isNull(column) && Objects.isNull(columns)) continue;

        Object data = each.invoke(condModel);
        Class<?> type = each.getReturnType();
        if (Collection.class.isAssignableFrom(type) || Array.class.isAssignableFrom(type)) type = List.class;
        if (Objects.nonNull(data) && data instanceof Array) data = Arrays.asList((Object[]) data);
        if (Objects.nonNull(data) && data instanceof Collection) data = new ArrayList((Collection) data);

        if (Objects.isNull(columns)) {
          val flag = column.flag();
          val origin = isBlank(column.goal()) ? this.headDown(each.getName().substring(3)) : column.goal();
          val method = AND.concat(this.headUp(origin)).concat(flag.get());
          wrappers.add(new Wrapper(data, flag, type, origin, method));
        } else {
          for (Column item : columns.value()) {
            val flag = item.flag();
            val origin = isBlank(item.goal()) ? this.headDown(each.getName().substring(3)) : item.goal();
            val method = AND.concat(this.headUp(origin)).concat(flag.get());
            wrappers.add(new Wrapper(data, flag, type, origin, method));
          }
        }
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return wrappers;
  }

  @SuppressWarnings("all")
  private List<Wrapper> wrap(Field field) {
    try {
      field.setAccessible(true);
      val column = field.getAnnotation(Column.class);
      val columns = field.getAnnotation(Columns.class);
      if (Objects.isNull(column) && Objects.isNull(columns)) return null;

      Object data = field.get(condModel);
      Class type = (Collection.class.isAssignableFrom(field.getType()) || Array.class.isAssignableFrom(field.getType())) ? List.class : field.getType();
      if (Objects.nonNull(data) && data instanceof Array) data = Arrays.asList((Object[]) data);
      if (Objects.nonNull(data) && data instanceof Collection) data = new ArrayList((Collection) data);

      val wrappers = new ArrayList<Wrapper>();
      if (Objects.isNull(column)) {
        for (Column each : columns.value()) {
          val flag = each.flag();
          val origin = isBlank(each.goal()) ? field.getName() : each.goal();
          val method = AND.concat(this.headUp(origin)).concat(flag.get());
          wrappers.add(new Wrapper(data, flag, type, origin, method));
        }
      } else {
        val flag = column.flag();
        val origin = isBlank(column.goal()) ? field.getName() : column.goal();
        val method = AND.concat(this.headUp(origin)).concat(flag.get());
        wrappers.add(new Wrapper(data, flag, type, origin, method));
      }
      return wrappers;
    } catch (IllegalAccessException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  private String headUp(String name) {
    return name.substring(0, 1).toUpperCase().concat(name.substring(1));
  }

  private String headDown(String name) {
    return name.substring(0, 1).toLowerCase().concat(name.substring(1));
  }

  @Getter
  @AllArgsConstructor
  static final class Wrapper {
    @JsonProperty("values")
    final Object data;

    @NonNull
    @JsonIgnore
    final Flag flag;

    @NonNull
    @JsonIgnore
    final Class<?> type;

    @NonNull
    @JsonIgnore
    final String origin;

    @NonNull
    final String method;

    boolean in(String... param) {
      return Arrays.asList(param).contains(origin);
    }

    boolean notIn(String... param) {
      return ! this.in(param);
    }
  }

  CrudHelperModel(@NonNull Object condModel) {
    val condModelClass = condModel.getClass();
    val fields = new ArrayList<Field>(Arrays.asList(condModelClass.getDeclaredFields()));

    this.findAllFields(condModelClass, fields);
    this.condModel = condModel;
    this.attributes = fields.parallelStream().map(this::wrap).filter(Objects::nonNull).flatMap(List::parallelStream).collect(Collectors.toList());
    this.attributes.addAll(this.findAllColumnOnMethod());
  }
}
