package com.iotek.ssm.entity;

public class Salary {
	private int salId;
	private User user;
	private int basicPay;//基本工资
	private int meritPay;//绩效工资
	private int overtimePay;//加班工资
	private int socialSecurity;//社保
	private int rewardsPay;//奖惩
	private int year;
	private int month;
	public Salary() {
		super();
	}
	public Salary(int salId, User user, int basicPay, int meritPay, int overtimePay, int socialSecurity, int rewardsPay,
			int year, int month) {
		super();
		this.salId = salId;
		this.user = user;
		this.basicPay = basicPay;
		this.meritPay = meritPay;
		this.overtimePay = overtimePay;
		this.socialSecurity = socialSecurity;
		this.rewardsPay = rewardsPay;
		this.year = year;
		this.month = month;
	}
	public int getSalId() {
		return salId;
	}
	public void setSalId(int salId) {
		this.salId = salId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(int basicPay) {
		this.basicPay = basicPay;
	}
	public int getMeritPay() {
		return meritPay;
	}
	public void setMeritPay(int meritPay) {
		this.meritPay = meritPay;
	}
	public int getOvertimePay() {
		return overtimePay;
	}
	public void setOvertimePay(int overtimePay) {
		this.overtimePay = overtimePay;
	}
	public int getSocialSecurity() {
		return socialSecurity;
	}
	public void setSocialSecurity(int socialSecurity) {
		this.socialSecurity = socialSecurity;
	}
	public int getRewardsPay() {
		return rewardsPay;
	}
	public void setRewardsPay(int rewardsPay) {
		this.rewardsPay = rewardsPay;
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
	@Override
	public String toString() {
		return "Salary [salId=" + salId + ", user=" + user + ", basicPay=" + basicPay + ", meritPay=" + meritPay
				+ ", overtimePay=" + overtimePay + ", socialSecurity=" + socialSecurity + ", rewardsPay=" + rewardsPay
				+ ", year=" + year + ", month=" + month + "]";
	}
	
	
}
