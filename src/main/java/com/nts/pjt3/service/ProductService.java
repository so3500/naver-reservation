package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> getProducts(int start);
	public List<Product> getProductsByCategoryId(int start, int categoryId);
	public int getProductsCount();
	public int getProductsCountByCategoryId(int categoryId);
	public Product getProductByDisplayInfoId(int displayInfoId);
}
