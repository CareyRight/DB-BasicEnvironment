package com.company.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 这个类用来代替web.xml文件
 * 继承这个父类自动配置前端控制器,这个父类中有注册前端控制器的方法
 * tomcat启动时会读取此类,并执行此类中的onStart方法
 */
public class AppWebInitializer
	extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	}
	
	/**
	 * 此方法官方建议加载service,respository配置
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("AppWebInitializer.getRootConfigClasses()");
		return new Class[] {SpringRepository.class,SpringService.class};
	}

	/**
	 * 加载springweb-config配置,此方法官方建议加载controller,viewResovler,..
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("AppWebInitializer.getServletConfigClasses()");
		return new Class[] {SpringWeb.class};
	}
	/*
	 * 定义映射路径,springmvc处理哪些请求,哪些url是交给springmvc处理的
	 */
	@Override
	protected String[] getServletMappings() {
		System.out.println("AppWebInitializer.getServletMappings()");
		return new String[] {"*.do"};
	}
}
