package com.iotek.ssm.entity;

import java.util.Date;

public class Train {
	private int tid;
	private String trainName;
	private Date trainTime;
	private Department department;
	public Train() {
		super();
	}
	public Train(int tid, String trainName, Date trainTime, Department department) {
		super();
		this.tid = tid;
		this.trainName = trainName;
		this.trainTime = trainTime;
		this.department = department;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public Date getTrainTime() {
		return trainTime;
	}
	public void setTrainTime(Date trainTime) {
		this.trainTime = trainTime;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Train [tid=" + tid + ", trainName=" + trainName + ", trainTime=" + trainTime + ", department="
				+ department + "]";
	}
	
}
