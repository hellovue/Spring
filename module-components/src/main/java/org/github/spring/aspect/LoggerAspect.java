package org.github.spring.aspect;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.annotation.Resource;

import org.github.spring.annotation.Logger;
import org.github.spring.service.ILoggerService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class LoggerAspect {
  @Resource
  private ILoggerService loggerService;

  @Around(value = "@annotation(org.github.spring.annotation.Logger)")
  public Object saveLogger(ProceedingJoinPoint joinPoint) throws Throwable {
    String name = joinPoint.getSignature().getName();
    Method method = currentMethod(joinPoint, name);
    if (Objects.nonNull(method)) {
      Logger logger = method.getAnnotation(Logger.class);
      if (Objects.nonNull(logger)) {
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
