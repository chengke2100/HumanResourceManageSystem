package com.iotek.ssm.controller;


import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iotek.ssm.entity.Apply;
import com.iotek.ssm.entity.Interview;
import com.iotek.ssm.entity.Resume;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.ApplyService;
import com.iotek.ssm.service.InterviewService;
import com.iotek.ssm.service.ResumeService;

@Controller
@RequestMapping("interview")
public class InterviewController {
	@Autowired
	private InterviewService interviewService;
	@Autowired
	private ApplyService applyService;
	@Autowired
	private ResumeService resumeService;
	
	@RequestMapping("addInterview")
	public String addInterview(Integer applyId,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date interviewTime, Model model) {
		Apply apply = applyService.getApplyById(applyId);
		Interview interview = new Interview(-1, apply, "未确认", interviewTime, "未录用");
		interviewService.addInterview(interview);
		//给页面一个反馈信息
		model.addAttribute("addInterview", "addInterview");
		return "manager";
	}
	
	@RequestMapping("join")
	@ResponseBody
	public String join(Integer interId) {
		Interview interview = interviewService.getInterviewById(interId);
		interview.setIsInterview("按时面试");
		interviewService.updateInterview(interview);
		return "ok";
	}
	
	@RequestMapping("skip")
	@ResponseBody
	public String skip(Integer interId) {
		Interview interview = interviewService.getInterviewById(interId);
		interview.setIsInterview("不参加面试");
		interviewService.updateInterview(interview);
		return "ok";
	}
	
	@RequestMapping("doInterview/{interId}")
	public String doInterview(@PathVariable("interId")Integer interId,Model model) {
		int userId = interviewService.doInterview(interId);
		Resume resume = resumeService.getResumeByUid(userId);
		model.addAttribute("resume", resume);
		model.addAttribute("interId", interId);
		return "departmentManager";
	}
	
	@RequestMapping("hire")
	public String hire(Integer interId,HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		user = interviewService.doHire(interId,user.getUid());
		session.setAttribute("user", user);
		model.addAttribute("hire", "hire");
		return "departmentManager";
	}
	
	@RequestMapping("noHire/{interId}")
	public String hire(Integer interId,Model model) {
		interviewService.noHire(interId);
		model.addAttribute("nohire", "nohire");
		return "departmentManager";
	}
}
