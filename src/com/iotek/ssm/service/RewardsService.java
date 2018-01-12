package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Rewards;

public interface RewardsService {
	int addRewards(Rewards rewards);
	
	Rewards getRewardsById(int rewardsId);
	
	List<Rewards> findRewardsByUidAndMonth(int userId,int year,int month);
}
