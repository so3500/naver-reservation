package com.nts.pjt3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nts.pjt3.dao.FileDao;
import com.nts.pjt3.dto.File;
import com.nts.pjt3.service.FileService;

public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileDao fileDao;

	@Override
	public int createFile(File file) {
		return fileDao.createFile(file);
	}

}
