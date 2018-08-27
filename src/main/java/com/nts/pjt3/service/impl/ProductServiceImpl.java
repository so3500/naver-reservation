package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.ProductDao;
import com.nts.pjt3.dto.Product;
import com.nts.pjt3.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getAll(int start) {
		return productDao.getAll(start, ProductService.LIMIT);
	}

	@Override
	public List<Product> getAllByCategoryId(int start, int categoryId) {
		return productDao.getAllByCategoryId(start, categoryId, ProductService.LIMIT);
	}

	@Override
	public int getAllCount() {
		return productDao.count();
	}

	@Override
	public int getAllCountByCategoryId(int categoryId) {
		return productDao.countByCategoryId(categoryId);
	}

	@Override
	public Product getByDisplayInfoId(int displayInfoId) {
		return productDao.getByDisplayInfoId(displayInfoId);
	}
	
	@Override
	public Product getByProductId(int productId) {
		return productDao.getByProductId(productId);
	}

}
