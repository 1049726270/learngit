package org.yueqian.test;

import java.sql.Connection;

import org.junit.Test;
import org.yueqian.util.JDBCutil;

/**
 * @author Administrator
 *	jdbc连接的测试类
 */
public class JdbcTest {

	/**
	 * Junit测试jdbc连接
	 * @throws Exception 
	 */
	@Test
	public void jdbcTest() throws Exception{
		Connection connection = JDBCutil.getConnection();
		System.out.println(connection);
	}
	
}
