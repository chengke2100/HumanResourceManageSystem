package com.iotek.ssm.service;

import java.util.List;

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
	/**
	 * ��ѯ����ְλ
	 * @return
	 */
	List<Position> findAllPositions();
	/**
	 * ����ְλ������ָ�������µ�ְλ
	 * @param pName
	 * @param did 
	 * @return
	 */
	Position getPositionByName(String pName, Integer did);
	
	int updatePositon(Position position);
	
	int deletePosition(Integer pid);
}
