package com.nts.pjt3.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3.dao.sqls.ProductPriceDaoSqls;
import com.nts.pjt3.dto.ProductPrice;

@Repository
public class ProductPriceDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	public ProductPriceDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductPrice> findAllByDisplayInfoId(int displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(ProductPriceDaoSqls.SELECT_PRODUCT_PRICES_BY_DISPLAY_INFO_ID, params, rowMapper);
	}

}
