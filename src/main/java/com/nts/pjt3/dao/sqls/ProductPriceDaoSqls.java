package com.nts.pjt3.dao.sqls;

public class ProductPriceDaoSqls {

	public static final String SELECT_PRODUCT_PRICES_BY_DISPLAY_INFO_ID =
		"SELECT" +
		" pprice.id," +
		" pprice.product_id," +
		" pprice.price_type_name," +
		" pprice.price," +
		" pprice.discount_rate," +
		" pprice.create_date," +
		" pprice.modify_date" +
		" FROM" +
		" product_price pprice," +
		" product," +
		" display_info dpinfo" +
		" WHERE" +
		" pprice.product_id = product.id AND" +
		" product.id = dpinfo.product_id AND" +
		" dpinfo.id = :displayInfoId;";
}
