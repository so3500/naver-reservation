package com.nts.pjt3.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.product.dao.ProductImageDao;
import com.nts.pjt3.product.dto.ProductImage;

@Service
public class ProductImageService {
	
	public static final Integer LIMIT = 4;
		
	@Autowired
	private ProductImageDao productImageDao;

	public List<ProductImage> getAllByDisplayInfoIdAndType(int displayInfoId, String type) {
		return productImageDao.getAllByDisplayInfoIdAndType(displayInfoId, type);
	}
	
	public ProductImage getByDisplayInfoIdAndType(int displayInfoId, String type) {
		return productImageDao.getByDisplayInfoIdAndType(displayInfoId, type);
	}
	
	public ProductImage getByProductIdAndProductImageId(int productId, int productImageId) {
		return productImageDao.getByProductIdAndProductImageId(productId, productImageId);
	}
}
