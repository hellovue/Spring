//*****************************************************************************
// The file is automatically generated by the program, do not manually modify. 
//*****************************************************************************

package org.github.spring.base.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.EqualsAndHashCode;
import org.github.spring.base.key.BlogKey;

/**
 * BlogEntity [ sampledb.t_blog ].
 *
 * @author MyBatisGenerator
 * @version 2017-09-21
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class BlogEntity extends BlogKey implements Serializable {
  /** 用户ID（外键） [ t_blog.user_id ]. */
  private String userId;

  /** 文章标题 [ t_blog.title ]. */
  private String title;

  /** 作者 [ t_blog.author ]. */
  private String author;

  /** 文章类型（原创、转载） [ t_blog.flag ]. */
  private String type;

  /** 转载地址 [ t_blog.loadURL ]. */
  private String loadurl;

  /** 文章标签 [ t_blog.label ]. */
  private String label;

  /** 文章描述信息 [ t_blog.decoration ]. */
  private String decoration;

  /** 创建时间 [ t_blog.create_time ]. */
  private Date createTime;

  /** 修改时间 [ t_blog.alter_time ]. */
  private Date alterTime;

  /** 文章状态（未审核、保存、审核通过） [ t_blog.state ]. */
  private String state;

  /**
   * GET 用户ID（外键） [ t_blog.user_id ].
   *
   * @return java.lang.String
   */
  public String getUserId() {
    return userId;
  }

  /**
   * WITH 用户ID（外键） [ t_blog.user_id ].
   *
   * @param userId java.lang.String
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withUserId(String userId) {
    this.setUserId(userId);
    return this;
  }

  /**
   * SET 用户ID（外键） [ t_blog.user_id ].
   *
   * @param userId java.lang.String
   */
  public void setUserId(String userId) {
    this.userId = userId == null ? null : userId.trim();
  }

  /**
   * GET 文章标题 [ t_blog.title ].
   *
   * @return java.lang.String
   */
  public String getTitle() {
    return title;
  }

  /**
   * WITH 文章标题 [ t_blog.title ].
   *
   * @param title java.lang.String
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withTitle(String title) {
    this.setTitle(title);
    return this;
  }

  /**
   * SET 文章标题 [ t_blog.title ].
   *
   * @param title java.lang.String
   */
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  /**
   * GET 作者 [ t_blog.author ].
   *
   * @return java.lang.String
   */
  public String getAuthor() {
    return author;
  }

  /**
   * WITH 作者 [ t_blog.author ].
   *
   * @param author java.lang.String
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withAuthor(String author) {
    this.setAuthor(author);
    return this;
  }

  /**
   * SET 作者 [ t_blog.author ].
   *
   * @param author java.lang.String
   */
  public void setAuthor(String author) {
    this.author = author == null ? null : author.trim();
  }

  /**
   * GET 文章类型（原创、转载） [ t_blog.flag ].
   *
   * @return java.lang.String
   */
  public String getType() {
    return type;
  }

  /**
   * WITH 文章类型（原创、转载） [ t_blog.flag ].
   *
   * @param type java.lang.String
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withType(String type) {
    this.setType(type);
    return this;
  }

  /**
   * SET 文章类型（原创、转载） [ t_blog.flag ].
   *
   * @param type java.lang.String
   */
  public void setType(String type) {
    this.type = type == null ? null : type.trim();
  }

  /**
   * GET 转载地址 [ t_blog.loadURL ].
   *
   * @return java.lang.String
   */
  public String getLoadurl() {
    return loadurl;
  }

  /**
   * WITH 转载地址 [ t_blog.loadURL ].
   *
   * @param loadurl java.lang.String
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withLoadurl(String loadurl) {
    this.setLoadurl(loadurl);
    return this;
  }

  /**
   * SET 转载地址 [ t_blog.loadURL ].
   *
   * @param loadurl java.lang.String
   */
  public void setLoadurl(String loadurl) {
    this.loadurl = loadurl == null ? null : loadurl.trim();
  }

  /**
   * GET 文章标签 [ t_blog.label ].
   *
   * @return java.lang.String
   */
  public String getLabel() {
    return label;
  }

  /**
   * WITH 文章标签 [ t_blog.label ].
   *
   * @param label java.lang.String
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withLabel(String label) {
    this.setLabel(label);
    return this;
  }

  /**
   * SET 文章标签 [ t_blog.label ].
   *
   * @param label java.lang.String
   */
  public void setLabel(String label) {
    this.label = label == null ? null : label.trim();
  }

  /**
   * GET 文章描述信息 [ t_blog.decoration ].
   *
   * @return java.lang.String
   */
  public String getDecoration() {
    return decoration;
  }

  /**
   * WITH 文章描述信息 [ t_blog.decoration ].
   *
   * @param decoration java.lang.String
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withDecoration(String decoration) {
    this.setDecoration(decoration);
    return this;
  }

  /**
   * SET 文章描述信息 [ t_blog.decoration ].
   *
   * @param decoration java.lang.String
   */
  public void setDecoration(String decoration) {
    this.decoration = decoration == null ? null : decoration.trim();
  }

  /**
   * GET 创建时间 [ t_blog.create_time ].
   *
   * @return java.util.Date
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * WITH 创建时间 [ t_blog.create_time ].
   *
   * @param createTime java.util.Date
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withCreateTime(Date createTime) {
    this.setCreateTime(createTime);
    return this;
  }

  /**
   * SET 创建时间 [ t_blog.create_time ].
   *
   * @param createTime java.util.Date
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * GET 修改时间 [ t_blog.alter_time ].
   *
   * @return java.util.Date
   */
  public Date getAlterTime() {
    return alterTime;
  }

  /**
   * WITH 修改时间 [ t_blog.alter_time ].
   *
   * @param alterTime java.util.Date
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withAlterTime(Date alterTime) {
    this.setAlterTime(alterTime);
    return this;
  }

  /**
   * SET 修改时间 [ t_blog.alter_time ].
   *
   * @param alterTime java.util.Date
   */
  public void setAlterTime(Date alterTime) {
    this.alterTime = alterTime;
  }

  /**
   * GET 文章状态（未审核、保存、审核通过） [ t_blog.state ].
   *
   * @return java.lang.String
   */
  public String getState() {
    return state;
  }

  /**
   * WITH 文章状态（未审核、保存、审核通过） [ t_blog.state ].
   *
   * @param state java.lang.String
   * @return org.github.spring.base.entity.BlogEntity
   */
  public BlogEntity withState(String state) {
    this.setState(state);
    return this;
  }

  /**
   * SET 文章状态（未审核、保存、审核通过） [ t_blog.state ].
   *
   * @param state java.lang.String
   */
  public void setState(String state) {
    this.state = state == null ? null : state.trim();
  }
}