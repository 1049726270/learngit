package org.yueqian.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;
import org.yueqian.model.Attend;
import org.yueqian.model.User;
import org.yueqian.util.JDBCutil;

/**
 * @author Administrator
 *	attend表相关的测试
 */
public class AttendTest {

	/**
	 * 插入正常的打卡记录
	 */
	@Test
	public void insert(){
		User user = new User(1, "xiaoming123", "123456", "小明");
		//1.需要模拟数据
		
		
		Attend attend = new Attend();
		attend.setUserId(user.getId());
		attend.setAttendDate(new Date());
		attend.setAttendMorning(new Date());
		attend.setAttendEvening(new Date());
		//shif + enter 换行
		attend.setAttendStatus(1);
		//2.进行jdbc的插入操作
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "insert into attend(userid,attendDate,attendMorning,attendEvening,attendStatus)values(?,?,?,?,?)";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, attend.getUserId());
			prepareStatement.setDate(2, new java.sql.Date(attend.getAttendDate().getTime()));
			prepareStatement.setTimestamp(3, new java.sql.Timestamp(attend.getAttendMorning().getTime()));
			prepareStatement.setTimestamp(4, new java.sql.Timestamp(attend.getAttendEvening().getTime()));
			prepareStatement.setInt(5, attend.getAttendStatus());
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, null);
		}
		
	}
	@Test
	public void select(){
		//模拟参数
		
		String date = "2018-08";
		User user = new User(1, "xiaoming123", "123456", "小明");
		
		//指定查询的状态
		Integer attendStatus = 1;
		
		//jdbc查询
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "select count(*) record_ from attend where userId = ? and attendStatus = ?";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, user.getId());
			
			prepareStatement.setInt(2, attendStatus);
			
			rs = prepareStatement.executeQuery();
			while(rs.next()){
				//接受返回的统计数量
				Integer count = rs.getInt("record_");
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, rs);
		}
		
	}
	
}
