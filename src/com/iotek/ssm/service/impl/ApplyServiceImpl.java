package com.iotek.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.ApplyDao;
import com.iotek.ssm.entity.Apply;
import com.iotek.ssm.service.ApplyService;

@Service("applyService")
public class ApplyServiceImpl implements ApplyService {
	@Autowired
	private ApplyDao applyDao;

	@Override
	public int addApply(Apply apply) {
		return applyDao.insertApply(apply);
	}

	@Override
	public int deleteApply(int applyId) {
		return applyDao.deleteApply(applyId);
	}

	@Override
	public List<Apply> findApplysByStatus(String status) {
		return applyDao.queryApplysByStatus(status);
	}

	@Override
	public List<Apply> findApplyByUidAndStatus(int uid, String status) {
		return applyDao.queryApplyByUidAndStatus(uid,status);
	}

	@Override
	public Apply getApplyById(Integer applyId) {
		return applyDao.queryApplyById(applyId);
	}

	@Override
	public int updateApply(Apply apply) {
		return applyDao.updateApply(apply);
	}

}
