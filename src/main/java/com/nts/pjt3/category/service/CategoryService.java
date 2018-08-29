package com.nts.pjt3.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.category.dao.CategoryDao;
import com.nts.pjt3.category.dto.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public List<Category> getAll() {
		return categoryDao.getAll();
	}

}
