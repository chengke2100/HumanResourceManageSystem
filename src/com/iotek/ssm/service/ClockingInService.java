package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.ClockingIn;

public interface ClockingInService {
	/**
	 * 添加打卡记录
	 * @param clo
	 * @return
	 */
	int addClockingin(ClockingIn clo);
	/**
	 * 更新打卡记录
	 * @param clo
	 * @return
	 */
	int updateClockingin(ClockingIn clo);
	/**
	 * 根据打卡记录id查考勤记录
	 * @param cloId
	 * @return
	 */
	ClockingIn getClockingInById(int cloId);
	/**
	 * 查找某个员工某个月的考勤记录
	 * @param userId
	 * @param year
	 * @param month
	 * @return
	 */
	List<ClockingIn> findClockingInByUid(int userId,int year,int month);
	/**
	 * 查询当前当月缺勤天数
	 * @param uid
	 * @param year
	 * @param month
	 * @return
	 */
	int findAbsenteeismDays(int uid, int year, int month);
}
