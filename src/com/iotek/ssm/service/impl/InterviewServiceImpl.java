package com.iotek.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.ApplyDao;
import com.iotek.ssm.dao.InterviewDao;
import com.iotek.ssm.dao.PositionDao;
import com.iotek.ssm.dao.RecruitDao;
import com.iotek.ssm.dao.UserDao;
import com.iotek.ssm.entity.Apply;
import com.iotek.ssm.entity.Department;
import com.iotek.ssm.entity.Interview;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Recruit;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.InterviewService;
@Service("interviewService")
public class InterviewServiceImpl implements InterviewService {
	@Autowired
	private InterviewDao interviewDao;
	@Autowired
	private ApplyDao applyDao;
	@Autowired
	private RecruitDao recruitDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PositionDao positionDao;
	
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

	@Override
	public List<Interview> findIterviewByIsInterviewAndStatus(String isInterview,String status) {
		return interviewDao.queryInterviewByIsInterviewAndStatus(isInterview, status);
	}

	@Override
	public int doInterview(Integer interId) {
		Interview interview = interviewDao.queryInterviewById(interId);
		Apply apply = applyDao.queryApplyById(interview.getApply().getApplyId());
		apply.setStatus("������");
		applyDao.updateApply(apply);
		return apply.getUserId();
	}

	@Override
	public User doHire(Integer interId,int uid) {
		Interview interview = interviewDao.queryInterviewById(interId);
		//�����Լ�¼���Ƿ�¼�øĳ�¼��
		interview.setIsHire("¼��");
		interviewDao.updateInterview(interview);
		//����Ƹ��Ϣ��״̬�ĳ�ʧЧ״̬
		Apply apply = interview.getApply();
		apply = applyDao.queryApplyById(apply.getApplyId());
		Recruit recruit = recruitDao.queryRecruitById(apply.getRecruit().getRecruitsId());
		recruit.setStatus(0);
		recruitDao.updateRecruit(recruit);
		//���ο͵��ʻ��ĳ�Ա�����˺�
		User user = userDao.queryUserById(uid);
		Position position = recruit.getPosition();
		Department department = position.getDepartment();
		user.setDepartment(department);
		user.setEntryDate(new Date());
		user.setPosition(position);
		user.setType(3);
		userDao.updateUser(user);
		return user;
	}

	@Override
	public void noHire(Integer interId) {
		Interview interview = interviewDao.queryInterviewById(interId);
		//�����Լ�¼���Ƿ�¼�øĳɾܾ�
		interview.setIsHire("�ܾ�");
		interviewDao.updateInterview(interview);
	}

}
