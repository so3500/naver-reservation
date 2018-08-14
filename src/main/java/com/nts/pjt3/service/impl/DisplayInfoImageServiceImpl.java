package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.DisplayInfoImageDao;
import com.nts.pjt3.dto.DisplayInfoImage;
import com.nts.pjt3.service.DisplayInfoImageService;

@Service
public class DisplayInfoImageServiceImpl implements DisplayInfoImageService {

	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;

	@Override
	public List<DisplayInfoImage> getDisplayInfoImagesByDisplayInfoId(int displayInfoId) {
		return displayInfoImageDao.getDisplayInfoImagesByDisplayInfoId(displayInfoId);
	}

}
