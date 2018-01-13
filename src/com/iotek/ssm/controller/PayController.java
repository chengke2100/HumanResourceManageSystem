package com.iotek.ssm.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iotek.ssm.entity.ClockingIn;
import com.iotek.ssm.entity.Resume;
import com.iotek.ssm.entity.Rewards;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.ClockingInService;
import com.iotek.ssm.service.ResumeService;
import com.iotek.ssm.service.RewardsService;
import com.iotek.ssm.service.SalaryService;
import com.iotek.ssm.util.MyUtil;

@RequestMapping("pay")
@Controller
public class PayController {
	@Autowired
	private ClockingInService clockingInService;
	@Autowired
	private SalaryService salayService;
	@Autowired
	private RewardsService rewardsService;
	@Autowired
	private ResumeService resumeService;
	
	@RequestMapping("clockin")
	public String clockin(HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day=cal.get(Calendar.DATE);
		cal.set(Calendar.HOUR_OF_DAY,9);
        cal.set(Calendar.MINUTE,0);
        Date coreTime = cal.getTime();//正常的上班时间
        Date beginTime = new Date();
        String isLate="";
        if(beginTime.before(coreTime)) {
        	isLate="正常";
        }else {
        	isLate="迟到";
        }
        int workdays = MyUtil.getWorkdays(year, month);
        int absenteeismDays=0;
        List<ClockingIn> list = clockingInService.findClockingInByUid(user.getUid(), year, month);
        if(list.isEmpty()) {
        	//代表本月第一次打卡
        	absenteeismDays=workdays-1;
        }else {
        	absenteeismDays=clockingInService.findAbsenteeismDays(user.getUid(),year,month)-1;
        } 
        ClockingIn clo = new ClockingIn(-1, user, new Date(), null, isLate, null, absenteeismDays, year, month,day);
        clockingInService.addClockingin(clo);
        model.addAttribute("clockin", "clockin");
        return "employee";
	}
	
	@RequestMapping("clockout")
	public String clockout(HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day=cal.get(Calendar.DATE);
        ClockingIn clo = clockingInService.findClockingInByUidAndTime(user.getUid(),year,month,day);
        if(clo==null) {
        	//说明员工还没有打上班卡，不能打下班卡
        	model.addAttribute("message", "message");
        	return "employee";
        }
        cal.set(Calendar.HOUR_OF_DAY,9);
        cal.set(Calendar.MINUTE,0);
        //计算迟到的时间
        Date coreTime = cal.getTime();//正常的上班时间
        Date beginTime = clo.getBeginTime();
        double absenceHours = 0;
        if(1.0*(beginTime.getTime()-coreTime.getTime())/(1000*60*60)>0) {
        	absenceHours+=1.0*(beginTime.getTime()-coreTime.getTime())/(1000*60*60);
        }
        //计算早退的时间
        cal.set(Calendar.HOUR_OF_DAY,18);
        Date closingTime = cal.getTime();//正常的下班时间
        Date endTime = new Date();
        String isEarly = null;
        if(1.0*(closingTime.getTime()-endTime.getTime())/(1000*60*60)>0) {
        	absenceHours+=1.0*(closingTime.getTime()-endTime.getTime())/(1000*60*60);
        	isEarly="早退";
        }else {
        	isEarly="正常";
        }
        clo.setEndTime(endTime);
        clo.setIsEarly(isEarly);
        clockingInService.updateClockingin(clo);
        Resume resume = resumeService.getResumeByUid(user.getUid());
        if(absenceHours>3) {
        	//算旷工一天，要扣除当天工资
        	int basicPay = salayService.getBasicPayByUid(user.getUid());//查出该员工的基本工资
        	int workdays = MyUtil.getWorkdays(year, month);//获得当月的工作日
        	int bonus = basicPay/workdays;//应该扣除的当天工资        	
        	//生成对应的处罚记录
        	Rewards rewards = new Rewards(-1, user, resume.getRealName(), "旷工一天", new Date(), -bonus, "罚", year, month);
        	rewardsService.addRewards(rewards);
        }else {
        	if(clo.getIsLate().equals("迟到")) {
        		//迟到但是迟到早退的时间没有超过3小时
        		Rewards rewards = new Rewards(-1, user, resume.getRealName(), "迟到", new Date(), -50, "罚", year, month);
        		rewardsService.addRewards(rewards);
        	}
        	if(isEarly.equals("早退")) {
        		//早退但是迟到早退的时间没有超过3小时
        		Rewards rewards = new Rewards(-1, user, resume.getRealName(), "早退", new Date(), -50, "罚", year, month);
        		rewardsService.addRewards(rewards);
        	}
        }
        model.addAttribute("clockout", "clockout");
        return "employee";
	}
	
	@RequestMapping("showClockingIn/{year}/{month}")
	public String showClockingIn(HttpSession session,Model model,@PathVariable("year")Integer year,@PathVariable("month")Integer month) {
		User user = (User) session.getAttribute("user");
		if(year==0||month==0) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH)+1;
		}
		List<ClockingIn> clockingIns = clockingInService.findClockingInByUid(user.getUid(), year, month);
		model.addAttribute("clockingIns", clockingIns);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		int absenteeismDays = MyUtil.getWorkdays(year, month);
		if(!clockingIns.isEmpty()) {
			absenteeismDays = clockingInService.findAbsenteeismDays(user.getUid(), year, month);
		}
		model.addAttribute("absenteeismDays", absenteeismDays);
		return "employee";
	}
}
