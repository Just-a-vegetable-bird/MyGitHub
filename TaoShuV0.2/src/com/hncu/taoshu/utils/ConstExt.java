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

	// ////////////////�����ݿ���ȭ����///////////////////////////
	
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
	
	public static String BadNet = "��ǰ���粻���ã���������²���!";
	public static String REQUESTFAIL = "��Ŷ����������ʧ��...";
	
	
///////////////////////////��������/////////////////////
	public static final String SEARCHTYPE = "search_type";
	public static final String SEARCHCONTACT = "search_contact";
	
	public static final String SHAREADDRESS = "search_address";
	
	/////////////////////�鼮���///////////////////////
	
	
	/////////////////////��֤//////////////////////////
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
	
	
	/////////////////////������ʾ��Ϣ/////////////////////
	public static String[] category = new String[]{"�ܲ���","��ҵ����","ѧ����ҵ","С˵"};
	
	static
	{

		chineseMap.put(MESSAGE_LOGIN, new String[]
		{ "������֤�û������Ժ�...", "�û���֤�ɹ�.", "�û������������" });

		chineseMap.put(MESSAGE_HOME_INFO, new String[]
		{ "���ڻ�������鷿��Ϣ�����Ժ�...", "�ɹ���������鷿��Ϣ.", "����鷿��Ϣʧ�ܣ�" });

		chineseMap.put(MESSAGE_BOOK_DETAIL, new String[]
		{ "���ڻ���鼮��Ϣ�����Ժ�...", "�ɹ�����鼮��Ϣ.", "����鼮��Ϣʧ�ܣ�" });
//
//		chineseMap.put(MESSAGE_UPDATE_STATUS, new String[]
//		{ "���ڷ���΢�������Ժ�...", "�ɹ�����΢��.", "����΢��ʧ�ܣ�" });
//
//		chineseMap.put(MESSAGE_COUNT, new String[]
//		{ "���ڻ��ת���������������Ժ�...", "�ɹ����ת����������.", "ת����������ʧ�ܣ�" });
//		chineseMap.put(MESSAGE_COMMENT, new String[]
//		{ "���ڻ��������Ϣ�����Ժ�...", "�ɹ����������Ϣ.", "���������Ϣʧ�ܣ�" });







	}
}
