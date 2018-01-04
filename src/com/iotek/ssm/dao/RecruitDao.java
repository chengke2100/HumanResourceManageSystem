package com.iotek.ssm.dao;

import java.util.List;

import com.iotek.ssm.entity.Recruit;

public interface RecruitDao {
	/**
	 * �����Ƹ��Ϣ
	 * @param recruit
	 * @return
	 */
	int insertRecruit(Recruit recruit);
	/**
	 * �޸���Ƹ��Ϣ
	 * @param recruit
	 * @return
	 */
	int updateRecruit(Recruit recruit);
	/**
	 * ����id��ѯ��Ƹ��Ϣ
	 * @param recruitId
	 * @return
	 */
	Recruit queryRecruitById(int recruitId);
	/**
	 * ������Ƹ״̬��ѯ��Ƹ��Ϣ
	 * @param status(0/1)0�����ѱ��رյ���Ƹ��1����������Ƹ
	 * @return
	 */
	List<Recruit> queryRecruitsByStatus(int status);
}
