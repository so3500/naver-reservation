package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.ProductImage;

public interface ProductImageService {
	public List<ProductImage> getProductImagesByDisplayInfoIdAndType(int displayInfoId, String type);
	public ProductImage getProductImageByDisplayInfoIdAndType(int displayInfoId, String type);
	public ProductImage getProductImageByProductIdAndProductImageId(int productId, int productImageId);	
}
