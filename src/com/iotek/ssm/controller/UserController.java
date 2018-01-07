package com.iotek.ssm.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Resume;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.DepartmentService;
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
		String positions = JSON.toJSONString(src);
		return positions;		
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
		System.out.println(user.getPassword().equals(password));
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
