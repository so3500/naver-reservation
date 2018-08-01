package com.nts.pjt3.dao.sqls;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = "SELECT pm.id AS id, p.id AS product_id, c.id AS category_id," +
		" c.name AS category_name, p.description, pi.id AS product_image_id" +
		" FROM promotion AS pm, product AS p, category AS c, product_image AS pi" +
		" WHERE pm.product_id = p.id AND p.category_id = c.id AND p.id = pi.product_id";
}
