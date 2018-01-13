package com.iotek.ssm.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.iotek.ssm.entity.ClockingIn;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.entity.Interview;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Resume;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.ClockingInService;
import com.iotek.ssm.service.DepartmentService;
import com.iotek.ssm.service.InterviewService;
import com.iotek.ssm.service.ResumeService;
import com.iotek.ssm.service.UserService;
import com.iotek.ssm.util.MyUtil;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService; 
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private InterviewService interviewService;
	@Autowired
	private ClockingInService clockingInService;

	@RequestMapping("loginPage")
	public String goLoginPage() {
		return "forward:../login.jsp";
	}

	@RequestMapping("registerPage")
	public String goRegisterPage() {
		return "forward:../register.jsp";
	}
	
	@RequestMapping("check")
	@ResponseBody
	public String checkName(String userName) {
		Boolean res = userService.isReapat(userName);
		if(res) {
			return "yes";
		}
		return "no";
	}

	@RequestMapping("register")
	public String register(User user,String password1, HttpSession session) {
		user.setType(1);
		String password = MyUtil.md5(password1);
		user.setPassword(password);
		userService.addUser(user);
		return "forward:../user/login";
	}

	@RequestMapping("login")
	public String login(Model model, String userName, String password1, HttpSession session) {
		String md5Password = MyUtil.md5(password1);
		User user = userService.findUserByNameAndPassowrd(userName, md5Password);
		if (user != null) {
			session.setAttribute("user", user);
			List<Department> departments = departmentService.findAllDepartments();
//			model.addAttribute("departments", departments);
			session.setAttribute("departments", departments);
			if(user.getType()==1) {
				//游客
				Resume resume = resumeService.getResumeByUid(user.getUid());
				if(resume!=null) {
					int did = resume.getPosition().getDepartment().getDid();
					Department department = departmentService.getDepartmentById(did);
					resume.getPosition().setDepartment(department);
				}
				model.addAttribute("resume", resume);
				Interview interview = interviewService.getInterviewByUid(user.getUid());
				model.addAttribute("interview", interview);
//				if(resume!=null) {
//					Position position = resume.getPosition();
//					model.addAttribute("position", position);
//				}
				return "tourist";
			}
			if(user.getType()==0) {
				//管理员
				return "manager";
			}
			if(user.getType()==2) {
				//部门管理员
				List<Interview> interviews = interviewService.findIterviewByIsInterviewAndStatus("按时面试","未面试");
				model.addAttribute("interviews", interviews);
				return "departmentManager";
			}
			if(user.getType()==3) {
				//员工
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH)+1;
				int day=cal.get(Calendar.DATE);
				ClockingIn clockingIn = clockingInService.findClockingInByUidAndTime(user.getUid(), year, month, day);
				model.addAttribute("clockingIn", clockingIn);
				return "employee";
			}
		}
		//走到这儿说明帐户或者密码错误
		model.addAttribute("error", "error");
		return "forward:../login.jsp";
	}
	
	
	@RequestMapping(value="positions",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPositions(Integer did) {
		Department department = departmentService.getDepartmentById(did);
		Set<Position> src = department.getPositions();
		String data = null;
		if(src.isEmpty()) {
			data = JSON.toJSONString(null);
		}else {
			data = JSON.toJSONString(src);
		}
		return data;		
	}
	
	@ResponseBody
	@RequestMapping("updatePassword")
	public String updatePassword(String newPassword,HttpSession session) {
		String password = MyUtil.md5(newPassword);
		User user = (User) session.getAttribute("user");
		user.setPassword(password);
		userService.updateUser(user);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("checkPassword")
	public String checkPassword(String oldPassword,HttpSession session) {
		String password = MyUtil.md5(oldPassword);
		User user = (User) session.getAttribute("user");
		if(user.getPassword().equals(password)) {
			return "success";
		}
		return "fail";
		
	}
	
	@ResponseBody
	@RequestMapping("findResume")
	public String findResume(HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		Resume resume = resumeService.getResumeByUid(user.getUid());
		model.addAttribute("resume", resume);
		return "on";
	}

}
