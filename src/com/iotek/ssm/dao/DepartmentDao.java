package com.iotek.ssm.dao;

import java.util.List;

import com.iotek.ssm.entity.Department;

public interface DepartmentDao {
	/**
	 * ��Ӳ���
	 * @param department
	 * @return
	 */
	int insertDepartment(Department department);
	/**
	 * ɾ������
	 * @param did
	 * @return
	 */
	int deleteDepartment(int did);
	/**
	 * �޸Ĳ���
	 * @param department
	 * @return
	 */
	int updateDepartment(Department department);
	/**
	 * ��ѯ���в���
	 * @return
	 */
	List<Department> queryAllDepartments();
	/**
	 * ���ݲ���id��ѯ����
	 * @param did
	 * @return
	 */
	Department queryDepartmentById(int did);
}
