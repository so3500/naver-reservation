package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.ProductPrice;

public interface ProductPriceService {
	public List<ProductPrice> getAllByDisplayInfoId(int displayInfoId);
}
