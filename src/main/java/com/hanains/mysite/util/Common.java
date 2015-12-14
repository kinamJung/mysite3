package com.hanains.mysite.util;

public class Common {

	public static final String DB_USER = "webdb";
	public static final String DB_PASSWORD = "webdb";
	public static final String ORCLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String CONNECT_DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//한 페이지에 보이 질 게시글 수
	public static final int SHOW_BOARD_WRITHING_COUNT_ON_PAGE = 10;
	
	
	public static final String FILE_SAVE_PATH = "upload";
	public static final int UPLOAD_FILE_SIZE_LIMIT = 5 * 1024 * 1024;
	public static final String ENC_TYPE = "UTF-8";
	
}
