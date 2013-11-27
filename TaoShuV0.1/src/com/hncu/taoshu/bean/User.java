package com.hncu.taoshu.bean;

import android.graphics.Point;

import com.hncu.taoshu.http.Response;

public class User {
	private int userId;
	private String roomName,userName,userAddress,sinaToken,doubanToken,qqToken,userIcon;
	private Point addressPoint ;
	private boolean isDfault = false;
	
	 public static String USER_ID = "userId";
	 public static String USER_NAME = "userName";
	 public static String SINA_TOKEN = "sinaToken";
	 public static String DOUBAN_TOKEN = "doubanToken";
	 public static String QQ_TOKEN = "qqToken";
	 public static String USER_ICON = "userIcon";
	 public static String IS_DEFAULT = "isDefault";
	 public static String ADDRESS_POINT = "addressPoint";
	 
	public User(){
		
	}
	
	
	public User(int userId, String roomName, String userName,
			String userAddress, String sinaToken, String doubanToken,
			String qqToken, String userIcon, Point addressPoint,
			boolean isDfault) {
		super();
		this.userId = userId;
		this.roomName = roomName;
		this.userName = userName;
		this.userAddress = userAddress;
		this.sinaToken = sinaToken;
		this.doubanToken = doubanToken;
		this.qqToken = qqToken;
		this.userIcon = userIcon;
		this.addressPoint = addressPoint;
		this.isDfault = isDfault;
	}


	
	
	public User(int userId, String userName, String sinaToken,
			String doubanToken, String qqToken, boolean isDfault) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.sinaToken = sinaToken;
		this.doubanToken = doubanToken;
		this.qqToken = qqToken;
		this.isDfault = isDfault;
	}

	
	public static User constructUser(Response response){
		System.out.println("aaaaa");
		System.out.println(response.asString());
		
		return new User();
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getSinaToken() {
		return sinaToken;
	}
	public void setSinaToken(String sinaToken) {
		this.sinaToken = sinaToken;
	}
	public String getDoubanToken() {
		return doubanToken;
	}
	public void setDoubanToken(String doubanToken) {
		this.doubanToken = doubanToken;
	}
	public String getQqToken() {
		return qqToken;
	}
	public void setQqToken(String qqToken) {
		this.qqToken = qqToken;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public Point getAddressPoint() {
		return addressPoint;
	}
	public void setAddressPoint(Point addressPoint) {
		this.addressPoint = addressPoint;
	}
	public boolean isDfault() {
		return isDfault;
	}
	public void setDfault(boolean isDfault) {
		this.isDfault = isDfault;
	}

	
	
}
