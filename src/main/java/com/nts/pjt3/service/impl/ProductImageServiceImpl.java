package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.ProductImageDao;
import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	
	@Autowired
	private ProductImageDao productImageDao;

	@Override
	public List<ProductImage> getProductImagesByDisplayInfoIdAndType(int displayInfoId, String type) {
		return productImageDao.getProductImagesByDisplayInfoIdAndType(displayInfoId, type);
	}
	
	@Override
	public ProductImage getProductImageByDisplayInfoIdAndType(int displayInfoId, String type) {
		return productImageDao.getProductImageByDisplayInfoIdAndType(displayInfoId, type);
	}
	
}
