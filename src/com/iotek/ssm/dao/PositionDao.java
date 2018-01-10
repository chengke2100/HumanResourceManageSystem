package com.iotek.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	/**
	 * 修改职位信息
	 * @param position
	 * @return
	 */
	int updatePositon(Position position);
	
	List<Position> queryAllPositions();
	
	Position queryPositionByName(@Param(value="pName")String pName, @Param(value="did")Integer did);
	
	int deletePosition(Integer pid);
}
