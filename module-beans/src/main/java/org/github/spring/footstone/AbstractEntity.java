package org.github.spring.footstone;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 抽象实体类,仅提供方法而不存在属性.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Constants, Serializable {
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
      val request = this.request();
      pageHelperModel.setSortOrder(request.getParameter(FIELD_SORT_ORDER));
      pageHelperModel.setPageNumber(request.getParameter(FIELD_PAGE_NUMBER));
      pageHelperModel.setSortName(request.getParameter(FIELD_SORT_NAME));
      pageHelperModel.setPageSize(request.getParameter(FIELD_PAGE_SIZE));
      pageHelperModel.setPageFlag(request.getParameter(FIELD_PAGE_FLAG));
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
    }

    LoggerFactory.getLogger(this.getClass()).debug("Generated PageHelper ==> {}", pageHelperModel);
    return pageHelperModel;
  }

  /** GET 请求对象(HttpServletRequest). */
  public HttpServletRequest request() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  /** GET 响应对象(HttpServletResponse). */
  public HttpServletResponse response() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
  }

  public AbstractEntity assertParam() {
    return this;
  }

  public AbstractEntity injectParam() {
    return this;
  }
}