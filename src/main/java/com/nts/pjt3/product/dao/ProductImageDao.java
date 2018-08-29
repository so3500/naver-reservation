package com.nts.pjt3.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.pjt3.product.dto.ProductImage;

public interface ProductImageDao {
	public List<ProductImage> getAllByDisplayInfoIdAndType(
		@Param("displayInfoId") int displayInfoId
		, @Param("type") String type);

	public ProductImage getByDisplayInfoIdAndType(
		@Param("displayInfoId") int displayInfoId
		, @Param("type") String type);
	
	public ProductImage getByProductIdAndProductImageId(
		@Param("productId") int productId
		, @Param("productImageId") int productImageId);
		
}
