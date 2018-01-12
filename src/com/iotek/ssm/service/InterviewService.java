package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Interview;
import com.iotek.ssm.entity.User;

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
	
	List<Interview> findIterviewByIsInterviewAndStatus(String isInterview,String status);
	/**
	 * 面试员工
	 * @param interId
	 * @return
	 */
	int doInterview(Integer interId);
	/**
	 * 录用员工
	 * @param interId 面试记录id
	 * @param uid 用户id
	 * @param basePay 
	 */
	User doHire(Integer interId,int uid, Integer basePay);
	/**
	 * 不录用员工
	 * @param interId
	 */
	void noHire(Integer interId);
}
