package com.iotek.ssm.dao;

import java.util.List;

import com.iotek.ssm.entity.Apply;

public interface ApplyDao {
	/**
	 * ����ӦƸ��Ϣ
	 * @param apply
	 * @return
	 */
	int insertApply(Apply apply);
	/**
	 * ɾ��ӦƸ��Ϣ
	 * @param applyId
	 * @return
	 */
	int deleteApply(int applyId);
	/**
	 * ��ѯ����ӦƸ��Ϣ
	 * �˷�����δʵ�ֲ��apply���recruit
	 * @return
	 */
	List<Apply> queryAllApplys();
}
