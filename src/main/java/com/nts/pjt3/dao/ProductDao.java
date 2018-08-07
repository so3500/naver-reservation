package com.nts.pjt3.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3.dao.sqls.ProductDaoSqls;
import com.nts.pjt3.dto.Product;
import com.nts.pjt3.service.ProductService;

@Repository
public class ProductDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> findAll(int start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", ProductService.LIMIT);
		return jdbc.query(ProductDaoSqls.SELECT_PRODUCTS, params, rowMapper);
	}

	public List<Product> findAllByCategoryId(int start, int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", ProductService.LIMIT);
		params.put("categoryId", categoryId);
		return jdbc.query(ProductDaoSqls.SELECT_PRODUCTS_BY_CATEGORY_ID, params, rowMapper);
	}

	public int count() {
		return jdbc.queryForObject(ProductDaoSqls.SELECT_PRODUCTS_COUNT, Collections.emptyMap(), Integer.class);
	}

	public int countByCategoryId(int categoryId) {
		Map<String, Object> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(ProductDaoSqls.SELECT_PRODUCTS_COUNT_BY_CATEGORY_ID, params, Integer.class);
	}
	
	public Product findByDisplayInfoId(int displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(ProductDaoSqls.SELECT_PRODUCT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}

}
