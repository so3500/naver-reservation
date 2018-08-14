package com.nts.pjt3.dao;

import java.util.List;

import com.nts.pjt3.dto.DisplayInfoImage;

public interface DisplayInfoImageDao {
	List<DisplayInfoImage> getDisplayInfoImagesByDisplayInfoId(int displayInfoId);
}
