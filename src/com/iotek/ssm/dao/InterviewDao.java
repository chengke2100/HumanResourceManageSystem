package com.iotek.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotek.ssm.entity.Interview;

public interface InterviewDao {
	/**
	 * 增加面试记录
	 * @param interview
	 * @return
	 */
	int insertInterview(Interview interview);
	/**
	 * 根据用户id查询面试记录
	 * @param uid
	 * @return
	 */
	Interview queryInterviewByUid(int uid);
	
	
	Interview queryInterviewById(Integer interId);
	
	int updateInterview(Interview interview);
	
	List<Interview> queryInterviewByIsInterviewAndStatus(@Param(value="isInterview")String isInterview,@Param(value="status")String status);
}
