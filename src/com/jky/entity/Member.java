package com.jky.entity;

public class Member {
	/**成员ID*/
	private int memberId;
	/**账户名*/
	private String userName;
	/**登录密码*/
	private String password;
	/**成员名称*/
	private String name;
	/**成员角色*/
	private int role;
	/**是否在工作*/
	private int isWork;
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getIsWork() {
		return isWork;
	}
	public void setIsWork(int isWork) {
		this.isWork = isWork;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", userName=" + userName + ", password=" + password + ", name=" + name
				+ ", role=" + role + ", isWork=" + isWork + "]";
	}
	
	
}
