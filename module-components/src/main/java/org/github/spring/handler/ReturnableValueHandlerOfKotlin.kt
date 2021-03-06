package org.github.spring.handler

import org.github.spring.restful.Returnable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.method.support.ModelAndViewContainer
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object ReturnableValueHandlerOfKotlin : HandlerMethodReturnValueHandler {
  /** log.*/
  private val log: Logger = LoggerFactory.getLogger(javaClass)

  override fun supportsReturnType(returnType: MethodParameter): Boolean {
    return returnType.parameterType is Returnable
  }

  override fun handleReturnValue(returnValue: Any?, returnType: MethodParameter, mavContainer: ModelAndViewContainer, webRequest: NativeWebRequest) {
    val returnable = (returnValue ?: Returnable.of()) as Returnable
    if (returnable.terminal()) {
      try {
        returnable.collect(webRequest.nativeRequest as HttpServletRequest, webRequest.nativeRequest as HttpServletResponse)
      } catch (e: IOException) {
        log.error("exception-file/text/xml/html/json ==> ${e.message}.", e)
      }
    } else {
      mavContainer.viewName = returnable.get()
    }
    mavContainer.isRequestHandled = returnable.terminal()
  }
}