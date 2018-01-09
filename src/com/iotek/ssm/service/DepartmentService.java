package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Department;

public interface DepartmentService {
	/**
	 * ��ѯ���еĲ���
	 * @return
	 */
	List<Department> findAllDepartments();
	/**
	 * ���ݲ���id��ѯ����
	 * @param did
	 * @return
	 */
	Department getDepartmentById(int did);
	/**
	 * ɾ������
	 * @param did
	 * @return
	 */
	int deleteDepartment(int did);
	/**
	 * �޸Ĳ�����Ϣ
	 * @param department
	 * @return
	 */
	int updateDepartment(Department department);
}
