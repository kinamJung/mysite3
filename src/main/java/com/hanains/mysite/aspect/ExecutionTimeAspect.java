package com.hanains.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component // 컨테이너안에 들어가야 하기 때문에.
public class ExecutionTimeAspect {

	@Around( "execution( * *..dao.*.*(..)) " )
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		
		String taskName =  pjp.getSignature().toString(); // pjp의 정보를 불러온다.
		StopWatch stopWatch = new StopWatch();
		stopWatch.start( taskName );
		
		Object result =  pjp.proceed();
		stopWatch.stop();
		
		System.out.println("[info] [" + taskName + "] Execution Time: " + stopWatch.getTotalTimeMillis());
		
		return result;
	}
	
	
	
	
	
}
