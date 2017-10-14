package org.github.spring.bootstrap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Holder of application context.
 *
 * @author JYD_XL
 * @see org.springframework.context.ApplicationContextAware
 * @since 0.0.7-SNAPSHOT
 */
public final class ApplicationContextHolder implements ApplicationContextAware {
  /** application context. */
  private static ApplicationContext applicationContext;

  /** GET application context. */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Deprecated
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextHolder.applicationContext = applicationContext;
  }
}
