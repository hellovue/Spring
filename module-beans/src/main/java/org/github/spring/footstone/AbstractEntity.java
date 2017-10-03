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
 * Const of entity.
 *
 * @author JYD_XL
 * @see java.io.Closeable
 * @since 1.0.0GA
 */
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable, ConstInterface, BeansInterface, Cloneable {
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

  @JsonIgnore
  public HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  @JsonIgnore
  public HttpServletResponse getResponse() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
  }

  public PageHelperModel createPageHelper() {
    val pageHelperModel = new PageHelperModel();
    try {
      val request = this.getRequest();
      pageHelperModel.setOrder(request.getParameter(FIELD_SORT_ORDER));
      pageHelperModel.setColumn(request.getParameter(FIELD_SORT_NAME));
      pageHelperModel.setFlag(request.getParameter(FIELD_PAGE_FLAG));
      pageHelperModel.setSize(request.getParameter(FIELD_PAGE_SIZE));
      pageHelperModel.setNumber(request.getParameter(FIELD_PAGE_NUMBER));
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
    }

    LoggerFactory.getLogger(this.getClass()).debug("Generated PageHelper ==> {}.", pageHelperModel);
    return pageHelperModel;
  }

  public CrudHelperModel createCrudHelper() {
    val crudHelperModel = new CrudHelperModel(this);
    LoggerFactory.getLogger(this.getClass()).debug("Generated CrudHelper ==> {}.", crudHelperModel);
    return crudHelperModel;
  }
}
