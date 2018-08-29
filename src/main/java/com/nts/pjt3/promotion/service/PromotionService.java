package com.nts.pjt3.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.promotion.dao.PromotionDao;
import com.nts.pjt3.promotion.dto.Promotion;

@Service
public class PromotionService {

	@Autowired
	private PromotionDao promotionDao;
	
	public List<Promotion> getAll() {
		return promotionDao.getAll();
	}

}
