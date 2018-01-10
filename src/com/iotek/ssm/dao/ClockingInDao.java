package com.iotek.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotek.ssm.entity.ClockingIn;

public interface ClockingInDao {
	int insertClockingin(ClockingIn clo);
	
	int updateClockingin(ClockingIn clo);
	
	ClockingIn queryClockingInById(int cloId);
	
	List<ClockingIn> queryClockingInByUid(@Param("userId")int userId,@Param("year")int year,@Param("month")int month);
	
	int queryAbsenteeismDays(@Param("userId")int userId,@Param("year")int year,@Param("month")int month);
}
