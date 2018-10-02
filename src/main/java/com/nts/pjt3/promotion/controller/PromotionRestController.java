package com.nts.pjt3.promotion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.promotion.dto.Promotion;
import com.nts.pjt3.promotion.service.PromotionService;

@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionRestController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public Map<String, Object> getPromotions() {
		Map<String, Object> map = new HashMap<>();
		List<Promotion> promotions = promotionService.getAll();
		map.put("promotions", promotions);
		map.put("size", promotions.size());

		return map;
	}
}
