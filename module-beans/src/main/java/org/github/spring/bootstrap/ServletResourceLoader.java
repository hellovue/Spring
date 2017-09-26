package org.github.spring.bootstrap;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.support.ServletContextResourceLoader;

import javax.servlet.ServletContext;

/**
 * Servlet资源文件加载器,直接委托给{@link ServletContextResourceLoader}.
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
