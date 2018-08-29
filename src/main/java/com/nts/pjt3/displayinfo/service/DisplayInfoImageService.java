package com.nts.pjt3.displayinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.displayinfo.dao.DisplayInfoImageDao;
import com.nts.pjt3.displayinfo.dto.DisplayInfoImage;

@Service
public class DisplayInfoImageService {

	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;

	public List<DisplayInfoImage> getAllByDisplayInfoId(int displayInfoId) {
		return displayInfoImageDao.getAllByDisplayInfoId(displayInfoId);
	}

}
