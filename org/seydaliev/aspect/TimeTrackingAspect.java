package org.seydaliev.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.seydaliev.service.TimeTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.processing.ProcessingEnvironment;

@Aspect
@Component
public class TimeTrackingAspect {

    @Autowired
    private TimeTrackingService service;

    @Pointcut("@annotation(org.seydaliev.annotation.TrackTime)")
    public void trackTimePointcut() {
    }

    @Around("trackTimePointcut()")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            long start = System.currentTimeMillis();
            Object proceed = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            service.saveExecutionTime(joinPoint.getSignature().getName(), executionTime);
            return proceed;
        } catch (Throwable throwable) {
            throw new RuntimeException("Error tracking time", throwable);
        }
    }
}
