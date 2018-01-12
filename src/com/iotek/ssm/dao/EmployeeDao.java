package com.iotek.ssm.dao;

import java.util.List;

import com.iotek.ssm.entity.Employee;

public interface EmployeeDao {
	/**
	 * 查询在职员工的真实姓名
	 * @param resignationSeason
	 * @return
	 */
	List<Employee> queryEmployeesByIsOnJob();

	List<Employee> queryEmployeesNotOnJob();
}
