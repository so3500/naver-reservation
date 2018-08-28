package com.nts.pjt3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nts.pjt3.dao.FileInfoDao;
import com.nts.pjt3.dto.FileInfo;
import com.nts.pjt3.service.FileInfoService;

public class FileInfoServiceImpl implements FileInfoService {
	
	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	public int createFileInfo(FileInfo fileInfo) {
		return fileInfoDao.createFileInfo(fileInfo);
	}

}
