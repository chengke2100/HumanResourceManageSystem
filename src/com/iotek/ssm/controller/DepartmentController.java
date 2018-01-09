package com.iotek.ssm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.service.DepartmentService;

@Controller
@RequestMapping("department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@ResponseBody
	@RequestMapping("updateDepartment")
	public String updateDepartment(Integer did,String deptName) {
		Department department = departmentService.getDepartmentById(did);
		department.setDeptName(deptName);
		departmentService.updateDepartment(department);
		String data = JSON.toJSONString(department);
		return data;
	}

}
