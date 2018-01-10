package com.iotek.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.InterviewDao;
import com.iotek.ssm.entity.Interview;
import com.iotek.ssm.service.InterviewService;
@Service("interviewService")
public class InterviewServiceImpl implements InterviewService {
	@Autowired
	private InterviewDao interviewDao;
	
	@Override
	public int addInterview(Interview interview) {
		return interviewDao.insertInterview(interview);
	}

	@Override
	public Interview getInterviewByUid(int uid) {
		return interviewDao.queryInterviewByUid(uid);
	}

	@Override
	public Interview getInterviewById(Integer interId) {
		return interviewDao.queryInterviewById(interId);
	}

	@Override
	public int updateInterview(Interview interview) {
		return interviewDao.updateInterview(interview);
	}

}
