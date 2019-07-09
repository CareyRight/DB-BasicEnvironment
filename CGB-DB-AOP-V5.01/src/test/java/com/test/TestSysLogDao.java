package com.test;

import org.junit.Test;

import com.db.sys.dao.SysLogDao;

public class TestSysLogDao extends TestBase {
	
	 @Test
	 public void testDeleteObjects() {
		 SysLogDao dao=
		 ctx.getBean("sysLogDao",SysLogDao.class);
		 int rows=dao.deleteObjects(11,12);
		 System.out.println("delete ok,rows="+rows);
	 }
}