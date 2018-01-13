package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Train;

public interface TrainService {
	/**
	 * �����ѵ
	 * @param train
	 * @return
	 */
	int addTrain(Train train);
	/**
	 * �޸���ѵ
	 * @param train
	 * @return
	 */
	int updateTrain(Train train);
	/**
	 * ����id��ѯ��ѵ
	 * @param tid
	 * @return
	 */
	Train getTrainById(int tid);
	/**
	 * ���ݲ���id��ѯ��ѵ
	 * @param did
	 * @return
	 */
	List<Train> findTrainByDeptId(int did);
	/**
	 * ��ѯ������ѵ
	 * @return
	 */
	List<Train> findAllTrains();
}
