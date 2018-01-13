package com.iotek.ssm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iotek.ssm.entity.ClockingIn;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.entity.Employee;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Resume;
import com.iotek.ssm.entity.Rewards;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.ClockingInService;
import com.iotek.ssm.service.DepartmentService;
import com.iotek.ssm.service.EmployeeService;
import com.iotek.ssm.service.PositionService;
import com.iotek.ssm.service.ResumeService;
import com.iotek.ssm.service.RewardsService;
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
	@Autowired
	private RewardsService rewardsService;
	
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
		model.addAttribute("realName", realName);
		model.addAttribute("userId", userId);
		return "manager";
	}
	
	@RequestMapping("showClockingIn/{year}/{month}/{realName}/{userId}")
	public String showClockingIn(@PathVariable("year")int year,@PathVariable("month")int month,
			@PathVariable("realName")String realName,@PathVariable("userId")Integer userId,Model model) {
		List<ClockingIn> clockingList = clockingInService.findClockingInByUid(userId, year, month);
		model.addAttribute("clockingList", clockingList);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("realName", realName);
		model.addAttribute("userId", userId);
		return "manager";	
	}
	
	@RequestMapping("message/{userId}")
	public String showMessage(@PathVariable("userId")Integer userId,Model model) {
		Resume resume = resumeService.getResumeByUid(userId);
		model.addAttribute("employeeResume", resume);
		User user = userService.getUserByUid(userId);
		model.addAttribute("entryDate", user.getEntryDate());
		return "manager";
	}
	
	@RequestMapping("give")
	public String give(Integer bonus,Integer userId,String season,String realName,String rewardsTime1,Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date rewardsTime = null;
		try {
			rewardsTime = sdf.parse(rewardsTime1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		User user = userService.getUserByUid(userId);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		Rewards rewards = new Rewards(-1, user, realName, season, rewardsTime, bonus, "赏", year, month);
		rewardsService.addRewards(rewards);
		model.addAttribute("give", "give");
		return "manager";
	}
	
	@RequestMapping("findRewards/{year}/{month}")
	public String findRewards(@PathVariable("year")int year,@PathVariable("month")int month,Model model) {
		List<Rewards> rewardsList = rewardsService.findRewardsByMonth(year,month);
		model.addAttribute("rewardsList", rewardsList);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "manager";
	}
	
	
}
