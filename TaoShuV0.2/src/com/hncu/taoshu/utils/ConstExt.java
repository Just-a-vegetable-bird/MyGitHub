package com.hncu.taoshu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hncu.taoshu.bean.Book;



public abstract class ConstExt implements Const{

	public static Map<Integer, String[]> chineseMap = new HashMap<Integer, String[]>();
	public static Map<Integer, String[]> englishMap = new HashMap<Integer, String[]>();
	public static List<Book> bookRoomAddDdata = new ArrayList<Book>();
	public static List<Book> borrowRoomAddDdata = new ArrayList<Book>();

	// ////////////////与数据库相拳常用///////////////////////////
	
	public final static String USERID = "user_id";
	public final static String USERNAME = "user_name";
	public final static String USERHEAD = "user_head";
	public final static String ROOMNAME = "room_name";
	public final static String USERADDRESS = "user_address";
	public final static String SINATOKEN = "sina_token";
	public final static String QQTOKEN = "qq_token";
	public final static String DOUBANTOKEN = "douban_token";
	public final static String LATITUDE = "latitude";
	public final static String LONGITUDE = "longitude";
	public final static String ROOMID = "room_id";
	public final static String ISBNID = "isbn_id";
	public final static String BOOKNAME = "book_name";
	public final static String BOOKCATEGORY = "book_category";
	public final static String BOOKPUBLISH = "book_publish";
	public final static String BOOKAUTHOR = "book_author";
	public final static String BOOKDETAIL = "book_detail";
	
	
	
	// ////////////////URL///////////////////////////
	public static String BaseURL = "http://192.168.137.1:8080/phonelibV2";
	public static String LOGIN = BaseURL+"/auth/phoneSignIn";
	public static String GETBOOKS = BaseURL+"/getBooksById?room_id=";
	public static String GETBOOKINFO = "http://api.douban.com/v2/book/isbn/";
	public static String CHECKBOOKEXIST = BaseURL+"";
	public static String GETCATEGORYBOOKS = BaseURL+"/getAllCategoryBooks?room_id=";
	public static String GETBOOKSDETAIL = BaseURL+"/getBooks";
	public static String CHECKUSEREXITS = BaseURL+"/exits?";
	public static String GETROOMS = BaseURL+"/getRooms?user_address=";
	public static String GETUSER = BaseURL+"/getUser?room_id=";
	
	public static String BadNet = "当前网络不可用，请检查后重新操作!";
	public static String REQUESTFAIL = "啊哦！请求数据失败...";
	
	
///////////////////////////搜索类型/////////////////////
	public static final String SEARCHTYPE = "search_type";
	public static final String SEARCHCONTACT = "search_contact";
	
	public static final String SHAREADDRESS = "search_address";
	
	/////////////////////书籍类别///////////////////////
	
	
	/////////////////////认证//////////////////////////
	public static final String SINA_APP_KEY="3774443550";
	public static final String SINA_REDIRECT_URL = "http://www.sina.com";
	public static final String SINA_SCOPE = "email,direct_messages_read,direct_messages_write," +
			"friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
				"follow_app_official_microblog";
	public static final String SINA_CONSUMER_SECRET = "0339ecb7f9396be9d5d285225e89ce94";
	
	
	public static String SINA_ACCESS_TOKEN = "https://api.weibo.com/oauth2/access_token?client_id="
			+ SINA_APP_KEY
			+ "&client_secret="
			+ SINA_CONSUMER_SECRET
			+ "&grant_type=authorization_code&redirect_uri="+SINA_REDIRECT_URL+"&code=";
	
	
	/////////////////////所有提示信息/////////////////////
	public static String[] category = new String[]{"总藏书","工业技术","学科作业","小说"};
	
	static
	{

		chineseMap.put(MESSAGE_LOGIN, new String[]
		{ "正在验证用户，请稍后...", "用户验证成功.", "用户名或密码错误，" });

		chineseMap.put(MESSAGE_HOME_INFO, new String[]
		{ "正在获得您的书房信息，请稍后...", "成功获得您的书房信息.", "获得书房信息失败，" });

		chineseMap.put(MESSAGE_BOOK_DETAIL, new String[]
		{ "正在获得书籍信息，请稍后...", "成功获得书籍信息.", "获得书籍信息失败，" });
//
//		chineseMap.put(MESSAGE_UPDATE_STATUS, new String[]
//		{ "正在发布微博，请稍后...", "成功发布微博.", "发布微博失败，" });
//
//		chineseMap.put(MESSAGE_COUNT, new String[]
//		{ "正在获得转发和评论数，请稍后...", "成功获得转发和评论数.", "转发和评论数失败，" });
//		chineseMap.put(MESSAGE_COMMENT, new String[]
//		{ "正在获得评论信息，请稍后...", "成功获得评论信息.", "获得评论信息失败，" });







	}
}
