package com.test;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.company.config.SpringRepository;
import com.company.config.SpringService;
import com.db.common.aspect.TestAspect;
import com.db.sys.dao.SysLogDao;

public class TestBase {
	protected AnnotationConfigApplicationContext ctx;
	@Before
	public void init() {
		ctx=new AnnotationConfigApplicationContext(SpringRepository.class,SpringService.class);
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
	
	//对动态代理进行单元测试
	@Test
	public void testDao()throws Exception {
		SysLogDao ssf=
				ctx.getBean("sysLogDao", SysLogDao.class);
		System.out.println(ssf);
	}
	//对切面进行单元测试
	@Test
	public void testAspect()throws Exception {
		TestAspect ssf=
				ctx.getBean("testAspect", TestAspect.class);
		System.out.println(ssf);
	}
}