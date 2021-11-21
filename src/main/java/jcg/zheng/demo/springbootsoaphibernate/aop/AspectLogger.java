package jcg.zheng.demo.springbootsoaphibernate.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {
	@Around("@annotation(LoggableService)")
	public Object logExecutionTimeAndSignature(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		Object proceed = joinPoint.proceed();

		long executionTime = System.currentTimeMillis() - start;

		logExecutionTimeAndSignature(joinPoint, executionTime);
		return proceed;
	}

	@Async
	public void logExecutionTimeAndSignature(ProceedingJoinPoint joinPoint, long executionTime) {
		String className = joinPoint.getTarget().getClass().getName();

		Logger logger = LoggerFactory.getLogger(className);

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();

		final String[] parameterNames = methodSignature.getParameterNames();

		StringBuilder argsMsg = new StringBuilder();
		for (int paramIndex = 0; paramIndex < parameterNames.length; paramIndex++) {
			String paramName = parameterNames[paramIndex];
			Object paramValue = args[paramIndex];
			argsMsg.append(paramName).append("=").append(paramValue).append(",");
		}

		String message = "method=" + methodSignature.getName() + ", " + argsMsg.toString() + " executed in "
				+ executionTime + "ms";

		logger.info(message);
	}

}
