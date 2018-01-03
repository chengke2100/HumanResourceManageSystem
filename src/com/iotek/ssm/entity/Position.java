package com.iotek.ssm.entity;

import java.util.Date;
import java.util.List;

public class Position {
	private int pid;
	private String name;
	private Department department;
	private Date createTime;
	private List<User> users;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	public Position() {
		super();
	}
	
	public Position(int pid, String name, Department department, Date createTime, List<User> users) {
		super();
		this.pid = pid;
		this.name = name;
		this.department = department;
		this.createTime = createTime;
		this.users = users;
	}
	@Override
	public String toString() {
		return "Position [pid=" + pid + ", name=" + name + ", department=" + department + ", createTime=" + createTime
				+ "]";
	}
	
	

}
