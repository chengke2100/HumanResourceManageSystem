package com.iotek.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Recruit;
import com.iotek.ssm.service.PositionService;
import com.iotek.ssm.service.RecruitService;

@Controller
@RequestMapping("recruit")
public class RecruitController {
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private PositionService positionService;
	
	@RequestMapping("add")
	public String postJob(Recruit recruit,Integer pid,Model model) {
		Position position = positionService.getPositionById(pid);
		recruit.setPosition(position);
		recruit.setStatus(1);
		recruitService.addResruit(recruit);
		model.addAttribute("success", "success");
		return "manager";
	}
	
	@RequestMapping("showAll")
	public String showAllRecruits(Model model) {
		List<Recruit> recruits = recruitService.findRecruitsByStatus(1);
		model.addAttribute("recruits", recruits);
		return "manager";
	}
	
	@RequestMapping("update")
	public String updateRecruit(Recruit recruit,Integer pid,Model model) {
		if(pid!=null) {
			Position position = positionService.getPositionById(pid);
			recruit.setPosition(position);
			recruitService.updateRecruit(recruit);
		}
		model.addAttribute("suc", "suc");
		return "manager";
			
	}
}
