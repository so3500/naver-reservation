package com.nts.pjt3.dao.sqls;

public class ProductDaoSqls {
	
	public static final String SELECT_ALL = "SELECT p.id, c.id AS category_id, d.id AS display_info_id, c.name," +
		" p.description, p.content, p.event, d.opening_hours, d.place_name, d.place_lot, d.place_street," +
		" d.tel, d.homepage, d.email, p.create_date, p.modify_date" +
		" FROM product AS p, display_info AS d, category AS c" +
		" WHERE p.id = d.product_id AND p.category_id = c.id" + 
		" LIMIT :start, :limit";
	
	public static final String SELECT_ALL_BY_CATEGORY_ID = "SELECT p.id, c.id AS category_id, d.id AS display_info_id, c.name," +
		" p.description, p.content, p.event, d.opening_hours, d.place_name, d.place_lot, d.place_street," +
		" d.tel, d.homepage, d.email, p.create_date, p.modify_date" +
		" FROM product AS p, display_info AS d, category AS c" +
		" WHERE p.id = d.product_id AND p.category_id = c.id AND c.id = :categoryId" + 
		" LIMIT :start, :limit";
	
	public static final String SELECT_ALL_COUNT = "SELECT count(*) FROM product";
}
