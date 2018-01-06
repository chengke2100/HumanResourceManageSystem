package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Recruit;

public interface RecruitService {
	/**
	 * 发布招聘信息
	 * @param recruit
	 * @return
	 */
	int addResruit(Recruit recruit);
	/**
	 * 修改招聘信息
	 * @param recruit
	 * @return
	 */
	int updateRecruit(Recruit recruit);
	/**
	 * 根据id查询招聘信息
	 * @param recruitId
	 * @return
	 */
	Recruit getRecruitBuId(int recruitId);
	/**
	 * 根据招聘状态查询招聘信息
	 * @param status(0/1)0代表已被关闭的招聘，1代表正在招聘
	 * @return
	 */
	List<Recruit> findRecruitsByStatus(int status);
}
