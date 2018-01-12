package com.iotek.ssm.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.iotek.ssm.entity.ClockingIn;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.entity.Employee;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Resume;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.ClockingInService;
import com.iotek.ssm.service.DepartmentService;
import com.iotek.ssm.service.EmployeeService;
import com.iotek.ssm.service.PositionService;
import com.iotek.ssm.service.ResumeService;
import com.iotek.ssm.service.UserService;

@RequestMapping("employee")
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private ClockingInService clockingInService;
	@Autowired
	private ResumeService resumeService;
	
	@RequestMapping("showEmployees/{isOnJob}")
	public String showEmployees(@PathVariable("isOnJob")String isOnJob,Model model) {
		List<Employee> employees=null;
		if(isOnJob.equals("在职员工")) {
			employees = employeeService.findEmployeesIsOnJob();
		}else {
			employees = employeeService.findEmployeesNotOnJob();
		}
		model.addAttribute("employees", employees);
		model.addAttribute("isOnJob", isOnJob);
		return "manager";
	}
	
	@RequestMapping("transfer/{userId}")
	public String transfer(@PathVariable("userId")Integer userId,Model model) {
		User user = userService.getUserByUid(userId);
		Department department = departmentService.getDepartmentById(user.getDepartment().getDid());
		user.setDepartment(department);
		model.addAttribute("user", user);
		return "manager";
	}
	
	@RequestMapping("dotransfer")
	public String dotransfer(int uid,int deptId,int positionId,Model model) {
		User user = userService.getUserByUid(uid);
		Department department = departmentService.getDepartmentById(deptId);
		Position position = positionService.getPositionById(positionId);
		user.setDepartment(department);
		user.setPosition(position);
		userService.updateUser(user);
		model.addAttribute("dotransfer", "dotransfer");
		return "manager";
	}
	
	@RequestMapping("clocking/{userId}/{realName}")
	public String clocking(@PathVariable("userId")Integer userId,@PathVariable("realName")String realName,Model model) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		List<ClockingIn> clockingList = clockingInService.findClockingInByUid(userId, year, month);
		model.addAttribute("clockingList", clockingList);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "manager";
	}
	
	@RequestMapping("message/{userId}")
	public String showMessage(@PathVariable("userId")Integer userId,Model model) {
		Resume resume = resumeService.getResumeByUid(userId);
		model.addAttribute("employeeResume", resume);
		return "manager";
	}
}
