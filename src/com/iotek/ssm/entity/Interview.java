package com.iotek.ssm.entity;

import java.util.Date;

public class Interview {
	private int interId;
	private Apply apply;
	private String isInterview;//�Ƿ�����
	private Date interviewTime;//����ʱ��
	private String isHire;//�Ƿ�¼��
	public Interview() {
		super();
	}
	public Interview(int interId, Apply apply, String isInterview, Date interviewTime, String isHire) {
		super();
		this.interId = interId;
		this.apply = apply;
		this.isInterview = isInterview;
		this.interviewTime = interviewTime;
		this.isHire = isHire;
	}
	public int getInterId() {
		return interId;
	}
	public void setInterId(int interId) {
		this.interId = interId;
	}
	public Apply getApply() {
		return apply;
	}
	public void setApply(Apply apply) {
		this.apply = apply;
	}
	public String getIsInterview() {
		return isInterview;
	}
	public void setIsInterview(String isInterview) {
		this.isInterview = isInterview;
	}
	public Date getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(Date interviewTime) {
		this.interviewTime = interviewTime;
	}
	public String getIsHire() {
		return isHire;
	}
	public void setIsHire(String isHire) {
		this.isHire = isHire;
	}
	@Override
	public String toString() {
		return "Interview [interId=" + interId + ", apply=" + apply + ", isInterview=" + isInterview
				+ ", interviewTime=" + interviewTime + ", isHire=" + isHire + "]";
	}
	
	

}
