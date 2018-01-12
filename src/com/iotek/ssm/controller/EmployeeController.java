package com.iotek.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iotek.ssm.entity.Employee;
import com.iotek.ssm.service.EmployeeService;

@RequestMapping("employee")
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
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
}
