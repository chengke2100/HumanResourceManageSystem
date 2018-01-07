package com.iotek.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotek.ssm.entity.Apply;

public interface ApplyDao {
	/**
	 * ����ӦƸ��Ϣ
	 * @param apply
	 * @return
	 */
	int insertApply(Apply apply);
	/**
	 * ɾ��ӦƸ��Ϣ
	 * @param applyId
	 * @return
	 */
	int deleteApply(int applyId);

	List<Apply> queryApplysByStatus(String status);
	
	List<Apply> queryApplyByUidAndStatus(@Param(value="uid")int uid,@Param(value="status")String status);
	
	Apply queryApplyById(Integer applyId);
	/**
	 * �޸�ӦƸ��Ϣ
	 * @param apply
	 * @return
	 */
	int updateApply(Apply apply);
	
	

}
