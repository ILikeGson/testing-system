package org.example.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingServiceAspect {
    private static final Logger LOG = Logger.getLogger(LoggingServiceAspect.class);

    @Around("execution(* org.example.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("Class name: " + joinPoint.getTarget().getClass().getSimpleName());
        LOG.info("Starting method: " + joinPoint.getSignature().getName());
        LOG.info("With args: " + Arrays.toString(joinPoint.getArgs()));

        Object res = joinPoint.proceed();
        LOG.info("Method " + joinPoint.getSignature().toShortString() + " was executed");
        return res;
    }
}
