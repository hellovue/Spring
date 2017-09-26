package org.github.spring.base;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BindingResult;

/** 采用AOP的方式处理参数. */
@Slf4j
public class BindingResultAop {
    @Pointcut("execution(org.github.spring.base.BaseResult org.github..*.*(..))")
    public void aopMethod() {}

    @Around("aopMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("before method invoking!");
        BindingResult bindingResult = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                bindingResult = (BindingResult) arg;
            }
        }
        if (bindingResult != null) {
            if (bindingResult.hasErrors()) {
                String errorInfo = "[" + bindingResult.getFieldError().getField() + "]" + bindingResult.getFieldError().getDefaultMessage();
                return new BaseResult<>(false, errorInfo);
            }
        }
        return joinPoint.proceed();
    }
}
