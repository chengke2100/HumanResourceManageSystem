package com.iotek.ssm.dao;

import com.iotek.ssm.entity.Position;

public interface PositionDao {
	/**
	 * ���ְλ
	 * @param position
	 * @return
	 */
	int insertPosition(Position position);
	/**
	 * ����id��ѯְλ
	 * @param pid
	 * @return
	 */
	Position queryPositionById(int pid);
}
