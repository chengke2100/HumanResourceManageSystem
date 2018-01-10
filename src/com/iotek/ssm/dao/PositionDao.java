package com.iotek.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	/**
	 * �޸�ְλ��Ϣ
	 * @param position
	 * @return
	 */
	int updatePositon(Position position);
	
	List<Position> queryAllPositions();
	
	Position queryPositionByName(@Param(value="pName")String pName, @Param(value="did")Integer did);
	
	int deletePosition(Integer pid);
}
