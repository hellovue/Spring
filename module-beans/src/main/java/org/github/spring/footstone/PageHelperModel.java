package org.github.spring.footstone;

import java.util.Optional;

import org.github.spring.util.StringUtil;

import org.apache.ibatis.session.RowBounds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageHelper;

/**
 * 分页查询数据模型, 该类的作用是辅助分页及帮助分页时进行排序操作.
 *
 * @author JYD_XL
 * @see org.github.spring.footstone.AbstractEntity
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public final class PageHelperModel extends AbstractEntity {
  /** sort name. */
  private String sortName;

  /** sort order. */
  private String sortOrder = ASC;

  /** page number. */
  private int pageNumber = NUMBER;

  /** page flag. */
  private boolean pageFlag = FLAG;

  /** page size. */
  private int pageSize = SIZE;

  /** Get values pageSize, part or full. */
  @JsonIgnore
  public int getData() {
    return pageFlag ? pageSize : 0;
  }

  /** GET method. */
  public boolean getPageFlag() {
    return pageFlag;
  }

  /** SET method. */
  public void setPageFlag(boolean pageFlag) {
    this.pageFlag = pageFlag;
  }

  /** GET pageNumber. */
  public int getPageNumber() {
    return pageNumber;
  }

  /** SET pageNumber. */
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  /** GET pageSize. */
  public int getPageSize() {
    return pageSize;
  }

  /** SET pageSize. */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  /** Get RowBounds. */
  @JsonIgnore
  @Deprecated
  public RowBounds getRowBounds() {
    return new RowBounds(pageNumber, this.getData());
  }

  /**
   * 获取排序信息,使用手写SQL时使用.
   *
   * @return 排序信息
   */
  @JsonIgnore
  private String getSortInfoByCustom() {
    return Optional.ofNullable(sortName).filter(StringUtil::isNotBlank).map(v -> v.concat(SPACE).concat(sortOrder)).orElse(null);
  }

  /**
   * 获取排序信息,将驼峰命名转为下划线命名,使用自动生成查询语句时使用.
   *
   * @return 排序信息
   */
  @JsonIgnore
  private String getSortInfoByDefault() {
    if (StringUtil.isBlank(sortName)) {return null;}
    StringBuilder column = new StringBuilder();
    for (int i = 0; i < this.sortName.length(); i++) {
      if (Character.isUpperCase(this.sortName.charAt(i))) {
        column.append(UNDER_LINE).append(Character.toLowerCase(this.sortName.charAt(i)));
      } else {
        column.append(this.sortName.charAt(i));
      }
    }
    return column.append(SPACE).append(sortOrder).toString();
  }

  /** GET sortName. */
  public String getSortName() {
    return sortName;
  }

  /** SET sortName. */
  public void setSortName(String sortName) {
    Optional.ofNullable(sortName).filter(v -> PATTERN_PARAM.matcher(v).matches()).ifPresent(v -> this.sortName = v);
  }

  /** GET sortOrder. */
  public String getSortOrder() {
    return sortOrder;
  }

  /** SET sortOrder. */
  public void setSortOrder(String sortOrder) {
    if (ASC.equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) this.sortOrder = sortOrder.toUpperCase();
  }

  /** SET method. */
  public void setFlag(String flag) {
    if (StringUtil.isNotBlank(flag)) this.pageFlag = Boolean.parseBoolean(flag);
  }

  /** SET pageNumber. */
  public void setNumber(String number) {
    if (StringUtil.isNotBlank(number)) this.pageNumber = Integer.parseInt(number);
  }

  /** SET pageSize. */
  public void setSize(String size) {
    if (StringUtil.isNotBlank(size)) this.pageSize = Integer.parseInt(size);
  }

  /** start page. */
  public void startPage() {
    PageHelper.startPage(pageNumber, this.getData());
  }

  /** start page. */
  public void startPageOrderByCustom(String sortInfo) {
    PageHelper.startPage(pageNumber, this.getData(), Optional.ofNullable(this.getSortInfoByCustom()).orElse(sortInfo));
  }

  /** start page. */
  public void startPageOrderByCustom() {
    PageHelper.startPage(pageNumber, this.getData(), this.getSortInfoByCustom());
  }

  /** start page. */
  public void startPageOrderByDefault() {
    PageHelper.startPage(pageNumber, this.getData(), this.getSortInfoByDefault());
  }

  /** start page. */
  public void startPageOrderByDefault(String sortInfo) {
    PageHelper.startPage(pageNumber, this.getData(), Optional.ofNullable(this.getSortInfoByDefault()).orElse(sortInfo));
  }

  /** WITH sortName. */
  public PageHelperModel withColumn(String column) {
    this.setSortName(column);
    return this;
  }

  /** WITH method. */
  public PageHelperModel withFlag(boolean flag) {
    this.setPageFlag(flag);
    return this;
  }

  /** WITH pageNumber. */
  public PageHelperModel withNumber(int number) {
    this.setPageNumber(number);
    return this;
  }

  /** WITH sortOrder. */
  public PageHelperModel withOrder(String order) {
    this.setSortOrder(order);
    return this;
  }

  /** WITH pageSize. */
  public PageHelperModel withSize(int size) {
    this.setPageSize(size);
    return this;
  }

  /** default sort sortOrder. */
  private static final String ASC = "ASC";

  /** default page pageNumber. */
  private static final int NUMBER = 1;

  /** default page pageSize. */
  private static final int SIZE = 10;

  /** default page method. */
  private static final boolean FLAG = true;
}
