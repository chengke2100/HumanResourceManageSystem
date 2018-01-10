package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.ClockingIn;

public interface ClockingInService {
	/**
	 * ��Ӵ򿨼�¼
	 * @param clo
	 * @return
	 */
	int addClockingin(ClockingIn clo);
	/**
	 * ���´򿨼�¼
	 * @param clo
	 * @return
	 */
	int updateClockingin(ClockingIn clo);
	/**
	 * ���ݴ򿨼�¼id�鿼�ڼ�¼
	 * @param cloId
	 * @return
	 */
	ClockingIn getClockingInById(int cloId);
	/**
	 * ����ĳ��Ա��ĳ���µĿ��ڼ�¼
	 * @param userId
	 * @param year
	 * @param month
	 * @return
	 */
	List<ClockingIn> findClockingInByUid(int userId,int year,int month);
	/**
	 * ��ѯ��ǰ����ȱ������
	 * @param uid
	 * @param year
	 * @param month
	 * @return
	 */
	int findAbsenteeismDays(int uid, int year, int month);
}
