package com.iotek.ssm.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iotek.ssm.entity.ClockingIn;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.ClockingInService;
import com.iotek.ssm.util.MyUtil;

@RequestMapping("pay")
@Controller
public class PayController {
	@Autowired
	private ClockingInService clockingInService;
	
	@RequestMapping("clockin")
	public String clockin(HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day=cal.get(Calendar.DATE);
		int hour=cal.get(Calendar.HOUR);
        int minute=cal.get(Calendar.MINUTE);
        String isLate="";
        if(hour>9||(hour==9 && minute>0)) {
        	isLate="是";
        }else {
        	isLate="否";
        }
        int workdays = MyUtil.getWorkdays(year, month);
        int absenteeismDays=0;
        List<ClockingIn> list = clockingInService.findClockingInByUid(user.getUid(), year, month);
        if(list.isEmpty()) {
        	//代表本月第一次打卡
        	absenteeismDays=workdays;
        }else {
        	absenteeismDays=clockingInService.findAbsenteeismDays(user.getUid(),year,month)-1;
        } 
        ClockingIn clo = new ClockingIn(-1, user, new Date(), null, isLate, null, absenteeismDays, year, month,day);
        clockingInService.addClockingin(clo);
        model.addAttribute("clockin", "clockin");
        return "employee";
	}
}
