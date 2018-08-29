package com.nts.pjt3.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.product.dao.ProductDao;
import com.nts.pjt3.product.dto.Product;

@Service
public class ProductService {
	
	private static final int LIMIT = 4;

	@Autowired
	private ProductDao productDao;

	public List<Product> getAll(int start) {
		return productDao.getAll(start, LIMIT);
	}

	public List<Product> getAllByCategoryId(int start, int categoryId) {
		return productDao.getAllByCategoryId(start, categoryId, LIMIT);
	}

	public int getAllCount() {
		return productDao.count();
	}

	public int getAllCountByCategoryId(int categoryId) {
		return productDao.countByCategoryId(categoryId);
	}

	public Product getByDisplayInfoId(int displayInfoId) {
		return productDao.getByDisplayInfoId(displayInfoId);
	}
	
	public Product getByProductId(int productId) {
		return productDao.getByProductId(productId);
	}

}
