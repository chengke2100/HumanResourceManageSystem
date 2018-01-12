package com.iotek.ssm.entity;

public class Employee {
	private int userId;
	private String realName;
	private Department department;
	private Position position;
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Employee [userId=" + userId + ", realName=" + realName + ", department=" + department + ", position="
				+ position + "]";
	}
	
	
}
