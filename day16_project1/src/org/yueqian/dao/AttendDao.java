package org.yueqian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.yueqian.model.Attend;
import org.yueqian.model.User;
import org.yueqian.util.JDBCutil;

/**
 * @author Administrator
 *	attend表相关的数据库访问层
 */
public class AttendDao {
	
	
	/**
	 * @param user
	 * @param attendStatus
	 * @param attendDate
	 * @return
	 * 统计具体考勤状态下的天数
	 */
	public Integer select(User user,Integer attendStatus,String attendDate){
		/*//模拟参数
		
		String attendDate = "2018-08";
		User user = new User(1, "xiaoming123", "123456", "小明");
		
		//指定查询的状态
		Integer attendStatus = 1;*/
		
		//jdbc查询
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "select count(*) record_ from attend where userId = ? and attendStatus = ? and attendDate like ?";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, user.getId());
			
			prepareStatement.setInt(2, attendStatus);
			
			prepareStatement.setString(3, "%"+attendDate+"%");
			
			rs = prepareStatement.executeQuery();
			//接受返回的统计数量
			Integer count = null;
			while(rs.next()){
				//接受返回的统计数量
				count = rs.getInt("record_");
				//System.out.println(count);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, rs);
		}
		
	}

	/**
	 * @param attend
	 * @return
	 * 根据日期和用户Id查询当天的记录
	 */
	public Attend selectByAttendDate(Attend attend) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "select * from attend where userId = ? and attendDate like ?";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, attend.getUserId());
			
			Date date = attend.getAttendDate();
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String attendDate = simpleDateFormat.format(date);
			
			prepareStatement.setString(2, "%"+attendDate+"%");
			
			rs = prepareStatement.executeQuery();
			Attend attendDB = null;
			while(rs.next()){
				//接受返回的统计数量
				Integer id = rs.getInt(1);
				Integer userId = rs.getInt(2);
				java.sql.Date attenddate = rs.getDate(3);
				Timestamp attendMorning = rs.getTimestamp(4);
				Timestamp attendEvening = rs.getTimestamp(5);
				Integer attendStatus = rs.getInt(6);
				attendDB = new Attend(id, userId, new Date(attenddate.getTime()),attendMorning, attendEvening, attendStatus);
			}
			return attendDB;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, rs);
		}
		
	}

	/**
	 * @param attend
	 * @return
	 * 插入早上的打卡记录
	 */
	public String insertAttendMorning(Attend attend) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "insert into attend(userid,attendDate,attendMorning)values(?,?,?)";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, attend.getUserId());
			prepareStatement.setDate(2, new java.sql.Date(attend.getAttendDate().getTime()));
			prepareStatement.setTimestamp(3, new java.sql.Timestamp(attend.getAttendMorning().getTime()));
			int num = prepareStatement.executeUpdate();
			if (num == 1) {
				return "插入成功！";
			}else{
				return "插入失败！";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, null);
		}
	}

	/**
	 * @param attend 
	 * @return
	 * 插入下班打卡的考勤记录
	 */
	public String insertAttendEvening(Attend attend) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "insert into attend(userid,attendDate,attendEvening)values(?,?,?)";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, attend.getUserId());
			prepareStatement.setDate(2, new java.sql.Date(attend.getAttendDate().getTime()));
			prepareStatement.setTimestamp(3, new java.sql.Timestamp(attend.getAttendEvening().getTime()));
			int num = prepareStatement.executeUpdate();
			if (num == 1) {
				return "插入成功！";
			}else{
				return "插入失败！";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, null);
		}
	}

	/**
	 * @param attend
	 * @return
	 * 更新下午打卡的记录
	 */
	public String updateAttendEvening(Attend attend) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "update attend set attendEvening = ? where id = ?";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setTimestamp(1, new java.sql.Timestamp(attend.getAttendEvening().getTime()));
			prepareStatement.setInt(2, attend.getUserId());
			int num = prepareStatement.executeUpdate();
			if (num == 1) {
				return "下班打卡更新成功！";
			}else{
				return "下午打卡更新失败！";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, null);
		}
	}

	/**
	 * @param attendDate
	 * @return
	 * 根据当前的日期获取所有的考勤记录
	 */
	public ArrayList<Attend> selectAllAttendByAttendDate(String attendDate) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "select * from attend where  attendDate like ?";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setString(1, "%"+attendDate+"%");
			
			rs = prepareStatement.executeQuery();
			Attend attendDB = null;
			ArrayList<Attend> attendsDB = new ArrayList<>();
			while(rs.next()){
				//接受返回的统计数量
				Integer id = rs.getInt(1);
				Integer userId = rs.getInt(2);
				java.sql.Date attenddate = rs.getDate(3);
				Timestamp attendMorning = rs.getTimestamp(4);
				Timestamp attendEvening = rs.getTimestamp(5);
				Integer attendStatus = rs.getInt(6);
				attendDB = new Attend(id, userId, new Date(attenddate.getTime()),attendMorning, attendEvening, attendStatus);
				attendsDB.add(attendDB);
			}
			return attendsDB;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, rs);
		}
		
	}

	/**
	 * @param attendanceDayIDs
	 * @param attendanceDayStatus
	 * 更新正常打卡的考勤记录的状态为1
	 */
	public void updateAttendanceAttendStatus(ArrayList<Integer> attendanceDayIDs, Integer attendanceDayStatus) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JDBCutil.getConnection();
			
			String sql = "update attend set attendStatus = ? where id = ?";
					   
			prepareStatement = connection.prepareStatement(sql);
			
			for (Integer id : attendanceDayIDs) {
				prepareStatement.setInt(1, attendanceDayStatus);
				prepareStatement.setInt(2, id);
				prepareStatement.addBatch();
			}
			prepareStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCutil.close(connection, prepareStatement, null);
		}
	
		
	}

}
