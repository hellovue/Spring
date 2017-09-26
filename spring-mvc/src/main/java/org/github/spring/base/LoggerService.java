package org.github.spring.base;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.github.spring.annotation.Logger;
import org.github.spring.common.service.impl.CommonService;
import org.github.spring.service.ILoggerService;
import org.springframework.stereotype.Service;

@Service
public class LoggerService extends CommonService implements ILoggerService {
  @Override
  public void save(ProceedingJoinPoint joinPoint, Method method, Logger logger) {}
}
