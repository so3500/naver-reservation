package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.ProductImage;

public interface ProductImageService {
	public List<ProductImage> getAllByDisplayInfoIdAndType(int displayInfoId, String type);
	public ProductImage getByDisplayInfoIdAndType(int displayInfoId, String type);
}
