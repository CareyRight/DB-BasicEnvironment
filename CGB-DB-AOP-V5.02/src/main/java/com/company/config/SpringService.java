package com.company.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@Configuration  //声明该类是一个配置对象要交给spring去管理,如果有包扫描注解@ComponentScan就不需要再添加当前注解
@ComponentScan(basePackages = {"com.db.sys.service","com.db.common.aspect"})   //配置包扫描,该包下面的类带有@Service注解的类将交由spring管理 

public class SpringService {

}
