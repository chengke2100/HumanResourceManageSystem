package com.iotek.ssm.dao;

import java.util.List;

import com.iotek.ssm.entity.Department;

public interface DepartmentDao {
	/**
	 * 添加部门
	 * @param department
	 * @return
	 */
	int insertDepartment(Department department);
	/**
	 * 删除部门
	 * @param did
	 * @return
	 */
	int deleteDepartment(int did);
	/**
	 * 修改部门
	 * @param department
	 * @return
	 */
	int updateDepartment(Department department);
	/**
	 * 查询所有部门
	 * @return
	 */
	List<Department> queryAllDepartments();
	/**
	 * 根据部门id查询部门
	 * @param did
	 * @return
	 */
	Department queryDepartmentById(int did);
	
	Integer checkDeptName(String deptName);
	
	Department queryDepartmentByDeptName(String deptName);
}
