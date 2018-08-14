package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.PromotionDao;
import com.nts.pjt3.dto.Promotion;
import com.nts.pjt3.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionDao promotionDao;
	
	@Override
	public List<Promotion> getPromotions() {
		return promotionDao.getPromotions();
	}

}
