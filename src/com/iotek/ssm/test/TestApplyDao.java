package com.iotek.ssm.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iotek.ssm.dao.ApplyDao;
import com.iotek.ssm.dao.ResumeDao;
import com.iotek.ssm.entity.Apply;
import com.iotek.ssm.entity.Resume;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml","classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class TestApplyDao {
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ApplyDao applyDao;
	@Test
	public void testInsertApply() {
		Apply apply = new Apply(0, null, 1, new Date(), false, "Œ¥√Ê ‘");
		int res = applyDao.insertApply(apply);
		System.out.println(res);
	}
	@Test
	public void testqueryResumeById() {
		int res = applyDao.deleteApply(1);
		System.out.println(res);
	}
	@Test
	public void testupdate() {
		Apply apply = applyDao.queryApplyById(3);
		System.out.println(apply);
	}
}
