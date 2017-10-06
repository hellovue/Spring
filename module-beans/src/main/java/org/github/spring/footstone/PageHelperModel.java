package org.github.spring.footstone;

import java.util.Optional;

import org.github.spring.util.StringUtil;

import org.apache.ibatis.session.RowBounds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageHelper;

/**
 * 分页查询数据模型.
 *
 * 该类的作用是辅助分页及帮助分页时进行排序操作.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public final class PageHelperModel extends AbstractEntity {
  /** sort column. */
  private String column;

  /** sort order. */
  private String order = ASC;

  /** page number. */
  private int number = NUMBER;

  /** page flag. */
  private boolean flag = FLAG;

  /** page size. */
  private int size = SIZE;

  /** GET size. */
  public int getSize() {
    return size;
  }

  /** SET size. */
  public void setSize(int size) {
    this.size = size;
  }

  /** GET number. */
  public int getNumber() {
    return number;
  }

  /** SET number. */
  public void setNumber(int number) {
    this.number = number;
  }

  /** GET column. */
  public String getColumn() {
    return column;
  }

  /** SET column. */
  public void setColumn(String column) {
    Optional.ofNullable(column).filter(v -> PATTERN_PARAM.matcher(v).matches()).ifPresent(v -> this.column = v);
  }

  /** GET order. */
  public String getOrder() {
    return order;
  }

  /** SET order. */
  public void setOrder(String order) {
    if (ASC.equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) this.order = order.toUpperCase();
  }

  /** GET flag. */
  public boolean getFlag() {
    return flag;
  }

  /** SET flag. */
  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  /**
   * 获取排序信息,将驼峰命名转为下划线命名,使用自动生成查询语句时使用.
   *
   * @return 排序信息
   */
  @JsonIgnore
  private String getSortInfoByDefault() {
    if (StringUtil.isBlank(column)) {return null;}
    StringBuilder column = new StringBuilder();
    for (int i = 0; i < this.column.length(); i++) {
      if (Character.isUpperCase(this.column.charAt(i))) {
        column.append(UNDER_LINE).append(Character.toLowerCase(this.column.charAt(i)));
      } else {
        column.append(this.column.charAt(i));
      }
    }
    return column.append(SPACE).append(order).toString();
  }

  /**
   * 获取排序信息,使用手写SQL时使用.
   *
   * @return 排序信息
   */
  @JsonIgnore
  private String getSortInfoByCustom() {
    return Optional.ofNullable(column).filter(StringUtil::isNotBlank).map(v -> v.concat(SPACE).concat(order)).orElse(null);
  }

  /** Get RowBounds. */
  @JsonIgnore
  @Deprecated
  public RowBounds getRowBounds() {
    return new RowBounds(number, this.getData());
  }

  /** Get data size, part or full. */
  @JsonIgnore
  public int getData() {
    return flag ? size : 0;
  }

  /** SET size. */
  public void setSize(String size) {
    if (StringUtil.isNotBlank(size)) this.size = Integer.parseInt(size);
  }

  /** SET number. */
  public void setNumber(String number) {
    if (StringUtil.isNotBlank(number)) this.number = Integer.parseInt(number);
  }

  /** SET flag. */
  public void setFlag(String flag) {
    if (StringUtil.isNotBlank(flag)) this.flag = Boolean.parseBoolean(flag);
  }

  /** WITH size. */
  public PageHelperModel withSize(int size) {
    this.setSize(size);
    return this;
  }

  /** WITH number. */
  public PageHelperModel withNumber(int number) {
    this.setNumber(number);
    return this;
  }

  /** WITH column. */
  public PageHelperModel withColumn(String column) {
    this.setColumn(column);
    return this;
  }

  /** WITH order. */
  public PageHelperModel withOrder(String order) {
    this.setOrder(order);
    return this;
  }

  /** WITH flag. */
  public PageHelperModel withFlag(boolean flag) {
    this.setFlag(flag);
    return this;
  }

  /** start page. */
  public void startPageOrderByDefault() {
    PageHelper.startPage(number, this.getData(), this.getSortInfoByDefault());
  }

  /** start page. */
  public void startPageOrderByDefault(String sortInfo) {
    PageHelper.startPage(number, this.getData(), Optional.ofNullable(this.getSortInfoByDefault()).orElse(sortInfo));
  }

  /** start page. */
  public void startPageOrderByCustom(String sortInfo) {
    PageHelper.startPage(number, this.getData(), Optional.ofNullable(this.getSortInfoByCustom()).orElse(sortInfo));
  }

  /** start page. */
  public void startPageOrderByCustom() {
    PageHelper.startPage(number, this.getData(), this.getSortInfoByCustom());
  }

  /** start page. */
  public void startPage() {
    PageHelper.startPage(number, this.getData());
  }

  /** default sort order. */
  private static final String ASC = "ASC";

  /** default page number. */
  private static final int NUMBER = 1;

  /** default page size. */
  private static final int SIZE = 10;

  /** default page flag. */
  private static final boolean FLAG = true;
}
