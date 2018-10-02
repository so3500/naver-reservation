package com.nts.pjt3.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.product.dao.ProductPriceDao;
import com.nts.pjt3.product.dto.ProductPrice;

@Service
public class ProductPriceService {

	@Autowired
	private ProductPriceDao productPriceDao;
	
	public List<ProductPrice> getAllByDisplayInfoId(int displayInfoId) {
		return productPriceDao.getAllByDisplayInfoId(displayInfoId);
	}

}
