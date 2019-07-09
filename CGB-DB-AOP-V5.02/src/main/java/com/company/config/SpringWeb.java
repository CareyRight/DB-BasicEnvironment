package com.company.config;
/**
 * 这个类用来代替spring-web.xml配置文件
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan(value = {"com.db.sys.controller","com.db.sys.common.web"})
@EnableWebMvc  //启动mvc默认配置,注册spring中默认的bean对象,等效于spring-config.xml文件中的<mvc:annotation-driven/>标签
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SpringWeb extends WebMvcConfigurerAdapter{ 
	//继承WebMvcConfigurerAdapter类,重写configureViewResolvers()方法,配置视图解析器
	 @Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		 registry.jsp("/WEB-INF/pages/", ".html"); //手动指定前缀和后缀
	}
	 
}
