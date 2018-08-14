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
	public List<Product> getProducts(int start) {
		return productDao.getProducts(start, ProductService.LIMIT);
	}

	@Override
	public List<Product> getProductsByCategoryId(int start, int categoryId) {
		return productDao.getProductsByCategoryId(start, categoryId, ProductService.LIMIT);
	}

	@Override
	public int getProductsCount() {
		return productDao.count();
	}

	@Override
	public int getProductsCountByCategoryId(int categoryId) {
		return productDao.countByCategoryId(categoryId);
	}

	@Override
	public Product getProductByDisplayInfoId(int displayInfoId) {
		return productDao.getProductByDisplayInfoId(displayInfoId);
	}

}
