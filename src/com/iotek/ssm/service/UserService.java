package com.iotek.ssm.service;

import java.util.List;

import com.iotek.ssm.entity.User;

public interface UserService {
	int addUser(User user);
	
	User findUserByNameAndPassowrd(String userName,String password);

	Boolean isReapat(String userName);

	int updateUser(User user);
	/**
	 * 判断部门里面是否有在职员工
	 * @param did 部门id
	 * @param resignationReason 如果离职原因不为空则说明是在职状态
	 * @return
	 */
	List<Integer> findUsersIdByDid(Integer did, String resignationReason);

	List<Integer> findUsersIdByPid(Integer pid,  String resignationReason);
	/** 
	 * 根据用户名查找用户
	 * @param deptName
	 * @return
	 */
	User getUserByName(String deptName);
	/**
	 * 删除部门管理员帐户
	 * @param did
	 * @return
	 */
	int deleteDepartmentManager(Integer did);

	User getUserByUid(Integer userId);
}
