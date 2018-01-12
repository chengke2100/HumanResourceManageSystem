package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Interview;
import com.iotek.ssm.entity.User;

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
	
	List<Interview> findIterviewByIsInterviewAndStatus(String isInterview,String status);
	/**
	 * ����Ա��
	 * @param interId
	 * @return
	 */
	int doInterview(Integer interId);
	/**
	 * ¼��Ա��
	 * @param interId ���Լ�¼id
	 * @param uid �û�id
	 * @param basePay 
	 */
	User doHire(Integer interId,int uid, Integer basePay);
	/**
	 * ��¼��Ա��
	 * @param interId
	 */
	void noHire(Integer interId);
}
