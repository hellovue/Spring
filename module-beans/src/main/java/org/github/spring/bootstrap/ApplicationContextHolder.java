package org.github.spring.bootstrap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import static org.github.spring.footstone.BeansInterface.APPLICATION_CONTEXT_HOLDER;

@Component(APPLICATION_CONTEXT_HOLDER)
public final class ApplicationContextHolder implements ApplicationContextAware {
  /** application context. */
  private static ApplicationContext applicationContext;

  @Deprecated
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextHolder.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
}
