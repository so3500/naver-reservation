package com.nts.pjt3.displayinfo.dao;

import java.util.List;

import com.nts.pjt3.displayinfo.dto.DisplayInfoImage;

public interface DisplayInfoImageDao {
	public List<DisplayInfoImage> getAllByDisplayInfoId(int displayInfoId);
}
