package com.nts.pjt3.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3.dao.sqls.DisplayInfoImageSqls;
import com.nts.pjt3.dto.DisplayInfoImage;

@Repository
public class DisplayInfoImageDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<DisplayInfoImage> findAllByDisplayInfoId(int displayInfoId){
		Map<String, Object> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(DisplayInfoImageSqls.SELECT_DISPLAY_INFO_IMAGES_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
	
}
