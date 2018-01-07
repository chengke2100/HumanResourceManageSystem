package com.iotek.ssm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iotek.ssm.dao.ResumeDao;
import com.iotek.ssm.entity.Resume;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml","classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class TestResumeDao {
	@Autowired
	private ResumeDao resumeDao;
	
	@Test
	public void testInsertResume() {
		Resume resume = new Resume(-1, 1, "张三", "男", 18, "本科", "15286697218", "25452@qq.com", null, "5000-6000", "ss", "2", "11", "篮球","否");
		int res = resumeDao.insertResume(resume);
		System.out.println(res);
	}
	@Test
	public void testqueryResumeById() {
		Resume resume = resumeDao.queryResumeByUid(11);
		System.out.println(resume);
	}
	@Test
	public void testupdate() {
		Resume resume = resumeDao.queryResumeById(1);
		resume.setAge(25);
		int res = resumeDao.updateResume(resume);
		System.out.println(res);
	}
}
