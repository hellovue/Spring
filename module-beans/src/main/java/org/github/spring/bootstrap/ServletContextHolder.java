package org.github.spring.bootstrap;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public final class ServletContextHolder implements ServletContextAware {
  /** servlet context. */
  private static ServletContext servletContext;

  public static ServletContext getServletContext() {
    return servletContext;
  }

  @Deprecated
  @Override
  public void setServletContext(ServletContext servletContext) {
    ServletContextHolder.servletContext = servletContext;
  }
}
