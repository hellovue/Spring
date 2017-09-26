package org.github.spring.aspect;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.github.spring.annotation.Logger;
import org.github.spring.service.ILoggerService;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;
import static org.github.spring.footstone.BeansInterface.LOGGER_ASPECT;

@Component(LOGGER_ASPECT)
public class LoggerAspect {
  @Resource
  private ILoggerService loggerService;

  @Around(value = "@annotation(org.github.spring.annotation.Logger)")
  public Object saveLogger(ProceedingJoinPoint joinPoint) throws Throwable {
    String name = joinPoint.getSignature().getName();
    Method method = currentMethod(joinPoint, name);
    if (nonNull(method)) {
      Logger logger = method.getAnnotation(Logger.class);
      if (nonNull(logger)) {
        loggerService.save(joinPoint, method, logger);
      }
    }
    return joinPoint.proceed();
  }

  private Method currentMethod(ProceedingJoinPoint joinPoint, String name) {
    Method[] methods = joinPoint.getTarget().getClass().getMethods();
    for (Method method : methods) {
      if (method.getName().equals(name)) return method;
    }
    return null;
  }
}
