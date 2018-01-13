package com.iotek.ssm.service;

import com.iotek.ssm.entity.Salary;

public interface SalaryService {
	
	int addSalary(Salary salary);
	
	int updateSalary(Salary salary);
	
	Salary getSalaryById(int salId);
	
	Salary getSalaryByUserIdAndMonth(int userId,int year,int month);

	int getBasicPayByUid(int uid);
}
