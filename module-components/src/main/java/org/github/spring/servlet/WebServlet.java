package org.github.spring.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.spring.handler.ReturnableValueHandler;
import org.github.spring.resolver.InvokeArgumentResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.google.common.collect.ImmutableList;

import lombok.val;

/**
 * WebServlet.
 *
 * @author JYD_XL
 * @see java.io.Serializable
 * @see javax.servlet.Servlet
 * @see javax.servlet.ServletConfig
 * @see javax.servlet.GenericServlet
 * @see javax.servlet.http.HttpServlet
 * @see org.springframework.beans.factory.Aware
 * @see org.springframework.context.EnvironmentAware
 * @see org.springframework.core.env.EnvironmentCapable
 * @see org.springframework.web.servlet.HttpServletBean
 * @see org.springframework.web.servlet.FrameworkServlet
 * @see org.springframework.context.ApplicationContextAware
 */
@SuppressWarnings("serial")
public class WebServlet extends DispatcherServlet {
  /** field of handlerAdapters. */
  private static final String FIELD_HANDLER_ADAPTERS = "handlerAdapters";
  
  /** field of argumentResolvers. */
  private static final String FIELD_ARGUMENT_RESOLVERS = "argumentResolvers";
  
  /** field of returnValueHandlers. */
  private static final String FIELD_RETURN_VALUE_HANDLERS = "returnValueHandlers";
  
  /** field of handlerExceptionResolvers */
  private static final String FIELD_HANDLER_EXCEPTION_RESOLVERS = "handlerExceptionResolvers";
  
  @Override
  protected void initStrategies(ApplicationContext applicationContext) {
    super.initStrategies(applicationContext);
    try {
      initializing();
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | IOException e) {
      logger.error(e.getMessage(), e);
    }
  }
  
  /**
   * Initializing custom settings.
   * <p>
   * 1.Set the priority of ReturnableValueHandler to maximum.
   * 2.Set the priority of InvokeArgumentResolver to maximum.
   * 3.Set the priority of SystemExceptionResolver to maximum.
   *
   * @throws IOException              Exception
   * @throws SecurityException        Exception
   * @throws NoSuchFieldException     Exception
   * @throws NoSuchMethodException    Exception
   * @throws IllegalAccessException   Exception
   * @throws IllegalArgumentException Exception
   */
  @SuppressWarnings("unchecked")
  private void initializing() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, IOException {
    val handlerAdapters = this.getClass().getSuperclass().getDeclaredField(FIELD_HANDLER_ADAPTERS);
    handlerAdapters.setAccessible(true);
    val handlerAdapterList = (List<Object>) handlerAdapters.get(this);
    Collections.reverse(handlerAdapterList);
    val requestMappingHandlerAdapter = (RequestMappingHandlerAdapter) handlerAdapterList.get(0);
    val clazz = requestMappingHandlerAdapter.getClass();
    
    val argumentResolvers = clazz.getDeclaredField(FIELD_ARGUMENT_RESOLVERS);
    argumentResolvers.setAccessible(true);
    val argumentResolverComposite = (HandlerMethodArgumentResolverComposite) argumentResolvers.get(requestMappingHandlerAdapter);
    val argumentResolverCompositeClass = argumentResolverComposite.getClass();
    val argumentResolversLink = argumentResolverCompositeClass.getDeclaredField(FIELD_ARGUMENT_RESOLVERS);
    argumentResolversLink.setAccessible(true);
    val argumentResolverList = new ArrayList<>((List<HandlerMethodArgumentResolver>) argumentResolversLink.get(argumentResolverComposite));
    argumentResolverList.add(0, new InvokeArgumentResolver());
    argumentResolvers.set(requestMappingHandlerAdapter, new HandlerMethodArgumentResolverComposite().addResolvers(argumentResolverList));
    
    val returnValueHandlers = clazz.getDeclaredField(FIELD_RETURN_VALUE_HANDLERS);
    returnValueHandlers.setAccessible(true);
    val returnValueHandlerComposite = (HandlerMethodReturnValueHandlerComposite) returnValueHandlers.get(requestMappingHandlerAdapter);
    val returnValueHandlerCompositeClass = returnValueHandlerComposite.getClass();
    val returnValueHandlersLink = returnValueHandlerCompositeClass.getDeclaredField(FIELD_RETURN_VALUE_HANDLERS);
    returnValueHandlersLink.setAccessible(true);
    val returnValueHandlerList = new ArrayList<>((List<HandlerMethodReturnValueHandler>) returnValueHandlersLink.get(returnValueHandlerComposite));
    returnValueHandlerList.add(0, new ReturnableValueHandler());
    returnValueHandlers.set(requestMappingHandlerAdapter, new HandlerMethodReturnValueHandlerComposite().addHandlers(returnValueHandlerList));
    
    val handlerExceptionResolvers = this.getClass().getSuperclass().getDeclaredField(FIELD_HANDLER_EXCEPTION_RESOLVERS);
    handlerExceptionResolvers.setAccessible(true);
    val handlerExceptionResolverList = new ArrayList<>((List<HandlerExceptionResolver>) handlerExceptionResolvers.get(this));
//    handlerExceptionResolverList.add(0, new SystemExceptionResolver());
    handlerExceptionResolvers.set(this, ImmutableList.copyOf(handlerExceptionResolverList));
  }
  
  //TODO 待实现...
  @Override
  protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    return super.processHandlerException(request, response, handler, ex);
  }
}
