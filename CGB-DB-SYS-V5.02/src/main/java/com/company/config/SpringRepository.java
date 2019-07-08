package com.company.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
 
import com.alibaba.druid.pool.DruidDataSource;
@PropertySource("classpath:db.properties")
@MapperScan({"com.db.*.dao"})   //配置mapper接口扫描,该包下面的类带有@Service注解的类将交由spring管理 
public class SpringRepository {

	//读取properties文件中的内容
	@Value("${jdbcDriver}")
	private String driverClass;
	
	@Value("${jdbcUrl}")
	private String url;
	
	@Value("${jdbcUser}")
	private String username;
	
	@Value("${jdbcPassword}")
	private String password;
	
	//整合第三方连接池
	//整合第三方bean对象时通常使用@Bean注解
	@Scope("singleton")
	@Lazy(false)
	@Bean(value = "dataSource", initMethod = "init", destroyMethod = "close")
	public DataSource newDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driverClass);
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		return druidDataSource;
	}
	
	//2.整合mybatis,整合的是factoryBean对象,等到调用getObject()方法时返回的是beanFactory对象,openSession()拿到session对象
	@Bean("sqlSessionFactory")
	public SqlSessionFactoryBean newSqlSessionFactoryBean() throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(newDataSource());
		Resource[] mapperLocations = 
				new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/sys/*Mapper.xml");
		factoryBean.setMapperLocations(mapperLocations);
		return factoryBean;
	}
}
