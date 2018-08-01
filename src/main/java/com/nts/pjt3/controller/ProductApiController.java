package com.nts.pjt3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.dto.Product;
import com.nts.pjt3.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {

	@Autowired
	ProductService productService;

	@GetMapping
	public Map<String, Object> getProducts(
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId) {
		Map<String, Object> map = new HashMap<>();
		List<Product> products;

		if (categoryId == 0) {
			products = productService.getProducts(start);
		} else {
			products = productService.getProductsByCategoryId(start, categoryId);
		}

		map.put("totalCount", productService.getProductsCount());
		map.put("productsCount", products.size());
		map.put("items", products);

		return map;
	}

}
