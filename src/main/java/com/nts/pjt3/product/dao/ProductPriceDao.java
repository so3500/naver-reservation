package com.nts.pjt3.product.dao;

import java.util.List;

import com.nts.pjt3.product.dto.ProductPrice;

public interface ProductPriceDao {
	public List<ProductPrice> getAllByDisplayInfoId(int displayInfoId);
}
