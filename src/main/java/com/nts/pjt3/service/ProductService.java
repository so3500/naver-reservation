package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> getAll(int start);
	public List<Product> getAllByCategoryId(int start, int categoryId);
	public int getAllCount();
	public int getAllCountByCategoryId(int categoryId);
	public Product getByDisplayInfoId(int displayInfoId);
	Product getByProductId(int productId);
}
