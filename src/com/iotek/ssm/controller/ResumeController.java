package com.iotek.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Resume;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.PositionService;
import com.iotek.ssm.service.ResumeService;

@RequestMapping("resume")
@Controller
public class ResumeController {
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private PositionService positionService;

//	@ResponseBody
//	@RequestMapping(value="query",produces = "application/json; charset=utf-8")
//	public String queryResume(HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		Resume resume = resumeService.getResumeByUid(user.getUid());
//		return JSON.toJSONString(resume);
//	}

	@RequestMapping("add")
	public String addResume(Resume resume, Integer resumeId, Integer deptId, Integer pid, HttpSession session,
			Model model) {
		Position position = positionService.getPositionById(pid);
		resume.setPosition(position);
		User user = (User) session.getAttribute("user");
		resume.setUid(user.getUid());
		if (resumeId == null) {
			resumeService.addResume(resume);
		} else {
			resume.setRid(resumeId);
			resumeService.updateResume(resume);
		}
//		model.addAttribute("resume", resume);
		return "tourist";
	}
}
