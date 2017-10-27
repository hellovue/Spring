package org.github.spring.bootstrap;

import javax.servlet.ServletContext;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * Holder of servlet context.
 *
 * @author JYD_XL
 * @see org.springframework.web.context.ServletContextAware
 */
@Component
public final class ServletContextHolder implements ServletContextAware {
  /** GET servlet context. */
  public static ServletContext getServletContext() {
    return servletContext;
  }

  @Deprecated
  @Override
  public void setServletContext(@NotNull ServletContext servletContext) {
    ServletContextHolder.servletContext = servletContext;
  }

  /** servlet context. */
  private static ServletContext servletContext;
}