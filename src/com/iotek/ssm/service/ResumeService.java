package com.iotek.ssm.service;

import com.iotek.ssm.entity.Resume;

public interface ResumeService {
	/**
	 * ��Ӽ���
	 * @param resume
	 * @return
	 */
	int addResume(Resume resume);
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
	Resume getResumeById(int rid);
	/**
	 * �����û�id��ѯ����
	 * @param uid
	 * @return
	 */
	Resume getResumeByUid(int uid);
}
