package com.iotek.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.RewardsDao;
import com.iotek.ssm.entity.Rewards;
import com.iotek.ssm.service.RewardsService;
@Service("rewardsService")
public class RewardsServiceImpl implements RewardsService {
	@Autowired
	private RewardsDao rewardsDao;

	@Override
	public int addRewards(Rewards rewards) {
		return rewardsDao.insertRewards(rewards);
	}

	@Override
	public Rewards getRewardsById(int rewardsId) {
		return rewardsDao.queryRewardsById(rewardsId);
	}

	@Override
	public List<Rewards> findRewardsByUidAndMonth(int userId, int year, int month) {
		return rewardsDao.queryRewardsByUidAndMonth(userId, year, month);
	}

}
