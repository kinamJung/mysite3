package com.hanains.mysite.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.METHOD ) //위치 지정
@Retention(RetentionPolicy.RUNTIME) //어느 시점에
public @interface Auth {
	
}
