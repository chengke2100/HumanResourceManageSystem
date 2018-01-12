package com.iotek.ssm.entity;

import java.util.Date;

public class Rewards {
	private int rewardsId;
	private User user;
	private String season;
	private Date rewardsTime;
	private int bonus;
	private String type;
	private int year;
	private int month;
	public Rewards() {
		super();
	}
	public Rewards(int rewardsId, User user, String season, Date rewardsTime, int bonus, String type, int year,
			int month) {
		super();
		this.rewardsId = rewardsId;
		this.user = user;
		this.season = season;
		this.rewardsTime = rewardsTime;
		this.bonus = bonus;
		this.type = type;
		this.year = year;
		this.month = month;
	}
	public int getRewardsId() {
		return rewardsId;
	}
	public void setRewardsId(int rewardsId) {
		this.rewardsId = rewardsId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public Date getRewardsTime() {
		return rewardsTime;
	}
	public void setRewardsTime(Date rewardsTime) {
		this.rewardsTime = rewardsTime;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return "Rewards [rewardsId=" + rewardsId + ", user=" + user + ", season=" + season + ", rewardsTime="
				+ rewardsTime + ", bonus=" + bonus + ", type=" + type + ", year=" + year + ", month=" + month + "]";
	}
	
	
}
