package org.github.spring.footstone;

import java.util.Optional;

import org.github.spring.util.StringUtil;

import org.apache.ibatis.session.RowBounds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageHelper;

/**
 * 分页查询数据模型.
 * <p>
 * 该类的作用是辅助分页及帮助分页时进行排序操作.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public final class PageHelperModel implements ConstInterface {
  /** sort name. */
  private String sortName = EMPTY;

  /** sort order. */
  private String sortOrder = ASC;

  /** page size. */
  private int pageSize = PAGE_SIZE;

  /** page number. */
  private int pageNumber = PAGE_NUMBER;

  /** page flag. */
  private boolean pageFlag = PAGE_FLAG;

  /**
   * GET pageSize.
   *
   * @return int
   */
  public int getPageSize() {
    return pageSize;
  }

  /**
   * SET pageSize.
   *
   * @param pageSize String
   */
  public void setPageSize(String pageSize) {
    if (StringUtil.isNotBlank(pageSize)) this.pageSize = Integer.parseInt(pageSize);
  }

  /**
   * GET pageNumber.
   *
   * @return int
   */
  public int getPageNumber() {
    return pageNumber;
  }

  /**
   * SET pageNumber.
   *
   * @param pageNumber String
   */
  public void setPageNumber(String pageNumber) {
    if (StringUtil.isNotBlank(pageNumber)) this.pageNumber = Integer.parseInt(pageNumber);
  }

  /**
   * GET sortName.
   *
   * @return String
   */
  public String getSortName() {
    return sortName;
  }

  /**
   * SET sortName.
   *
   * @param sortName String
   */
  public void setSortName(String sortName) {
    Optional.ofNullable(sortName).filter(v -> PATTERN_PARAM.matcher(v).matches()).ifPresent(v -> this.sortName = v);
  }

  /**
   * GET sortOrder.
   *
   * @return String
   */
  public String getSortOrder() {
    return sortOrder;
  }

  /**
   * SET sortOrder.
   *
   * @param sortOrder String
   */
  public void setSortOrder(String sortOrder) {
    if (ASC.equalsIgnoreCase(sortOrder) || DESC.equalsIgnoreCase(sortOrder)) this.sortOrder = sortOrder.toUpperCase();
  }

  /**
   * GET pageFlag.
   *
   * @return boolean
   */
  public boolean getPageFlag() {
    return pageFlag;
  }

  /**
   * SET pageFlag.
   *
   * @param pageFlag String
   */
  public void setPageFlag(String pageFlag) {
    if (StringUtil.isNotBlank(pageFlag)) this.pageFlag = Boolean.parseBoolean(pageFlag);
  }

  /**
   * 获取排序信息,将驼峰命名转为下划线命名,使用自动生成查询语句时使用.
   *
   * @return 排序信息
   */
  private String getSortInfoByDefault() {
    if (StringUtil.isBlank(sortName)) {return null;}
    StringBuilder column = new StringBuilder();
    for (int i = 0; i < sortName.length(); i++) {
      if (Character.isUpperCase(sortName.charAt(i))) {
        column.append(UNDER_LINE).append(Character.toLowerCase(sortName.charAt(i)));
      } else {
        column.append(sortName.charAt(i));
      }
    }
    return column.append(SPACE).append(sortOrder).toString();
  }

  /**
   * 获取排序信息,使用手写SQL时使用.
   *
   * @return 排序信息
   */
  private String getSortInfoByCustom() {
    return Optional.of(sortName).filter(StringUtil:: isNotBlank).map(v -> v.concat(SPACE).concat(sortOrder)).orElse(null);
  }

  /**
   * Get RowBounds.
   *
   * @return RowBounds
   */
  @JsonIgnore
  @Deprecated
  public RowBounds getRowBounds() {
    return new RowBounds(pageNumber, this.getDataSize());
  }

  /** Get data size,part or full. */
  @JsonIgnore
  public int getDataSize() {
    return pageFlag ? pageSize : PAGE_ZERO;
  }

  /**
   * SET pageSize.
   *
   * @param pageSize int
   */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  /**
   * SET pageNumber.
   *
   * @param pageNumber int
   */
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  /**
   * SET pageFlag.
   *
   * @param pageFlag boolean
   */
  public void setPageFlag(boolean pageFlag) {
    this.pageFlag = pageFlag;
  }

  /**
   * WITH pageSize.
   *
   * @param pageSize int
   * @return PageHelperModel
   */
  public PageHelperModel withPageSize(int pageSize) {
    this.setPageSize(pageSize);
    return this;
  }

  /**
   * WITH pageNumber.
   *
   * @param pageNumber int
   * @return PageHelperModel
   */
  public PageHelperModel withPageNumber(int pageNumber) {
    this.setPageNumber(pageNumber);
    return this;
  }

  /**
   * WITH sortName.
   *
   * @param sortName String
   * @return PageHelperModel
   */
  public PageHelperModel withSortName(String sortName) {
    this.setSortName(sortName);
    return this;
  }

  /**
   * WITH sortOrder.
   *
   * @param sortOrder String
   * @return PageHelperModel
   */
  public PageHelperModel withSortOrder(String sortOrder) {
    this.setSortOrder(sortOrder);
    return this;
  }

  /**
   * WITH pageFlag.
   *
   * @param pageFlag boolean
   * @return PageHelperModel
   */
  public PageHelperModel withPageFlag(boolean pageFlag) {
    this.setPageFlag(pageFlag);
    return this;
  }

  /**
   * Perform page.
   *
   * @return PageHelperModel
   */
  public void startPageOrderByDefault() {
    PageHelper.startPage(pageNumber, this.getDataSize(), this.getSortInfoByDefault());
  }

  /**
   * Perform page.
   *
   * @return PageHelperModel
   */
  public void startPageOrderByDefault(String sortInfo) {
    PageHelper.startPage(pageNumber, this.getDataSize(), Optional.ofNullable(this.getSortInfoByDefault()).orElse(sortInfo));
  }

  /**
   * Perform page.
   *
   * @return PageHelperModel
   */
  public void startPageOrderByCustom(String sortInfo) {
    PageHelper.startPage(pageNumber, this.getDataSize(), Optional.ofNullable(this.getSortInfoByCustom()).orElse(sortInfo));
  }

  /**
   * Perform page.
   *
   * @return PageHelperModel
   */
  public void startPageOrderByCustom() {
    PageHelper.startPage(pageNumber, this.getDataSize(), this.getSortInfoByCustom());
  }

  /**
   * Perform page.
   *
   * @return PageHelperModel
   */
  public void startPage() {
    PageHelper.startPage(pageNumber, this.getDataSize());
  }
}
