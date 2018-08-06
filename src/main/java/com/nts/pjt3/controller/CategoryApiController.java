package com.nts.pjt3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.dto.Category;
import com.nts.pjt3.service.CategoryService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public Map<String, Object> getCategories() {
		Map<String, Object> map = new HashMap<>();
		List<Category> categories = categoryService.getCategories();
		map.put("categories", categories);
		map.put("size", categories.size());

		return map;
	}

}
