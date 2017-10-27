package org.github.spring.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.github.spring.footstone.AbstractSpringComponent;
import org.github.spring.restful.Returnable;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * ReturnableValueHandler.
 *
 * @author JYD_XL
 * @see org.springframework.web.method.support.HandlerMethodReturnValueHandler
 */
@Slf4j
public class ReturnableValueHandler extends AbstractSpringComponent implements HandlerMethodReturnValueHandler {
  @Override
  public boolean supportsReturnType(@NotNull MethodParameter returnType) {
    return Returnable.class.isAssignableFrom(returnType.getParameterType());
  }

  @Override
  public void handleReturnValue(@Nullable Object returnValue, @NotNull MethodParameter returnType, @NotNull ModelAndViewContainer mavContainer, @NotNull NativeWebRequest webRequest) {
    if (returnValue == null) returnValue = Returnable.of();
    val returnable = (Returnable) returnValue;
    if (returnable.terminal()) {
      try {
        returnable.collect(webRequest.getNativeRequest(HttpServletRequest.class), webRequest.getNativeResponse(HttpServletResponse.class));
      } catch (IOException e) {
        log.error("exception-file/text/xml/html/json ==> " + e.getMessage(), e);
      }
    } else {
      mavContainer.setViewName(returnable.get());
    }
    mavContainer.setRequestHandled(returnable.terminal());
  }
}