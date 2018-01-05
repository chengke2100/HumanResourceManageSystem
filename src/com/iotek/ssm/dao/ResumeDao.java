package com.iotek.ssm.dao;

import com.iotek.ssm.entity.Resume;

public interface ResumeDao {
	/**
	 * ��Ӽ���
	 * @param resume
	 * @return
	 */
	int insertResume(Resume resume);
	/**
	 * �޸ļ���
	 * @param resume
	 * @return
	 */
	int updateResume(Resume resume);
	/**
	 * ����id��ѯ����
	 * @param rid
	 * @return
	 */
	Resume queryResumeById(int rid);
	/**
	 * �����û�id��ѯ����
	 * @param uid
	 * @return
	 */
	Resume queryResumeByUid(int uid);
}
