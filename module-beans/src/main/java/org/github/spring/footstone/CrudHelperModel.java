package org.github.spring.footstone;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.github.spring.annotation.Column;
import org.github.spring.annotation.Columns;
import org.github.spring.enumeration.Method;
import org.github.spring.util.StringUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CRUD辅助数据模型.
 *
 * @author JYD_XL
 * @see org.github.spring.footstone.AbstractEntity
 */
@Slf4j
public final class CrudHelperModel extends AbstractEntity {
  /** CRUD属性包装信息集合. */
  @Getter
  private final List<Wrapper> attributes;

  /** 原始数据. */
  private final Object condModel;

  /** Constructor. */
  CrudHelperModel(@NonNull Object condModel) {
    val condModelClass = condModel.getClass();
    val fields = new ArrayList<Field>(Arrays.asList(condModelClass.getDeclaredFields()));

    this.findAllFields(condModelClass, fields);
    this.condModel = condModel;
    this.attributes = fields.parallelStream().map(this::wrap).filter(Objects::nonNull).flatMap(List::parallelStream).collect(Collectors.toList());
    this.attributes.addAll(this.findAllColumnOnMethod());
  }

  @Override
  public String toString() {
    return JSON_HOLDER.toJSONString(attributes);
  }

  /** 查找所有位于方法上的CRUD标记注解. */
  @SuppressWarnings("all")
  private List<Wrapper> findAllColumnOnMethod() {
    val wrappers = new ArrayList<Wrapper>();
    val condModelClass = condModel.getClass();
    for (val each : condModelClass.getMethods()) {
      try {
        if (!each.getName().startsWith("get")) continue;

        val column = each.getAnnotation(Column.class);
        val columns = each.getAnnotation(Columns.class);
        if (Objects.isNull(column) && Objects.isNull(columns)) continue;

        var data = each.invoke(condModel);
        Class<?> type = each.getReturnType();
        if (Collection.class.isAssignableFrom(type) || Array.class.isAssignableFrom(type)) type = List.class;
        if (Objects.nonNull(data) && data instanceof Array) data = Arrays.asList((Object[]) data);
        if (Objects.nonNull(data) && data instanceof Collection) data = new ArrayList((Collection) data);

        if (Objects.isNull(columns)) {
          val flag = column.value();
          val origin = StringUtil.isBlank(column.field()) ? this.headDown(each.getName().substring(3)) : column.field();
          val method = AND.concat(this.headUp(origin)).concat(flag.get());
          wrappers.add(new Wrapper(data, flag, type, origin, method));
        } else {
          for (Column item : columns.value()) {
            val flag = item.value();
            val origin = StringUtil.isBlank(item.field()) ? this.headDown(each.getName().substring(3)) : item.field();
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

  /** 查找所有属性. */
  private void findAllFields(Class<?> condModelClass, List<Field> fields) {
    condModelClass = condModelClass.getSuperclass();
    if (condModelClass.isAssignableFrom(Object.class)) {return;}
    fields.addAll(Arrays.asList(condModelClass.getDeclaredFields()));
    findAllFields(condModelClass, fields);
  }

  /** 讲方法名转为属性名. */
  private String headDown(String name) {
    return name.substring(0, 1).toLowerCase().concat(name.substring(1));
  }

  /** 将属性名转为方法名. */
  private String headUp(String name) {
    return name.substring(0, 1).toUpperCase().concat(name.substring(1));
  }

  /** 执行方法. */
  public void startCrud(@NonNull Object criteria) {
    CrudHelper.startCrud(criteria, this);
  }

  /** 执行方法. */
  public void startCrudWithIgnore(@NonNull Object criteria, String... ignore) {
    CrudHelper.startIgnore(criteria, this, ignore);
  }

  /** 执行方法. */
  public void startCrudWithTarget(@NonNull Object criteria, String... target) {
    CrudHelper.startTarget(criteria, this, target);
  }

  /** 包装属性信息. */
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
          val flag = each.value();
          val origin = StringUtil.isBlank(each.field()) ? field.getName() : each.field();
          val method = AND.concat(this.headUp(origin)).concat(flag.get());
          wrappers.add(new Wrapper(data, flag, type, origin, method));
        }
      } else {
        val flag = column.value();
        val origin = StringUtil.isBlank(column.field()) ? field.getName() : column.field();
        val method = AND.concat(this.headUp(origin)).concat(flag.get());
        wrappers.add(new Wrapper(data, flag, type, origin, method));
      }
      return wrappers;
    } catch (IllegalAccessException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  /** 方法名前缀. */
  private static final String AND = "and";

  /** 属性信息包装类. */
  @Getter
  @AllArgsConstructor
  static final class Wrapper {
    @JsonProperty("values")
    final Object values;

    @NonNull
    @JsonIgnore
    final Method flag;

    @NonNull
    @JsonIgnore
    final Class type;

    @NonNull
    @JsonIgnore
    final String origin;

    @NonNull
    final String method;

    boolean in(String... param) {
      return Arrays.asList(param).contains(origin);
    }

    boolean notIn(String... param) {
      return !this.in(param);
    }
  }
}