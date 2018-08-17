package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.DisplayInfoImage;

public interface DisplayInfoImageService {
	public List<DisplayInfoImage> getAllByDisplayInfoId(int displayInfoId);
}
