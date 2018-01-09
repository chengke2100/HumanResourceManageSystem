package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Department;

public interface DepartmentService {
	
	int addDepartment(Department department);
	/**
	 * 查询所有的部门
	 * @return
	 */
	List<Department> findAllDepartments();
	/**
	 * 根据部门id查询部门
	 * @param did
	 * @return
	 */
	Department getDepartmentById(int did);
	/**
	 * 删除部门
	 * @param did
	 * @return
	 */
	int deleteDepartment(int did);
	/**
	 * 修改部门信息
	 * @param department
	 * @return
	 */
	int updateDepartment(Department department);
	/**
	 * 验证部门名是否已存在
	 * @param deptName
	 * @return
	 */
	Boolean checkDeptName(String deptName);
	/**
	 * 根据部门名查找部门
	 * @param deptName
	 * @return
	 */
	Department getDepartmentByName(String deptName);
}
