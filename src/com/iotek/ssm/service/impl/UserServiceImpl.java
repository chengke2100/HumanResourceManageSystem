package com.iotek.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.ssm.dao.UserDao;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public int addUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User findUserByNameAndPassowrd(String userName, String password) {
		return userDao.queryUserByNameAndPassword1(userName, password);
	}

	@Override
	public Boolean isReapat(String userName) {
		Integer uid = userDao.queryUserName(userName);
		return uid!=null?true:false;
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<Integer> findUsersIdByDid(Integer did, String resignationReason) {
		return userDao.queryUsersIdByDeptId(did, resignationReason);
	}

	@Override
	public List<Integer> findUsersIdByPid(Integer pid, String resignationReason) {
		return userDao.queryUsersIdByPidAndResignationReason(pid,resignationReason);
	}

}
