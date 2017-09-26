package org.github.spring.service;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.github.spring.annotation.Logger;

public interface ILoggerService {
  void save(ProceedingJoinPoint joinPoint, Method method, Logger logger);
}
