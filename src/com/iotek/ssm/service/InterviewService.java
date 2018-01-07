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
}
