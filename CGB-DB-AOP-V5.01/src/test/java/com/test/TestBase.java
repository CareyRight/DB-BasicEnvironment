package com.test;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBase {
	protected ClassPathXmlApplicationContext ctx;
	@Before
	public void init() {
		ctx=new ClassPathXmlApplicationContext("spring/spring-config.xml");
	}
	@Test
	public void testCtx() {
		System.out.println(ctx);
	}
	@After
	public void destory() {
		ctx.close();
	}
	
	//测试数据源druid
	@Test
	public void testDruidDataSource()throws Exception {
		DataSource ds=ctx.getBean("dataSource", DataSource.class);
		System.out.println(ds.getConnection());
	}
	
	//对mybatis进行单元测试
	@Test
	public void testSqlSessionFactory()throws Exception {
		SqlSessionFactory ssf=
		ctx.getBean("sqlSessionFactory", SqlSessionFactory.class);
		System.out.println(ssf);
	}
}