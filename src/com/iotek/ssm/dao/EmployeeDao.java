package com.iotek.ssm.dao;

import java.util.List;

import com.iotek.ssm.entity.Employee;

public interface EmployeeDao {
	/**
	 * ��ѯ��ְԱ������ʵ����
	 * @param resignationSeason
	 * @return
	 */
	List<Employee> queryEmployeesByIsOnJob();

	List<Employee> queryEmployeesNotOnJob();
}
