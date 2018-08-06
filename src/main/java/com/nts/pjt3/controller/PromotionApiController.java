package com.nts.pjt3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.dto.Promotion;
import com.nts.pjt3.service.PromotionService;

@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public Map<String, Object> getPromotions() {
		Map<String, Object> map = new HashMap<>();
		List<Promotion> promotions = promotionService.getPromotions();
		map.put("promotions", promotions);
		map.put("size", promotions.size());

		return map;
	}
}
