package com.iotek.ssm.dao;

import java.util.List;

import com.iotek.ssm.entity.Position;

public interface PositionDao {
	/**
	 * 添加职位
	 * @param position
	 * @return
	 */
	int insertPosition(Position position);
	/**
	 * 根据id查询职位
	 * @param pid
	 * @return
	 */
	Position queryPositionById(int pid);
	
	List<Position> queryAllPositions();
}
