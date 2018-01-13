package com.iotek.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.TrainDao;
import com.iotek.ssm.entity.Train;
import com.iotek.ssm.service.TrainService;
@Service("trainService")
public class TrainServiceImpl implements TrainService {
	@Autowired
	private TrainDao trainDao;
	
	@Override
	public int addTrain(Train train) {
		return trainDao.insertTrain(train);
	}

	@Override
	public int updateTrain(Train train) {
		return trainDao.updateTrain(train);
	}

	@Override
	public Train getTrainById(int tid) {
		return trainDao.queryTrainById(tid);
	}

	@Override
	public List<Train> findTrainByDeptId(int did) {
		return trainDao.queryTrainByDeptId(did);
	}

	@Override
	public List<Train> findAllTrains() {
		return trainDao.queryAllTrains();
	}

}
