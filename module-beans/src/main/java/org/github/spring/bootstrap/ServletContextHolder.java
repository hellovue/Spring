package org.github.spring.bootstrap;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

import static org.github.spring.footstone.BeansInterface.SERVLET_CONTEXT_HOLDER;

@Component(SERVLET_CONTEXT_HOLDER)
public final class ServletContextHolder implements ServletContextAware {
  /** servlet context. */
  private static ServletContext servletContext;

  @Deprecated
  @Override
  public void setServletContext(ServletContext servletContext) {
    ServletContextHolder.servletContext = servletContext;
  }

  public static ServletContext getServletContext() {
    return servletContext;
  }
}
