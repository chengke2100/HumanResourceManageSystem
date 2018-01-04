package com.iotek.ssm.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iotek.ssm.dao.PositionDao;
import com.iotek.ssm.dao.RecruitDao;
import com.iotek.ssm.dao.ResumeDao;
import com.iotek.ssm.entity.Position;
import com.iotek.ssm.entity.Recruit;
import com.iotek.ssm.entity.Resume;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml","classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class TestRecruitDao {
	@Autowired
	private RecruitDao recruitDao;
	@Autowired
	private PositionDao positionDao;
	
	@Test
	public void testInsertRecruit() {
		Position position = positionDao.queryPositionById(6);
		System.out.println(position);
		Recruit recruit = new Recruit(0, "��ͬ", position, "��������߲��������۵�����Ŀ�Ĺ�����߿���", "����������רҵ�����Ƽ�����ѧ����2������ Java �������飬������ʵ�����õĳ���˼ά����", "15000-20000", 1);
		int res = recruitDao.insertRecruit(recruit);
		System.out.println(res);
	}
	@Test
	public void testqueryResumeById() {
		Recruit recruit = recruitDao.queryRecruitById(1);
		System.out.println(recruit);
	}
	@Test
	public void testupdate() {
		Recruit recruit = recruitDao.queryRecruitById(1);
		System.out.println(recruit);
		recruit.setCompanyName("��ͬ");
		recruit.setSalary("16000-20000");
		System.out.println(recruit);
		int res = recruitDao.updateRecruit(recruit);
		System.out.println(res);
	}
	@Test
	public void testqueryRecruitsByStatus() {
		List<Recruit> list = recruitDao.queryRecruitsByStatus(1);
		System.out.println(list);
	}
}
