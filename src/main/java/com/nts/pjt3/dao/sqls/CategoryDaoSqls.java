package com.nts.pjt3.dao.sqls;

public class CategoryDaoSqls {

	public static final String SELECT_ALL = "SELECT c.id, c.name, COUNT(*) AS count" +
		" FROM category AS c, product AS p" +
		" WHERE c.id = p.category_id" +
		" GROUP BY c.id";
}
