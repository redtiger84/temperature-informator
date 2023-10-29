package com.example.largefilereadingchallenge.aspect;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.example.largefilereadingchallenge.services.TemperatureService.*(..))")
    public Object logMemoryUsage(ProceedingJoinPoint joinPoint) throws Throwable {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        long totalMemoryBefore = memoryMXBean.getHeapMemoryUsage().getCommitted();
        long totalUsedMemoryBefore = memoryMXBean.getHeapMemoryUsage().getUsed();
        long freeMemoryBefore = totalMemoryBefore - totalUsedMemoryBefore;

        log.debug("Method name: " + joinPoint.getSignature().getName());
        log.debug("Before method execution - Total Memory: {} Mb", totalMemoryBefore / 1048576);
        log.debug("Before method execution - Free Memory: {} Mb", freeMemoryBefore / 1048576);

        Object result = joinPoint.proceed();

        long totalMemoryAfter = memoryMXBean.getHeapMemoryUsage().getCommitted();
        long totalUsedMemoryAfter = memoryMXBean.getHeapMemoryUsage().getUsed();
        long freeMemoryAfter = totalMemoryAfter - totalUsedMemoryAfter;

        log.debug("After method execution - Total Memory: {} Mb", totalMemoryAfter / 1048576);
        log.debug("After method execution - Free Memory: {} Mb", freeMemoryAfter / 1048576);

        return result;
    }
}

