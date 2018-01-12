package com.iotek.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotek.ssm.entity.Rewards;

public interface RewardsDao {
	/**
	 * ��ӽ��ͼ�¼
	 * @param rewards
	 * @return
	 */
	int insertRewards(Rewards rewards);
	
	Rewards queryRewardsById(int rewardsId);
	/**
	 * ��ѯĳ���û�ĳ���µ��ͷ���¼
	 * @param userId
	 * @param year
	 * @param month
	 * @return
	 */
	List<Rewards> queryRewardsByUidAndMonth(@Param("userId")int userId,@Param("year")int year,@Param("month")int month);
}
