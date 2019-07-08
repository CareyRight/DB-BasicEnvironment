package com.db.sys.dao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
/**
 * spring-respository.xml 此
 * 文件中定义了一个MapperScannerConfigurer
 * 对象,此对象会对BasePackage属性指定的包
 * 下接口进行扫描,然后会为接口创建实现类的对象,
 * 并将这个对象存储到bean池,其key为接口名(首字母小写).
 */
public interface SysLogDao {//SysLogDao.class

	/**
	 * 执行日志删除操作
	 * @param id
	 * @return
	 */

	int deleteObjects(@Param("ids")Integer... id);
}
