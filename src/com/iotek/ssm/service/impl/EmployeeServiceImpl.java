package com.iotek.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.EmployeeDao;
import com.iotek.ssm.entity.Employee;
import com.iotek.ssm.service.EmployeeService;
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> findEmployeesIsOnJob() {
		return employeeDao.queryEmployeesByIsOnJob();
	}

	@Override
	public List<Employee> findEmployeesNotOnJob() {
		return employeeDao.queryEmployeesNotOnJob();
	}

}
