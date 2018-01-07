package com.iotek.ssm.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iotek.ssm.entity.Apply;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Recruit;
import com.iotek.ssm.entity.Resume;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.ApplyService;
import com.iotek.ssm.service.DepartmentService;
import com.iotek.ssm.service.PositionService;
import com.iotek.ssm.service.RecruitService;
import com.iotek.ssm.service.ResumeService;

@Controller
@RequestMapping("recruit")
public class RecruitController {
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private ApplyService applyService;
	
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
	public String showAllRecruits(Model model,HttpSession session) {
		List<Recruit> recruits = recruitService.findRecruitsByStatus(1);
		for (Recruit recruit : recruits) {
			Department department = departmentService.getDepartmentById(recruit.getPosition().getDepartment().getDid());
			recruit.getPosition().setDepartment(department);
		}
		model.addAttribute("recruits", recruits);
		User user = (User) session.getAttribute("user");
		if(user.getType()==1) {
			//返回游客页面
			Resume resume = resumeService.getResumeByUid(user.getUid());
			if(resume!=null) {
				int did = resume.getPosition().getDepartment().getDid();
				Department department = departmentService.getDepartmentById(did);
				resume.getPosition().setDepartment(department);
			}
			model.addAttribute("resume", resume);
			return "tourist";
		}
		//返回管理员界面
		return "manager";
	}
	
	@RequestMapping("update")
	public String updateRecruit(Recruit recruit,Integer pid,Model model) {
		if(pid!=null) {
			Position position = positionService.getPositionById(pid);
			recruit.setPosition(position);
			recruit.setStatus(1);
			recruitService.updateRecruit(recruit);
		}
		model.addAttribute("suc", "suc");
		return "manager";
			
	}
	
	@RequestMapping("apply")
	@ResponseBody
	public String apply(Integer recruitsId,HttpSession session) {
		Recruit recruit = recruitService.getRecruitBuId(recruitsId);
		User user = (User) session.getAttribute("user");
		System.out.println(user.getUid());
		Resume resume = resumeService.getResumeByUid(user.getUid());
		String data = "";
		if(resume==null) {
			data="1";//代表用户还没有创建简历
		}else if(resume.getPosition().getPid()!=recruit.getPosition().getPid()) {
			data="2";//代表用户简历上的求职岗位与招聘上的岗位不符合
		}else {
			List<Apply> applys = applyService.findApplyByUidAndStatus(user.getUid(),"未面试");
			if(!applys.isEmpty()) {
				data="3";//代表用户还有未完结的应聘
			}else {
				Apply apply = new Apply(-1, recruit, user.getUid(), new Date(), false, "未面试");
				applyService.addApply(apply);
				data="4";//投递成功
			}		
		}
		return data;
	}
	
	@RequestMapping("showApplys")
	public String showApplys(Model model) {
		List<Apply> applys = applyService.findApplysByStatus("未面试");
		model.addAttribute("applys", applys);
		return "manager";
	}
}
