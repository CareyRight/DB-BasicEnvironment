package com.db.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * 测试SpringAOP,重点注意AOP的条件表达式
 * @author 000
 *
 */
@Aspect
@Service
public class TestAspect {
	
	 @Pointcut("bean(searchController)")
	 public void doAspect() {}
    
	 @Before("doAspect()")
	 public void before() {
		 System.out.println("@Before");
	 }
     @After("doAspect()")
     public void after() {
    	 System.out.println("@After");
     }
     @AfterReturning("doAspect()")
     public void afterReturning() {
    	 System.out.println("@AfterReturning");
     }
     @AfterThrowing("doAspect()")
     public void afterThrowing() {
    	 System.out.println("@AfterThrowing");
     }
     @Around("doAspect()")
     public Object around(ProceedingJoinPoint jp)
    		 throws Throwable{
    	 System.out.println("@Around before");
    	 Object result=jp.proceed();
    	 System.out.println("@Around after");
    	 return result;
     }
}