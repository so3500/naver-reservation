package com.nts.pjt3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3.dto.Product;
import com.nts.pjt3.service.ProductService;

@Controller
public class ReviewController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/review")
	public String review(@RequestParam(name = "id") int displayInfoId, ModelMap model) {
		Product product = productService.getByDisplayInfoId(displayInfoId);

		model.put("product", product);

		return "review";
	}
	
	@GetMapping(path = "/reviweWrite")
	public String reviweWrite() {
		return "reviweWrite";
	}
}
