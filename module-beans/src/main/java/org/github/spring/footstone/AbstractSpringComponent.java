package org.github.spring.footstone;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

/**
 * Spring组件抽象类, 所有自定义Spring组件都继承自该类.
 *
 * @author JYD_XL
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.springframework.beans.factory.BeanNameAware
 * @see org.springframework.context.ApplicationContextAware
 * @see org.springframework.web.context.ServletContextAware
 * @since 0.0.1-SNAPSHOT
 */
public abstract class AbstractSpringComponent extends AbstractEntity implements Constants, BeanNameAware, ServletContextAware, ApplicationContextAware {
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
