package com.iotek.ssm.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iotek.ssm.dao.EmployeeDao;
import com.iotek.ssm.dao.InterviewDao;
import com.iotek.ssm.dao.UserDao;
import com.iotek.ssm.entity.Employee;
import com.iotek.ssm.entity.Interview;
import com.iotek.ssm.entity.User;
import com.iotek.ssm.util.MyUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml","classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class TestUserDao {
	@Autowired
	private UserDao userDao;
	@Autowired
	private InterviewDao interviewDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Test
	public void testinsertUser() {
		User user = new User("jack", "123456", 0, new Date());
		int res = userDao.insertUser(user);
		System.out.println(res);
	}
	
	@Test
	public void testqueryUserById() {
		User user = userDao.queryUserById(8);
		
		System.out.println(user);
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
	
	@Test
	public void testqueryInterviewByUid() {
		Interview interview = interviewDao.queryInterviewByUid(8);
		interview.setIsInterview("∞¥ ±√Ê ‘");
		int res = interviewDao.updateInterview(interview);
		System.out.println(res);
	}
	
	@Test
	public void testUtil() {
		int workdays = MyUtil.getWorkdays(2018, 1);
		System.out.println(workdays);
	}
	
	@Test
	public void testEmplyeeDao() {
		List<Employee> list = employeeDao.queryEmployeesByIsOnJob();
		System.out.println(list);
	}
	
}
