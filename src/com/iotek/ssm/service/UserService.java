package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.User;

public interface UserService {
	int addUser(User user);
	
	User findUserByNameAndPassowrd(String userName,String password);

	Boolean isReapat(String userName);

	int updateUser(User user);
	/**
	 * �жϲ��������Ƿ�����ְԱ��
	 * @param did ����id
	 * @param resignationReason �����ְԭ��Ϊ����˵������ְ״̬
	 * @return
	 */
	List<Integer> findUsersIdByDid(Integer did, String resignationReason);

	List<Integer> findUsersIdByPid(Integer pid,  String resignationReason);
	/** 
	 * �����û��������û�
	 * @param deptName
	 * @return
	 */
	User getUserByName(String deptName);
	/**
	 * ɾ�����Ź���Ա�ʻ�
	 * @param did
	 * @return
	 */
	int deleteDepartmentManager(Integer did);

	User getUserByUid(Integer userId);
}
