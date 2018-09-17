package org.yueqian.service;

import org.yueqian.dao.UserDao;
import org.yueqian.model.User;

/**
 * @author Administrator
 *	与user表相关的业务处理
 */
public class UserService {
	
	UserDao userDao = new UserDao();

	public User userLogin(User user) {
		User userDB = userDao.select(user);
		return userDB;
	}

}
