package com.iotek.ssm.entity;

public class Recruit {
	private int recruitsId;
	private String companyName;
	private Position position;
	private String jobInformation;//职位描述
	private String requirements;//岗位要求
	private String salary;//薪资范围
	private int status;//是否有效
	public Recruit() {
		super();
	}
	public Recruit(int recruitsId, String companyName, Position position, String jobInformation, String requirements,
			String salary, int status) {
		super();
		this.recruitsId = recruitsId;
		this.companyName = companyName;
		this.position = position;
		this.jobInformation = jobInformation;
		this.requirements = requirements;
		this.salary = salary;
		this.status = status;
	}
	public int getRecruitsId() {
		return recruitsId;
	}
	public void setRecruitsId(int recruitsId) {
		this.recruitsId = recruitsId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getJobInformation() {
		return jobInformation;
	}
	public void setJobInformation(String jobInformation) {
		this.jobInformation = jobInformation;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Recruit [recruitsId=" + recruitsId + ", companyName=" + companyName + ", position=" + position
				+ ", jobInformation=" + jobInformation + ", requirements=" + requirements + ", salary=" + salary
				+ ", status=" + status + "]";
	}
	
}
