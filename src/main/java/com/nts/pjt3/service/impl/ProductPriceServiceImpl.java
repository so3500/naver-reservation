package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.ProductPriceDao;
import com.nts.pjt3.dto.ProductPrice;
import com.nts.pjt3.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	private ProductPriceDao productPriceDao;
	
	@Override
	public List<ProductPrice> getProductPricesByDisplayInfoId(int displayInfoId) {
		return productPriceDao.getProductPricesByDisplayInfoId(displayInfoId);
	}

}
