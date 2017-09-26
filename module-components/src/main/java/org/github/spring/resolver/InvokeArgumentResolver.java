package org.github.spring.resolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.github.spring.annotation.Invoke;
import org.github.spring.exception.RunException;
import org.github.spring.footstone.AbstractSpringComponent;
import org.github.spring.footstone.JSONMapperHolder;
import org.github.spring.footstone.XMLMapperHolder;
import org.github.spring.util.StringUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * JSONArgumentResolver.
 *
 * @author JYD_XL
 */
@Slf4j
public class InvokeArgumentResolver extends AbstractSpringComponent implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(Invoke.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    ObjectMapper objectMapper = this.getMapper(parameter);
    String value = parameter.getParameterAnnotation(Invoke.class).value();
    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    try {
      if (StringUtil.isBlank(value)) {
        return objectMapper.readValue(request.getInputStream(), parameter.getParameterType());
      } else {
        return objectMapper.readValue(request.getParameter(value), parameter.getParameterType());
      }
    } catch (IOException e) {
      log.error("exception-invoke ==> " + e.getMessage(), e);
      return null;
    }
  }

  /**
   * getMapper.
   *
   * @param parameter MethodParameter
   * @return MAPPER
   */
  private ObjectMapper getMapper(MethodParameter parameter) {
    switch (parameter.getParameterAnnotation(Invoke.class).style()) {
      case JSON:
        return JSONMapperHolder.getWebJSONMapper();
      case XML:
        return XMLMapperHolder.getWebXMLMapper();
      default:
        throw new RunException();
    }
  }
}
