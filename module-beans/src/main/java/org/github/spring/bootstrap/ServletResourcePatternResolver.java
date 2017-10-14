package org.github.spring.bootstrap;

import org.springframework.web.context.support.ServletContextResourcePatternResolver;

public class ServletResourcePatternResolver extends ServletContextResourcePatternResolver {
  /** Constructor. */
  public ServletResourcePatternResolver() {
    super(ServletContextHolder.getServletContext());
  }
}
