package com.iotek.ssm.entity;

import java.util.Date;

public class ClockingIn {
	private int cloId;
	private User user;
	private Date beginTime;
	private Date endTime;
	private String isLate;
	private String isEarly;
	private int absenteeismDays;//本月缺勤天数
	private int year;
	private int month;
	private int day;
	public ClockingIn() {
		super();
	}
	public ClockingIn(int cloId, User user, Date beginTime, Date endTime, String isLate, String isEarly,
			int absenteeismDays, int year, int month, int day) {
		super();
		this.cloId = cloId;
		this.user = user;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.isLate = isLate;
		this.isEarly = isEarly;
		this.absenteeismDays = absenteeismDays;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public int getCloId() {
		return cloId;
	}
	public void setCloId(int cloId) {
		this.cloId = cloId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getIsLate() {
		return isLate;
	}
	public void setIsLate(String isLate) {
		this.isLate = isLate;
	}
	public String getIsEarly() {
		return isEarly;
	}
	public void setIsEarly(String isEarly) {
		this.isEarly = isEarly;
	}
	public int getAbsenteeismDays() {
		return absenteeismDays;
	}
	public void setAbsenteeismDays(int absenteeismDays) {
		this.absenteeismDays = absenteeismDays;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "ClockingIn [cloId=" + cloId + ", user=" + user + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", isLate=" + isLate + ", isEarly=" + isEarly + ", absenteeismDays=" + absenteeismDays + ", year="
				+ year + ", month=" + month + ", day=" + day + "]";
	}
	
}
