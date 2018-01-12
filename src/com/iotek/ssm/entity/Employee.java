package com.iotek.ssm.entity;

public class Employee {
	private int userId;
	private String realName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Override
	public String toString() {
		return "Employee [userId=" + userId + ", realName=" + realName + "]";
	}
	
}
