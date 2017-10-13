package org.github.spring.footstone;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

/**
 * Abstract class of spring component.
 *
 * @author JYD_XL
 * @see org.springframework.beans.factory.Aware
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.github.spring.footstone.BeansInterface
 * @see org.springframework.beans.factory.BeanNameAware
 * @since 0.0.1-SNAPSHOT
 */
public abstract class AbstractSpringComponent implements Constants, BeanNameAware, ServletContextAware, ApplicationContextAware {
  /** id. */
  protected String id;

  /** servlet context. */
  protected ServletContext servletContext;

  /** application context. */
  protected ApplicationContext applicationContext;

  @Deprecated
  @Override
  public void setBeanName(String beanName) {
    id = beanName;
  }

  @Deprecated
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Deprecated
  @Override
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }
}
