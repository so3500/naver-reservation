package com.nts.pjt3.dao.sqls;

public class ProductImageSqls {
	public static final String SELECT_MA_TYPE_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID =
		"SELECT" + 
		" prdimg.product_id," +
		" prdimg.id AS product_image_id," +
		" prdimg.type," +
		" prdimg.file_id AS file_info_id," +
		" finfo.file_name," +
		" finfo.save_file_name," +
		" finfo.content_type," +
		" finfo.delete_flag," +
		" finfo.create_date," +
		" finfo.modify_date" +
		" FROM" +
		" product_image prdimg, file_info finfo, product, display_info dpinfo" +
		" WHERE" +
		" product.id = prdimg.product_id AND" +
		" dpinfo.product_id = product.id AND" +
		" prdimg.file_id = finfo.id AND" +
		" prdimg.type = 'ma' AND" +
		" dpinfo.id = :displayInfoId;";
}
