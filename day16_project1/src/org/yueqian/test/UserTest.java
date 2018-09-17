package org.yueqian.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.yueqian.model.User;
import org.yueqian.util.JDBCutil;

/**
 * @author Administrator
 *	user表的测试类
 */
public class UserTest {
	
	/**
	 * 模拟数据测试登录
	 * @return 
	 */
	@Test
	public void select(){
		//模拟数据
		User user = new User(1, "xiaoming123", "123456", "小明");
		//jdbc查询
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "select *  from user where username = ? and password = ?";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setString(1, user.getUsername());
			
			prepareStatement.setString(2, user.getPassword());
			
			rs = prepareStatement.executeQuery();
			User userDB = null;
			while(rs.next()){
				//接受返回的统计数量
				Integer id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String realname = rs.getString(4);
				userDB = new User(id, username, password, realname);
			}
			if (userDB.getId() != null) {
				System.out.println("登录成功！欢迎"+user.getRealname()+"登录");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, rs);
		}
				
	}

}
