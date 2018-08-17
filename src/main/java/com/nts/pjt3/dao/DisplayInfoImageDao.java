package com.nts.pjt3.dao;

import java.util.List;

import com.nts.pjt3.dto.DisplayInfoImage;

public interface DisplayInfoImageDao {
	public List<DisplayInfoImage> getAllByDisplayInfoId(int displayInfoId);
}
