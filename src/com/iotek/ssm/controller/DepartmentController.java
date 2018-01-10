package com.iotek.ssm.controller;




import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.service.DepartmentService;
import com.iotek.ssm.service.PositionService;
import com.iotek.ssm.service.UserService;

@Controller
@RequestMapping("department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private UserService UserService;
	
	@ResponseBody
	@RequestMapping("updateDepartment")
	public String updateDepartment(Integer did,String deptName) {
		Boolean res = departmentService.checkDeptName(deptName);
		String data=null;
		if(res) {
			data=JSON.toJSONString(null);//代表部门名已存在
		}else {
			Department department = departmentService.getDepartmentById(did);
			department.setDeptName(deptName);
			departmentService.updateDepartment(department);
			data = JSON.toJSONString(department);
		}
		return data;
	}
	
	@RequestMapping("deleteDepartment")
	@ResponseBody
	public String deleteDepartment(Integer did) {
		List<Integer> list = UserService.findUsersIdByDid(did,null);
		String data="";
		if(!list.isEmpty()) {
			//说明部门里面有在职的员工，不能删除部门
			data = "0";
		}else {
			departmentService.deleteDepartment(did);
			data = "1";
		}
		return data;
	}
	
	@RequestMapping("addDepartment")
	@ResponseBody
	public String addDepartment(String deptName) {
		Boolean res = departmentService.checkDeptName(deptName);
		String data = null;
		if(res) {
			data=JSON.toJSONString(null);//代表部门名已存在
		}else {
			Department department = new Department(-1, deptName, new Date(), null);
			departmentService.addDepartment(department);
			department=departmentService.getDepartmentByName(deptName);//需要重新从数据库把添加的部门查询出来此对象才会有合法的id
			System.out.println(department);
			data = JSON.toJSONString(department);
		}
		return data;
	}
	
	@RequestMapping("addPosition")
	@ResponseBody
	public String addPositon(Integer did,String pName) {
		Position position = positionService.getPositionByName(pName, did);
		String data="";
		if(position!=null) {
			data = JSON.toJSONString(null);//代表此部门下已经有该名字的职位
		}else {
			//名字没有重复
			Department department = departmentService.getDepartmentById(did);
			position = new Position(-1, pName, department, new Date(),null);
			positionService.addPosition(position);
			position =positionService.getPositionByName(pName,did);
			data = JSON.toJSONString(position);
		}
		return data;
	}
	
	@RequestMapping("updatePosition")
	@ResponseBody
	public String updatePosition(Integer pid,String pName) {
		Position position = positionService.getPositionById(pid);
		Position p = positionService.getPositionByName(pName, position.getDepartment().getDid());
		String data = "";
		if(p!=null) {
			data = JSON.toJSONString(null);//代表此部门下已经有该名字的职位
		}else {
			position.setName(pName);
			positionService.updatePositon(position);
			data = JSON.toJSONString(position);
		}
		return data;
	}
	
	@RequestMapping("deletePosition")
	@ResponseBody
	public String deletePosition(Integer pid) {
		List<Integer> list = UserService.findUsersIdByPid(pid,null);
		String data="";
		if(!list.isEmpty()) {
			//说明职位里面有在职的员工，不能删除部门
			data = "0";
		}else {
			positionService.deletePosition(pid);
			data = "1";
		}
		return data;
	}

}
