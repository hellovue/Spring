package org.github.spring.footstone;

import javax.servlet.ServletContext;

import lombok.Getter;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

/**
 * Spring组件抽象类,所有自定义Spring组件都继承自该类.
 *
 * @author JYD_XL
 * @see org.github.spring.footstone.AbstractEntity
 * @see org.springframework.beans.factory.BeanNameAware
 * @see org.springframework.context.ApplicationContextAware
 * @see org.springframework.web.context.ServletContextAware
 */
@Getter
public abstract class AbstractSpringComponent extends AbstractEntity implements Constants, BeanNameAware, ServletContextAware, ApplicationContextAware {
  /** id. */
  private String id;

  /** servlet context. */
  private ServletContext servletContext;

  /** application context. */
  private ApplicationContext applicationContext;

  @Deprecated
  @Override
  public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Deprecated
  @Override
  public void setBeanName(@NotNull String beanName) {
    id = beanName;
  }

  @Deprecated
  @Override
  public void setServletContext(@NotNull ServletContext servletContext) {
    this.servletContext = servletContext;
  }
}