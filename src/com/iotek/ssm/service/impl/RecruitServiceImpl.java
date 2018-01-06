package com.iotek.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.RecruitDao;
import com.iotek.ssm.entity.Recruit;
import com.iotek.ssm.service.RecruitService;
@Service("RecruitService")
public class RecruitServiceImpl implements RecruitService {
	@Autowired
	private RecruitDao recruitDao;

	@Override
	public int addResruit(Recruit recruit) {
		return recruitDao.insertRecruit(recruit);
	}

	@Override
	public int updateRecruit(Recruit recruit) {
		return recruitDao.updateRecruit(recruit);
	}

	@Override
	public Recruit getRecruitBuId(int recruitId) {
		return recruitDao.queryRecruitById(recruitId);
	}

	@Override
	public List<Recruit> findRecruitsByStatus(int status) {
		return recruitDao.queryRecruitsByStatus(status);
	}

}
