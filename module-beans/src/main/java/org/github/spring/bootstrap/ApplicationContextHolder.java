package org.github.spring.bootstrap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class ApplicationContextHolder implements ApplicationContextAware {
  /** application context. */
  private static ApplicationContext applicationContext;

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Deprecated
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextHolder.applicationContext = applicationContext;
  }
}
