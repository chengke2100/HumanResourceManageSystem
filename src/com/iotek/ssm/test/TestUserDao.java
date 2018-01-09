package com.iotek.ssm.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iotek.ssm.dao.UserDao;
import com.iotek.ssm.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml","classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class TestUserDao {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testinsertUser() {
		User user = new User("jack", "123456", 0, new Date());
		int res = userDao.insertUser(user);
		System.out.println(res);
	}
	
	@Test
	public void testqueryUserById() {
		User user = userDao.queryUserById(2);
		user.setUserName("kska");
		int res = userDao.updateUser(user);
		System.out.println(res);
	}
	
	@Test
	public void testqueryUserByNameAndPassword() {
		User user = userDao.queryUserByNameAndPassword1("Tom", "12342132");
		System.out.println(user);
	}
	
	@Test
	public void testqueryAllUsers() {
		List<User> users = userDao.queryAllUsers();
		System.out.println(users);
	}
	
	@Test
	public void testqueryUserName() {
		Integer res = userDao.queryUserName("aab");
		System.out.println(res);
	}
	
	@Test
	public void testqueryUserIdBydid() {
		List<Integer> list = userDao.queryUsersIdByDeptId(1, null);
		System.out.println(list);
	}
}
