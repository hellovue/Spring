package org.github.spring.bootstrap;

import javax.servlet.ServletContext;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.support.ServletContextResourceLoader;

/**
 * ServletResourceLoader.
 *
 * @author JYD_XL
 * @see org.springframework.core.io.ResourceLoader
 * @see org.springframework.core.io.DefaultResourceLoader
 * @see org.springframework.web.context.support.ServletContextResourceLoader
 */
public class ServletResourceLoader extends ServletContextResourceLoader implements ResourceLoader {
  /** Constructor. */
  public ServletResourceLoader() {
    this(ServletContextHolder.getServletContext());
  }

  /** Constructor. */
  public ServletResourceLoader(ServletContext servletContext) {
    super(servletContext);
  }

  @Override
  public Resource getResource(String location) {
    return this.getResourceByPath(location);
  }
}
