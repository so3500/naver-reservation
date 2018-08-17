package com.nts.pjt3.dao;

import java.util.List;

import com.nts.pjt3.dto.ProductPrice;

public interface ProductPriceDao {
	public List<ProductPrice> getAllByDisplayInfoId(int displayInfoId);
}
