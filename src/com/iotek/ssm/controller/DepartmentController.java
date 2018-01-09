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
			data=JSON.toJSONString(null);//���������Ѵ���
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
			//˵��������������ְ��Ա��������ɾ������
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
			data=JSON.toJSONString(null);//���������Ѵ���
		}else {
			Department department = new Department(-1, deptName, new Date(), null);
			departmentService.addDepartment(department);
			department=departmentService.getDepartmentByName(deptName);//��Ҫ���´����ݿ����ӵĲ��Ų�ѯ�����˶���Ż��кϷ���id
			System.out.println(department);
			data = JSON.toJSONString(department);
		}
		return data;
	}
	
	@RequestMapping("addPosition")
	@ResponseBody
	public String addPositon(Integer did,String pName) {
		Department department = departmentService.getDepartmentById(did);
		Set<Position> positions = department.getPositions();
		String data = "";
		for (Position p : positions) {
			if(p.getName().equals(pName)) {
				data = JSON.toJSONString(null);//����˲������Ѿ��и����ֵ�ְλ
			}
		}
		if(data.equals("")){
			//����û���ظ�
			Position position = new Position(-1, pName, department, new Date(),null);
			positionService.addPosition(position);
			position =positionService.getPositionByName(pName,did);
			data = JSON.toJSONString(position);
		}
		return data;
	}

}
