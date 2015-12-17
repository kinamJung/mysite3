package com.hanains.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		
		//이미지 요청이나 CSS요청일 경우 default servlet handler으로 가야한다.
		if( handler instanceof HandlerMethod == false ){
			return true;
		}
		
		Auth auth =  ((HandlerMethod)handler).getMethodAnnotation(Auth.class); 
		
		//Auth Annotation이 안달려 있을때
		if(auth == null){
			return true;
		}
		
		HttpSession session =  request.getSession();
		
		if(session == null){
			response.sendRedirect( request.getContextPath()+"/user/loginform" );
			return false;
		}
		
		UserVo vo = (UserVo)session.getAttribute("authUser");
		if(vo == null){
			response.sendRedirect( request.getContextPath()+"/user/loginform" );
			return false;
		}
		
		return true;
	}
	
	
}
