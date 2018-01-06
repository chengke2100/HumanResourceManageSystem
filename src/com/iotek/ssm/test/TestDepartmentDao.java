package com.iotek.ssm.test;


import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iotek.ssm.dao.DepartmentDao;
import com.iotek.ssm.dao.PositionDao;

import com.iotek.ssm.entity.Department;
import com.iotek.ssm.entity.Position;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml","classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class TestDepartmentDao {
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PositionDao positionDao;
	
	@Test
	public void testinsertDepartment() {
		Department department = new Department(1, "销售部", new Date(), null);
		Position position = new Position(-1, "销售主管", department, new Date(), null);
//		User user = new User(-1, "aaa", "111", 1, new Date(), department, position, null);
//		Set<User> users = new HashSet<>();
//		users.add(user);
//		position.setUsers(users);
//		Set<Position> positions = new HashSet<>();
//		positions.add(position);
//		department.setPositions(positions);
		int res1 = positionDao.insertPosition(position);
//		System.out.println(res1);
//		int res2 = departmentDao.insertDepartment(department);
		System.out.println(res1);
	}
	
	@Test
	public void testqueryDepartmentById() {
		Department department = departmentDao.queryDepartmentById(1);
		System.out.println(department);
	}
	
	@Test
	public void queryPositionById() {
		Position position = positionDao.queryPositionById(1);
		System.out.println(position);
	}

}
