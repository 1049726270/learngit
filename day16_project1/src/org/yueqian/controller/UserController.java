package org.yueqian.controller;

import org.junit.Test;
import org.yueqian.model.User;
import org.yueqian.service.UserService;

public class UserController {
	
	UserService userService = new UserService();
	
	@Test
	public void userlogin(){
		
		//客户端传来的数据
		User user = new User(null, "xiaoming123", "123456", null);
		
		User userDB = userService.userLogin(user);
		String tip = "";
		if (userDB != null) {
			tip = "登录成功！欢迎"+userDB.getRealname()+"登录";
		} else {
			tip = "用户不存在！请重新登录！";
		}
		
		System.out.println(tip);
		
	}

}
