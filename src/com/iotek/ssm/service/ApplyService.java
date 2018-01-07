package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Apply;

public interface ApplyService {
	/**
	 * 增加应聘记录
	 * @param apply
	 * @return
	 */
	int addApply(Apply apply);
	/**
	 * 删除应聘记录
	 * @param applyId
	 * @return
	 */
	int deleteApply(int applyId);
	/**
	 * 根据应聘状态查询应聘记录
	 * @param status 
	 * @return
	 */
	List<Apply> findApplysByStatus(String status);
	/**
	 * 根据游客的id查询有效的投递记录(未完结的投递记录)
	 * @param uid
	 * @param status 1代表完结，0代表未完结
	 * @return
	 */
	List<Apply> findApplyByUidAndStatus(int uid, String status);
	/**
	 * 根据id查询应聘记录
	 * @param applyId
	 * @return
	 */
	Apply getApplyById(Integer applyId);
	/**
	 * 修改应聘信息
	 * @param apply
	 * @return
	 */
	int updateApply(Apply apply);
	

}
