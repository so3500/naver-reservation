package com.nts.pjt3.dao.sqls;

public class ProductDaoSqls {

	public static final String SELECT_ALL = 
		"SELECT category.name, category.id AS category_id," +
		" product.id, product.description, product.content, product.event, product.create_date, product.modify_date," +
		" dpinfo.id AS display_info_id, dpinfo.opening_hours, dpinfo.place_name, dpinfo.place_lot, dpinfo.place_street, dpinfo.tel, dpinfo.homepage, dpinfo.email" +
		" FROM product, display_info dpinfo, category" +
		" WHERE product.id = dpinfo.product_id AND product.category_id = category.id" +
		" LIMIT :start, :limit";

	public static final String SELECT_ALL_BY_CATEGORY_ID = 
		"SELECT category.name, category.id AS category_id," +
		" product.id, product.description, product.content, product.event, product.create_date, product.modify_date," +
		" dpinfo.id AS display_info_id, dpinfo.opening_hours, dpinfo.place_name, dpinfo.place_lot, dpinfo.place_street, dpinfo.tel, dpinfo.homepage, dpinfo.email" +
		" FROM product, display_info dpinfo, category" +
		" WHERE product.id = dpinfo.product_id AND product.category_id = category.id AND category.id = :categoryId" +
		" LIMIT :start, :limit";

	public static final String SELECT_ALL_COUNT = 
		"SELECT count(*)" +
		" FROM product, display_info dpinfo, category" +
		" WHERE product.id = dpinfo.product_id AND product.category_id = category.id";
	
	public static final String SELECT_ALL_COUNT_BY_CATEGORY_ID = 
		"SELECT count(*)" +
		" FROM product, display_info dpinfo, category" +
		" WHERE product.id = dpinfo.product_id AND product.category_id = category.id AND category.id = :categoryId";

}
