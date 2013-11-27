package com.hncu.taoshu.utils;


public interface Const {

	// ////////////////////////////语言//////////////////////////////////////
	public int LANGUAGE = 3000;
	public int LANGUAGE_ZH_CN = LANGUAGE + 1;
	public int LANGUAGE_EN_US = LANGUAGE + 2;
	
	
	// ////////////////////////操作类型///////////////////////////////////////
	public int OPERATION_TYPE = 4000;
	public int OPERATION_TYPE_LOGIN = OPERATION_TYPE + 1;
	public int OPERATION_TYPE_LOGIN_SUCCESS = OPERATION_TYPE + 2;
	public int OPERATION_TYPE_LOGIN_EXCEPTION = OPERATION_TYPE + 3;
	
	public int OPERATION_TYPE_HOMEINFO = OPERATION_TYPE + 4;
	public int OPERATION_TYPE_HOMEINFO_SUCCESS = OPERATION_TYPE + 5;
	public int OPERATION_TYPE_HOMEINFO_EXCEPTION = OPERATION_TYPE + 6;
	
	public int OPERATION_TYPE_BOOKS_DETAIL = OPERATION_TYPE + 7;
	public int OPERATION_TYPE_BOOKS_DETAIL_SUCCESS = OPERATION_TYPE + 8;
	public int OPERATION_TYPE_BOOKS_DETAIL_EXCEPTION = OPERATION_TYPE + 9;
	
	
	public int OPERATION_TYPE_BOOK_DETAIL = OPERATION_TYPE +10;
	public int OPERATION_TYPE_BOOK_DETAIL_SUCCESS = OPERATION_TYPE + 11;
	public int OPERATION_TYPE_BOOK_DETAIL_EXCEPTION = OPERATION_TYPE + 12;
	
	
	//////////////////////////搜索类别///////////////////////////////////////
	public int SEARCH_TYPE = 4500;
	public int SEARCH_TYPE_SEARCHROOM = SEARCH_TYPE+1;
	public int SEARCH_TYPE_SEARCHALL = SEARCH_TYPE+2;
	
	
	// ///////////////////////提示信息////////////////////////////////////////
	public int MESSAGE = 5000;
	public int MESSAGE_LOGIN = MESSAGE + 1;
	public int MESSAGE_HOME_INFO = MESSAGE + 2;
	public int MESSAGE_BOOKS_DETAIL = MESSAGE + 3;
	public int MESSAGE_BOOK_DETAIL = MESSAGE + 4;
	
	

	
	// ///////////////////////网络相关////////////////////////////////////////
	
	public final static int NOCONNECTED = 2;
	public final static int NORMAL = 1;
}
