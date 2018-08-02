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
	ProductDao productDao;

	@Override
	public List<Product> getProducts(int start) {
		return productDao.findAll(start);
	}

	@Override
	public List<Product> getProductsByCategoryId(int start, int categoryId) {
		return productDao.findAllByCategoryId(start, categoryId);
	}

	@Override
	public int getProductsCount() {
		return productDao.count();
	}

	@Override
	public int getProductsCountByCategoryId(int categoryId) {
		return productDao.countByCategoryId(categoryId);
	}
}
