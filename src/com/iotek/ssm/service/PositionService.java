package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Position;

public interface PositionService {
	/**
	 * 添加职位
	 * @param position
	 * @return
	 */
	int addPosition(Position position);
	/**
	 * 根据id查询职位
	 * @param pid
	 * @return
	 */
	Position getPositionById(int pid);
	/**
	 * 查询所有职位
	 * @return
	 */
	List<Position> findAllPositions();
	/**
	 * 根据职位名查找指定部门下的职位
	 * @param pName
	 * @param did 
	 * @return
	 */
	Position getPositionByName(String pName, Integer did);
	
	int updatePositon(Position position);
	
	int deletePosition(Integer pid);
}
