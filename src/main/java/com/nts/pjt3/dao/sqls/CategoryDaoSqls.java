package com.nts.pjt3.dao.sqls;

public class CategoryDaoSqls {

	public static final String SELECT_ALL = 
		"SELECT category.id, category.name, COUNT(*) AS count" +
		" FROM category, product, display_info disinfo" +
		" WHERE category.id = product.category_id AND product.id = disinfo.product_id" +
		" GROUP BY category.id";
}
