package com.nts.pjt3.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.pjt3.dto.ProductImage;

public interface ProductImageDao {
	public List<ProductImage> getProductImagesByDisplayInfoIdAndType(
		@Param("displayInfoId") int displayInfoId,
		@Param("type") String type);

	public ProductImage getProductImageByDisplayInfoIdAndType(
		@Param("displayInfoId") int displayInfoId,
		@Param("type") String type);
}
