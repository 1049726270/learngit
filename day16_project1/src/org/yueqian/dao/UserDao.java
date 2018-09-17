package org.yueqian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.yueqian.model.User;
import org.yueqian.util.JDBCutil;

/**
 * @author Administrator
 *	user表相关的读写交互
 */
public class UserDao {

	
	public User select(User user) {
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
			/*if (userDB != null) {
				return "登录成功！欢迎"+userDB.getRealname()+"登录";
			} else {
				return "用户不存在！请重新登录！";
			}*/
			return userDB;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, rs);
		}
		
	}

}
