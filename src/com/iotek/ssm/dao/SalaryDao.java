package com.iotek.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.iotek.ssm.entity.Salary;

public interface SalaryDao {
	/**
	 * ��ӹ��ʼ�¼
	 * @param salary
	 * @return
	 */
	int insertSalary(Salary salary);
	/**
	 * �޸Ĺ��ʼ�¼
	 * @param salary
	 * @return
	 */
	int updateSalary(Salary salary);
	/**
	 * ����id��ѯ���ʼ�¼
	 * @param salId
	 * @return
	 */
	Salary querySalaryById(int salId);
	/**
	 * �鿴ĳ��Ա��ĳ���µĹ��ʼ�¼
	 * @param useId
	 * @param year
	 * @param month
	 * @return
	 */
	Salary querySalaryByUserIdAndMonth(@Param("userId")int userId,@Param("year")int year,@Param("month")int month);
}
