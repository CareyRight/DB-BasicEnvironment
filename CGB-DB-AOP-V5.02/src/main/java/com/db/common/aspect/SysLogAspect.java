package com.db.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.db.sys.controller.SearchController;


/**
 * OCP原则:不改变原先业务功能,增加扩展业务
 * 通过此类为系统中的某些业务操作添加扩展功能--日志扩展
 * @author 000
 * 需要将此类声明为切面对象
 * @Aspect 该注解描述的类为一个切面对象,类中通常会有两大部分构成:
 * 1.pointCut--切入点(要植入扩展功能的点)
 * 2.advice--通知(扩展功能)
 */
@Order(1) //切面的优先级,数字越小等级越高
@Aspect
@Service //切面处理的扩展业务也属于业务层
public class SysLogAspect {
	
	/**
	 * 定义切入点:
	 * 1.借助于@Pointcut注解
	 * 2.bean(sysUserServiceImpl),一般写bean的名字或表达式
	 * 其语法结构:
	 * 1.bean(bean的名字),例如:bean(sysUserServiceImpl)
	 * 2.bean(bean的表达式),例如:bean(*serviceImpl),后缀为serviceImpl的类名,通配符表达式
	 */
	@Pointcut("bean(searchController)")
	public void doLog() { }
	
	/**
	 * @Around 此注解描述的方法为一个通知(环绕通知),该通知中可以在目标方法执行之前和之后做一些事情
	 * @param jp连接点对象,封装了目标方法信息
	 * @return 返回值是目标方法的执行结果
	 * @throws Throwable
	 * 
	 */
	@Around("doLog()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		long t1=System.currentTimeMillis();
		Object result = jp.proceed(); //执行下一个切面方法,没有下一个切面则执行目标方法,不调用目标方法不会执行
		long t2=System.currentTimeMillis();
		//如何输出那个类的哪个方法执行时长?
		//消费了这么长时间?
		//获取方法对象
		Method method = getTargedMethod(jp);
		//获取方法名
		String methodName = getTargetMethodName(method);
		//输出方法名和执行时长
		Long titleTime=(t2-t1);
		System.out.println(methodName+" execution time"+titleTime);
		saveObject(jp,titleTime);
		return result;
	}
	
	/**
	 * 获取目标方法的名称:类全名+方法名
	 * @param method 目标方法对象
	 * @return 返回目标方法的类全名+方法名
	 */
	private String getTargetMethodName(Method method) {
		return new StringBuilder(method.getDeclaringClass().getName())
				   .append(".").append(method.getName()).toString();
	}
	
	/**
	 * 基于连接点信息,获取目标方法对象
	 * @param jp 连接点对象,封装了目标方法信息
	 * @return 返回一个方法对象
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private Method getTargedMethod(ProceedingJoinPoint jp) throws NoSuchMethodException, SecurityException {
		//1.获取目标方法类对象(字节码对象)
		Class<?> targetClass = jp.getTarget().getClass();
		//2.获取方法签名信息(包含方法名,参数列表等信息)
		MethodSignature s = (MethodSignature)jp.getSignature();
		//获取目标方法对象
		Method method = targetClass.getDeclaredMethod(s.getName(), s.getParameterTypes());
		return method;
	} 
	
	/**
	 * 抓取用户的行为记录并插入到数据库日志表记录中
	 * @param jp
	 * @param titleTime
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private void saveObject(ProceedingJoinPoint jp,long titleTime) throws NoSuchMethodException, SecurityException {
		//1.获取用户行为日志信息
		//1.1.获取方法对象
		Method method = getTargedMethod(jp);
		//1.2.获取目标方法全名(包名类名方法名)
		String methodName = getTargetMethodName(method);
		//1.3.获取方法参数
		String params = Arrays.toString(jp.getArgs());
		//1.4.从session中获取用户信息,并获取用户名
//		String username = ShiroUtil.getUser().getUsername();
		//1.5.给定操作名称
		String operation = "operation";
		//1.6获取主机IP
		String ip = IPUtils.getIpAddr();
		//1.7获取方法上的注解对象
//		RequiredLog rlog = method.getDeclaredAnnotation(RequiredLog.class);
//		if (rlog!=null&&!StringUtils.isEmpty(rlog.value())) {
//			operation=rlog.value();
//		}
		//2.获取目标方法对象
		jp.getTarget().getClass();
		//2.1.封装用户行为日志信息
		SysLog log = new SysLog();
		log.setMethod(methodName);
		log.setParams(params);
		log.setOperation(operation);
		log.setUsername("admin");
		log.setTime(titleTime);
		log.setCreatedTime(new Date());
		log.setIp(ip);
		Logger Log4j = Logger.getLogger(SearchController.class);
		Log4j.info(log.toString());
	}
}
