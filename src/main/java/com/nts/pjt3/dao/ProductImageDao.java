package com.nts.pjt3.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3.dao.sqls.ProductImageDaoSqls;
import com.nts.pjt3.dto.ProductImage;

@Repository
public class ProductImageDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);

	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductImage> findAllByDisplayInfoIdAndType(int displayInfoId, String type) {
		Map<String, Object> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("type", type);
		return jdbc.query(ProductImageDaoSqls.SELECT_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID_AND_TYPE, params, rowMapper);
	}
	
	public ProductImage findByDisplayInfoIdAndType(int displayInfoId, String type) {
		Map<String, Object> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("type", type);
		return jdbc.queryForObject(ProductImageDaoSqls.SELECT_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID_AND_TYPE, params, rowMapper);
	}
	
}
