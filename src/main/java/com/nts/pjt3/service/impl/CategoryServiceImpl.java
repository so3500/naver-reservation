package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.CategoryDao;
import com.nts.pjt3.dto.Category;
import com.nts.pjt3.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getCategories() {
		return categoryDao.findAll();
	}

}
