package com.hanains.mysite.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExecutionLogAspect {

	
	@Around( "execution( * *..dao.*.*(..)) || execution( * *..service.*.*(..)) || execution( * *..controller.*.*(..)) " )
	public Object around(ProceedingJoinPoint pjp) throws Throwable{

		//ProceedingJoinPoint의 정보를 불러와 클래스 이름을 가져온다.
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		
		//가져온 클래스 이름을 가지고 로그입력에 사용한다.
		Log LOG = LogFactory.getLog( signature.getDeclaringType() );
		
		//info log
		LOG.info("[Start] "+signature.getDeclaringType().getName() + " : " + method.getName());
		
		String parameterLogMsg = "";
		/*for( Parameter parameter : method.getParameters() ){
			parameter.get
		}*/
		
		
		Object result =  pjp.proceed();
		
		LOG.info("[End]"+signature.getDeclaringType().getName() + " : " + method.getName());
		return result;
	}
	
	
	
}
