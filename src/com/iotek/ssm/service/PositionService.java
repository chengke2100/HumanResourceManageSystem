package com.iotek.ssm.service;

import com.iotek.ssm.entity.Position;

public interface PositionService {
	/**
	 * ���ְλ
	 * @param position
	 * @return
	 */
	int addPosition(Position position);
	/**
	 * ����id��ѯְλ
	 * @param pid
	 * @return
	 */
	Position getPositionById(int pid);
}
