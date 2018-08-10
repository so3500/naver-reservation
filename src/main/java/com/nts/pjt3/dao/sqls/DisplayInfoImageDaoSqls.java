package com.nts.pjt3.dao.sqls;

public class DisplayInfoImageDaoSqls {

	public static final String SELECT_DISPLAY_INFO_IMAGES_BY_DISPLAY_INFO_ID =
		"SELECT" +
		" dpinfoimg.id," +
		" dpinfoimg.display_info_id," +
		" finfo.id AS file_id," +
		" finfo.file_name," +
		" finfo.save_file_name," +
		" finfo.content_type," +
		" finfo.delete_flag," +
		" finfo.create_date," +
		" finfo.modify_date" +
		" FROM" +
		" display_info_image dpinfoimg," +
		" file_info finfo" +
		" WHERE" +
		" dpinfoimg.display_info_id = :displayInfoId AND" +
		" dpinfoimg.file_id = finfo.id;";
}
