package com.iotek.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.SalaryDao;
import com.iotek.ssm.entity.Salary;
import com.iotek.ssm.service.SalaryService;
@Service("salaryService")
public class SalaryServiceImpl implements SalaryService {
	@Autowired
	private SalaryDao salaryDao;
	
	@Override
	public int addSalary(Salary salary) {
		return salaryDao.insertSalary(salary);
	}

	@Override
	public int updateSalary(Salary salary) {
		return salaryDao.updateSalary(salary);
	}

	@Override
	public Salary getSalaryById(int salId) {
		return salaryDao.querySalaryById(salId);
	}

	@Override
	public Salary getSalaryByUserIdAndMonth(int userId, int year, int month) {
		return salaryDao.querySalaryByUserIdAndMonth(userId, year, month);
	}

}
