package org.github.spring.bootstrap;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * Holder of servlet context.
 *
 * @author JYD_XL
 * @version 0.0.7-SNAPSHOT
 * @see org.springframework.web.context.ServletContextAware
 */
public final class ServletContextHolder implements ServletContextAware {
  /** servlet context. */
  private static ServletContext servletContext;

  /** GET servlet context. */
  public static ServletContext getServletContext() {
    return servletContext;
  }

  @Deprecated
  @Override
  public void setServletContext(ServletContext servletContext) {
    ServletContextHolder.servletContext = servletContext;
  }

  /** GET servlet context. */
  public ServletContext getLetCtx() {
    return servletContext;
  }
}
