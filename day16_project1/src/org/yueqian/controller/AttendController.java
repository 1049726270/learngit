package org.yueqian.controller;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.yueqian.model.User;
import org.yueqian.service.AttendService;
import org.yueqian.service.UserService;

/**
 * @author Administrator
 *	attend表相关的页面控制层
 */
public class AttendController {

	static AttendService attendService = new AttendService();
	static UserService userService = new UserService();
	
	public static void main(String[] args) {
		//接受客户端传来的用户名和密码(模拟数据)
		String username = "xiaoming123";
		String password = "123456";
		User user = new User(null, username, password, null);
		
		User userDB = userlogin(user);
		
		String tip = "";
		if (userDB != null) {
			tip = "登录成功！欢迎"+userDB.getRealname()+"登录";
		} else {
			tip = "用户不存在！请重新登录！";
		}
		
		System.out.println(tip);
		
		//接受客户端传过来的日期(模拟数据)
		String attendDate =  "2018-08";
		LinkedHashMap<String, Integer> count = selectByMonth(userDB,attendDate);
		System.out.println(count);
		
	}
	
	
	public static User userlogin(User user){
		
		/*//客户端传来的数据
		User user = new User(null, "xiaoming123", "123456", null);*/
		
		User userDB = userService.userLogin(user);
		/*String tip = "";
		if (userDB != null) {
			tip = "登录成功！欢迎"+userDB.getRealname()+"登录";
		} else {
			tip = "用户不存在！请重新登录！";
		}
		
		System.out.println(tip);*/
		return userDB;
	}
	
	
	public static LinkedHashMap<String, Integer> selectByMonth(User user ,String attendDate){
		/*//模拟数据
		User user = new User(1, "xiaoming123", "123456", "小明");
		String attendDate = "2018-08";*/
		
		LinkedHashMap<String, Integer> count = attendService.selectByMonth(user, attendDate);
		
		//System.out.println(count);
		
		return count;
	}
	
	@Test
	public void insert(){
		//模拟前端传来的数据
		String line = "1";
		User user = new User(1, null, null, null);
		
		switch (line.trim()) {
		case "1":
			//1.编写上班打卡的代码
			String AttendMorningMsg = attendService.insertAttendMorning(user);
			//返回给客户端
			System.out.println(AttendMorningMsg);
			break;
		case "2":
			//2.编写下班打卡的代码
			String AttendEveningMsg = attendService.insertAttendEvening(user);
			System.out.println(AttendEveningMsg);
			break;
		case "4":
			//4.直接输出退出系统
			System.out.println("退出系统");
			break;
		default:
			//5.输入非法数据
			System.out.println("没有这个选项");
			break;
		}
	}
}
