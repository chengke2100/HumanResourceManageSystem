package com.iotek.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.ClockingInDao;
import com.iotek.ssm.entity.ClockingIn;
import com.iotek.ssm.service.ClockingInService;
@Service("clockingInService")
public class ClockingInServiceImpl implements ClockingInService {
	@Autowired
	private ClockingInDao clockingInDao;
	
	@Override
	public int addClockingin(ClockingIn clo) {
		return clockingInDao.insertClockingin(clo);
	}

	@Override
	public int updateClockingin(ClockingIn clo) {
		return clockingInDao.updateClockingin(clo);
	}

	@Override
	public ClockingIn getClockingInById(int cloId) {
		return clockingInDao.queryClockingInById(cloId);
	}

	@Override
	public List<ClockingIn> findClockingInByUid(int userId, int year, int month) {
		return clockingInDao.queryClockingInByUid(userId, year, month);
	}

	@Override
	public int findAbsenteeismDays(int userId, int year, int month) {
		return clockingInDao.queryAbsenteeismDays(userId, year, month);
	}

}
