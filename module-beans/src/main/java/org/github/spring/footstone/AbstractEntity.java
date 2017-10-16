package org.github.spring.footstone;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 抽象实体类, 仅提供方法而不存在属性.
 *
 * @author JYD_XL
 * @since 0.0.1-SNAPSHOT
 */
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Constants, Cloneable, Serializable {
  @Override
  public AbstractEntity clone() {
    try {
      return (AbstractEntity) super.clone();
    } catch (CloneNotSupportedException e) {
      LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
      return null;
    }
  }

  @Override
  public String toString() {
    return JSONMapperHolder.getWebJSONMapper().toJSONString(this);
  }

  /** 创建CRUD辅助数据模型. */
  public CrudHelperModel createCrudHelper() {
    val crudHelperModel = new CrudHelperModel(this);
    LoggerFactory.getLogger(this.getClass()).debug("Generated CrudHelper ==> {}", crudHelperModel);
    return crudHelperModel;
  }

  /** 创建分页辅助数据模型. */
  public PageHelperModel createPageHelper() {
    val pageHelperModel = new PageHelperModel();
    try {
      val request = this.getRequest();
      pageHelperModel.setSortOrder(request.getParameter(FIELD_SORT_ORDER));
      pageHelperModel.setSortName(request.getParameter(FIELD_SORT_NAME));
      pageHelperModel.setFlag(request.getParameter(FIELD_PAGE_FLAG));
      pageHelperModel.setSize(request.getParameter(FIELD_PAGE_SIZE));
      pageHelperModel.setNumber(request.getParameter(FIELD_PAGE_NUMBER));
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
    }

    LoggerFactory.getLogger(this.getClass()).debug("Generated PageHelper ==> {}", pageHelperModel);
    return pageHelperModel;
  }

  /** GET 请求对象(HttpServletRequest). */
  @JsonIgnore
  public HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  /** GET 响应对象(HttpServletResponse). */
  @JsonIgnore
  public HttpServletResponse getResponse() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
  }
}