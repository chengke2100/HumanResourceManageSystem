package com.iotek.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotek.ssm.entity.Rewards;

public interface RewardsDao {
	/**
	 * 添加奖惩记录
	 * @param rewards
	 * @return
	 */
	int insertRewards(Rewards rewards);
	
	Rewards queryRewardsById(int rewardsId);
	/**
	 * 查询某个用户某个月的赏罚记录
	 * @param userId
	 * @param year
	 * @param month
	 * @return
	 */
	List<Rewards> queryRewardsByUidAndMonth(@Param("userId")int userId,@Param("year")int year,@Param("month")int month);
}
