package com.iotek.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.PositionDao;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.service.PositionService;

@Service("positionService")
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;

	@Override
	public int addPosition(Position position) {
		return positionDao.insertPosition(position);
	}

	@Override
	public Position getPositionById(int pid) {
		return positionDao.queryPositionById(pid);
	}

	@Override
	public List<Position> findAllPositions() {
		return positionDao.queryAllPositions();
	}

	@Override
	public Position getPositionByName(String pName, Integer did) {
		return positionDao.queryPositionByName(pName,did);
	}

}
