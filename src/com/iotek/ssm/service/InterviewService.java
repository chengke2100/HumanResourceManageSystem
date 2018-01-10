package com.iotek.ssm.service;

import com.iotek.ssm.entity.Interview;

public interface InterviewService {
	int addInterview(Interview interview);
	/**
	 * �����û�id��ѯ���Լ�¼
	 * @param uid
	 * @return
	 */
	Interview getInterviewByUid(int uid);
	/**
	 * �������Լ�¼id��ѯ���Լ�¼
	 * @param interId
	 * @return
	 */
	Interview getInterviewById(Integer interId);
	
	int updateInterview(Interview interview);
}
