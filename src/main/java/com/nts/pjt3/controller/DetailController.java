package com.nts.pjt3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3.dto.Product;
import com.nts.pjt3.service.ProductService;

@Controller
public class DetailController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/detail")
	public String detail(@RequestParam("id") int displayInfoId, ModelMap model) {
		// TODO: product EmptyResultDataAccessException 예외처리
		Product product = productService.getByDisplayInfoId(displayInfoId);

		model.put("product", product);
		model.put("displayInfoId", displayInfoId);

		return "detail";
	}
}
