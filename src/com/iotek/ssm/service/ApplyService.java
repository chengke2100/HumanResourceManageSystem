package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.Apply;

public interface ApplyService {
	/**
	 * ����ӦƸ��¼
	 * @param apply
	 * @return
	 */
	int addApply(Apply apply);
	/**
	 * ɾ��ӦƸ��¼
	 * @param applyId
	 * @return
	 */
	int deleteApply(int applyId);
	/**
	 * ����ӦƸ״̬��ѯӦƸ��¼
	 * @param status 
	 * @return
	 */
	List<Apply> findApplysByStatus(String status);
	/**
	 * �����ο͵�id��ѯ��Ч��Ͷ�ݼ�¼(δ����Ͷ�ݼ�¼)
	 * @param uid
	 * @param status 1������ᣬ0����δ���
	 * @return
	 */
	List<Apply> findApplyByUidAndStatus(int uid, String status);
	/**
	 * ����id��ѯӦƸ��¼
	 * @param applyId
	 * @return
	 */
	Apply getApplyById(Integer applyId);
	/**
	 * �޸�ӦƸ��Ϣ
	 * @param apply
	 * @return
	 */
	int updateApply(Apply apply);
	

}
