package com.iotek.ssm.dao;

import com.iotek.ssm.entity.Salary;

public interface SalaryDao {
	/**
	 * 添加工资记录
	 * @param salary
	 * @return
	 */
	int insertSalary(Salary salary);
	/**
	 * 修改工资记录
	 * @param salary
	 * @return
	 */
	int updateSalary(Salary salary);
	/**
	 * 根据id查询工资记录
	 * @param salId
	 * @return
	 */
	Salary querySalaryById(int salId);
	/**
	 * 查看某个员工某个月的工资记录
	 * @param useId
	 * @param year
	 * @param month
	 * @return
	 */
	Salary querySalary(int useId,int year,int month);
}
