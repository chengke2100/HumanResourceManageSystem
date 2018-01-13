package com.iotek.ssm.dao;

import java.util.List;

import com.iotek.ssm.entity.Train;

public interface TrainDao {
	int insertTrain(Train train);
	
	int updateTrain(Train train);
	
	Train queryTrainById(int tid);
	
	List<Train> queryTrainByDeptId(int did);

	List<Train> queryAllTrains();
}
