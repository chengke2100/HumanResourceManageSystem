package com.iotek.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.DepartmentDao;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.service.DepartmentService;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<Department> findAllDepartments() {
		return departmentDao.queryAllDepartments();
	}

	@Override
	public Department getDepartmentById(int did) {
		return departmentDao.queryDepartmentById(did);
	}

	@Override
	public int deleteDepartment(int did) {
		return departmentDao.deleteDepartment(did);
	}

	@Override
	public int updateDepartment(Department department) {
		return departmentDao.updateDepartment(department);
	}

	@Override
	public Boolean checkDeptName(String deptName) {
		Integer did = departmentDao.checkDeptName(deptName);
		return did!=null?true:false;
	}

	@Override
	public int addDepartment(Department department) {
		return departmentDao.insertDepartment(department);
	}

	@Override
	public Department getDepartmentByName(String deptName) {
		return departmentDao.queryDepartmentByDeptName(deptName);
	}

}
