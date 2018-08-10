package com.nts.pjt3.dao.sqls;

public class ProductImageDaoSqls {
	public static final String SELECT_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID_AND_TYPE =
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
		" prdimg.type = :type AND" +
		" dpinfo.id = :displayInfoId";

	public static final String SELECT_PRODUCT_IMAGE_BY_PRODUCT_ID_AND_PRODUCT_IMAGE_ID =
		"SELECT" +
		" product.id AS product_id," +
		" prdimg.id AS product_image_id," +
		" prdimg.type," +
		" prdimg.file_id," +
		" finfo.file_name," +
		" finfo.save_file_name," +
		" finfo.content_type," +
		" finfo.delete_flag," +
		" finfo.create_date," +
		" finfo.modify_date" +
		" FROM" +
		" product, product_image prdimg, file_info finfo" +
		" WHERE" +
		" product.id = prdimg.product_id AND" +
		" prdimg.file_id = finfo.id AND" +
		" product.id = :productId AND" +
		" prdimg.id = :productImageId";
}
