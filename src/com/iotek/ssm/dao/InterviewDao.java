package com.iotek.ssm.dao;

import com.iotek.ssm.entity.Interview;

public interface InterviewDao {
	/**
	 * �������Լ�¼
	 * @param interview
	 * @return
	 */
	int insertInterview(Interview interview);
	/**
	 * �����û�id��ѯ���Լ�¼
	 * @param uid
	 * @return
	 */
	Interview queryInterviewByUid(int uid);
	
	
	Interview queryInterviewById(Integer interId);
	
	int updateInterview(Interview interview);
}
