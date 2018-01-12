package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Employee;

public interface EmployeeService {
	/**
	 * 查询在职员工的真实姓名
	 * @return
	 */
	List<Employee> findEmployeesIsOnJob();
	/**
	 * 查询离职员工的真实姓名
	 * @return
	 */
	List<Employee> findEmployeesNotOnJob();
}
