package com.nts.pjt3.controller;

import java.util.Collections;
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
	private ProductService productService;

	private final int ALL_CATEGORY = 0;

	@GetMapping
	public Map<String, Object> getProducts(
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId) {
		List<Product> products = Collections.emptyList();
		
		int productsCount = getProductsCountByCategoryId(categoryId);
		if (productsCount > 0) {
			products = getProductsByCategoryId(start, categoryId);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", productsCount);
		map.put("productsCount", products.size());
		map.put("products", products);

		return map;
	}

	private List<Product> getProductsByCategoryId(int start, int categoryId) {
		if (categoryId == ALL_CATEGORY) {
			return productService.getProducts(start);
		} else {
			return productService.getProductsByCategoryId(start, categoryId);
		}
	}

	private int getProductsCountByCategoryId(int categoryId) {
		if (categoryId == ALL_CATEGORY) {
			return productService.getProductsCount();
		} else {
			return productService.getProductsCountByCategoryId(categoryId);
		}
	}

}
