package org.yueqian.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author peng
 *	jdbc工具类2
 */
public class JDBCutil {
	
	private static final String dbconfig = "dbconfig.properties";
	
	private static Properties properties = new Properties();
	
	static{
		try {
			properties.load(JDBCutil.class.getResourceAsStream(dbconfig));
			Class.forName(properties.getProperty("driverClassName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(properties.getProperty("url"),
					properties.getProperty("username"), properties.getProperty("password"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close(Connection con , PreparedStatement stmt, ResultSet rs) {
			
			try {
				if(rs!=null){
					rs.close();	
				}if(stmt!=null){
					stmt.close();
				}if(con!=null){
					con.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
	
}