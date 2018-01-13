package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Train;

public interface TrainService {
	/**
	 * 添加培训
	 * @param train
	 * @return
	 */
	int addTrain(Train train);
	/**
	 * 修改培训
	 * @param train
	 * @return
	 */
	int updateTrain(Train train);
	/**
	 * 根据id查询培训
	 * @param tid
	 * @return
	 */
	Train getTrainById(int tid);
	/**
	 * 根据部门id查询培训
	 * @param did
	 * @return
	 */
	List<Train> findTrainByDeptId(int did);
	/**
	 * 查询所有培训
	 * @return
	 */
	List<Train> findAllTrains();
}
