package com.iotek.ssm.service;

import com.iotek.ssm.entity.Interview;

public interface InterviewService {
	int addInterview(Interview interview);
	/**
	 * 根据用户id查询面试记录
	 * @param uid
	 * @return
	 */
	Interview getInterviewByUid(int uid);
	/**
	 * 根据面试记录id查询面试记录
	 * @param interId
	 * @return
	 */
	Interview getInterviewById(Integer interId);
	
	int updateInterview(Interview interview);
}
