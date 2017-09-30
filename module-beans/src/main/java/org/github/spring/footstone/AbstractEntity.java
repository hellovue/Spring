package org.github.spring.footstone;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Const of entity.
 *
 * @author JYD_XL
 * @see java.io.Serializable
 * @see org.github.spring.footstone.ConstInterface
 */
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable, ConstInterface, BeansInterface {
  @Override
  public String toString() {
    return JSONMapperHolder.getWebJSONMapper().toJson(this);
  }

  @JsonIgnore
  public PageHelperModel getPageHelper() {
    PageHelperModel pageHelperModel = new PageHelperModel();
    try {
      HttpServletRequest request = this.getRequest();
      pageHelperModel.setSortOrder(request.getParameter(FIELD_SORT_ORDER));
      pageHelperModel.setSortName(request.getParameter(FIELD_SORT_NAME));
      pageHelperModel.setPageFlag(request.getParameter(FIELD_PAGE_FLAG));
      pageHelperModel.setPageSize(request.getParameter(FIELD_PAGE_SIZE));
      pageHelperModel.setPageNumber(request.getParameter(FIELD_PAGE_NUMBER));
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
    }
    return pageHelperModel;
  }

  @JsonIgnore
  public CrudHelperModel getCrudHelper() {
    return new CrudHelperModel(this);
  }

  @JsonIgnore
  public HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  @JsonIgnore
  public HttpServletResponse getResponse() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
  }
}
