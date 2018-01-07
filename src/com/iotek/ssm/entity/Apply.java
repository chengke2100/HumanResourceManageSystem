package com.iotek.ssm.entity;

import java.util.Date;

public class Apply {
	private int applyId;
	private Recruit recruit;
	private int userId;
	private Date deliverTime;//投递时间
	private Boolean isRead;//是否已查看
	private String status;//招聘是否有效
	public Apply() {
		super();
	}
	public Apply(int applyId, Recruit recruit, int userId, Date deliverTime, Boolean isRead, String status) {
		super();
		this.applyId = applyId;
		this.recruit = recruit;
		this.userId = userId;
		this.deliverTime = deliverTime;
		this.isRead = isRead;
		this.status = status;
	}
	public int getApplyId() {
		return applyId;
	}
	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	public Recruit getRecruit() {
		return recruit;
	}
	public void setRecruit(Recruit recruit) {
		this.recruit = recruit;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Apply [applyId=" + applyId + ", recruit=" + recruit + ", userId=" + userId + ", deliverTime="
				+ deliverTime + ", isRead=" + isRead + ", status=" + status + "]";
	}
	
	

}
