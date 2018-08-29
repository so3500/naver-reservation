package com.nts.pjt3.fileinfo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.nts.pjt3.fileinfo.dao.FileInfoDao;
import com.nts.pjt3.fileinfo.dto.FileInfo;

public class FileInfoService {
	
	@Autowired
	private FileInfoDao fileInfoDao;

	public int createFileInfo(FileInfo fileInfo) {
		return fileInfoDao.createFileInfo(fileInfo);
	}

}
