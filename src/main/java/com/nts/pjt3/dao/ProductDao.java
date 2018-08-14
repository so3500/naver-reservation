package com.nts.pjt3.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.pjt3.dto.Product;

public interface ProductDao {
	public List<Product> getProducts(
		@Param("start") int start,
		@Param("limit") int limit);

	public List<Product> getProductsByCategoryId(
		@Param("start") int start,
		@Param("categoryId") int categoryId,
		@Param("limit") int limit);

	public int count();
	public int countByCategoryId(int categoryId);
	public Product getProductByDisplayInfoId(int displayInfoId);
}
