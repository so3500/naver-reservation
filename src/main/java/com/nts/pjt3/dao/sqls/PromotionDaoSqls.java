package com.nts.pjt3.dao.sqls;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = 
		"SELECT product.id AS product_id, product.description, " +
		" promotion.id AS id," +
		" category.id AS category_id,category.name AS category_name," +
		" pimg.id AS product_image_id" +
		" FROM promotion, product, category, product_image pimg" +
		" WHERE promotion.product_id = product.id AND product.category_id = category.id AND product.id = pimg.product_id AND pimg.type = 'ma'";
}
