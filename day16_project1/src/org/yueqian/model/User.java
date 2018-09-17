package org.yueqian.model;

/**
 * @author Administrator user表的javabean
 */
public class User {

	private Integer id;

	private String username;

	private String password;

	private String realname;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String username, String password, String realname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", realname=" + realname + "]";
	}
	
	

}