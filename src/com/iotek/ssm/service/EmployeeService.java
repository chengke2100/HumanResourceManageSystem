package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Employee;

public interface EmployeeService {
	/**
	 * ��ѯ��ְԱ������ʵ����
	 * @return
	 */
	List<Employee> findEmployeesIsOnJob();
	/**
	 * ��ѯ��ְԱ������ʵ����
	 * @return
	 */
	List<Employee> findEmployeesNotOnJob();
}
