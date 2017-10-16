package org.github.spring.bootstrap;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * Holder of servlet context.
 *
 * @author JYD_XL
 * @see org.springframework.web.context.ServletContextAware
 * @since 0.0.1-SNAPSHOT
 */
@Component
public final class ServletContextHolder implements ServletContextAware {
  /** GET servlet context. */
  public static ServletContext getServletContext() {
    return servletContext;
  }

  @Deprecated
  @Override
  public void setServletContext(ServletContext servletContext) {
    ServletContextHolder.servletContext = servletContext;
  }

  /** servlet context. */
  private static ServletContext servletContext;
}