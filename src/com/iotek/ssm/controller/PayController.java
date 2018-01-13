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
        Date coreTime = cal.getTime();//�������ϰ�ʱ��
        Date beginTime = new Date();
        String isLate="";
        if(beginTime.before(coreTime)) {
        	isLate="����";
        }else {
        	isLate="�ٵ�";
        }
        int workdays = MyUtil.getWorkdays(year, month);
        int absenteeismDays=0;
        List<ClockingIn> list = clockingInService.findClockingInByUid(user.getUid(), year, month);
        if(list.isEmpty()) {
        	//�����µ�һ�δ�
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
        	//˵��Ա����û�д��ϰ࿨�����ܴ��°࿨
        	model.addAttribute("message", "message");
        	return "employee";
        }
        cal.set(Calendar.HOUR_OF_DAY,9);
        cal.set(Calendar.MINUTE,0);
        //����ٵ���ʱ��
        Date coreTime = cal.getTime();//�������ϰ�ʱ��
        Date beginTime = clo.getBeginTime();
        double absenceHours = 0;
        if(1.0*(beginTime.getTime()-coreTime.getTime())/(1000*60*60)>0) {
        	absenceHours+=1.0*(beginTime.getTime()-coreTime.getTime())/(1000*60*60);
        }
        //�������˵�ʱ��
        cal.set(Calendar.HOUR_OF_DAY,18);
        Date closingTime = cal.getTime();//�������°�ʱ��
        Date endTime = new Date();
        String isEarly = null;
        if(1.0*(closingTime.getTime()-endTime.getTime())/(1000*60*60)>0) {
        	absenceHours+=1.0*(closingTime.getTime()-endTime.getTime())/(1000*60*60);
        	isEarly="����";
        }else {
        	isEarly="����";
        }
        clo.setEndTime(endTime);
        clo.setIsEarly(isEarly);
        clockingInService.updateClockingin(clo);
        Resume resume = resumeService.getResumeByUid(user.getUid());
        if(absenceHours>3) {
        	//�����һ�죬Ҫ�۳����칤��
        	int basicPay = salayService.getBasicPayByUid(user.getUid());//�����Ա���Ļ�������
        	int workdays = MyUtil.getWorkdays(year, month);//��õ��µĹ�����
        	int bonus = basicPay/workdays;//Ӧ�ÿ۳��ĵ��칤��        	
        	//���ɶ�Ӧ�Ĵ�����¼
        	Rewards rewards = new Rewards(-1, user, resume.getRealName(), "����һ��", new Date(), -bonus, "��", year, month);
        	rewardsService.addRewards(rewards);
        }else {
        	if(clo.getIsLate().equals("�ٵ�")) {
        		//�ٵ����ǳٵ����˵�ʱ��û�г���3Сʱ
        		Rewards rewards = new Rewards(-1, user, resume.getRealName(), "�ٵ�", new Date(), -50, "��", year, month);
        		rewardsService.addRewards(rewards);
        	}
        	if(isEarly.equals("����")) {
        		//���˵��ǳٵ����˵�ʱ��û�г���3Сʱ
        		Rewards rewards = new Rewards(-1, user, resume.getRealName(), "����", new Date(), -50, "��", year, month);
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
