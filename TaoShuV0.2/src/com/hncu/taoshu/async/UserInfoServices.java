package com.hncu.taoshu.async;

import com.hncu.taoshu.db.DBHelper;



/**
 * 用户对象数据库操作层
 * @author <a href="http://bbs.droidstouch.com">Touch Android</a>
 *
 */
public class UserInfoServices
{

	private DBHelper dbHelper;
	
/*	private String[] columns ={UserInfo.ID,UserInfo.IS_DEFAULT,UserInfo.TOKEN,UserInfo.TOKEN_SECRET,
															UserInfo.USER_ICON,UserInfo.USER_ID,UserInfo.USER_NAME};
	
	public UserInfoServices(Context context)
	{
		dbHelper = new DBHelper(context);
	}
	
	*//**
	 * 添加用户信息
	 * @param user
	 *//*
	public void insertUserInfo(User user)
	{
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues(5);
		
		values.put(User.USER_ID,user.getUserId());
		values.put(User.USER_NAME, user.getUserName());
		values.put(User.TOKEN,user.getSinaToken());
//		values.put(User.TOKEN_SECRET, user.getTokenSecret());
		values.put(User.IS_DEFAULT,user.isDfault());
		
		db.insert(DBInfo.DB.DB_NAME, null, values);
		
		db.close();
	}
	
	
	
	*//**
	 * 根据用户ID获取用户对象
	 * @param userId
	 * @return
	 *//*
	public User getUserInfoByUserId(String userId)
	{
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		User userInfo =null;
		
		Cursor cursor =db.query(DBInfo.DB.DB_NAME, new String[]{User.IS_DEFAULT,User.TOKEN,
				User.TOKEN_SECRET,User.USER_ID,User.USER_NAME,User.USER_ICON},
				User.USER_ID +"=?", new String[]{userId}, null, null, null);
		
		if(null != cursor)
		{
			
			if(cursor.getCount() >0)
			{
				
				cursor.moveToFirst();
				userInfo = new User();
				
				Long id =cursor.getLong(cursor.getColumnIndex(UserInfo.ID));
				String uId = cursor.getString(cursor.getColumnIndex(UserInfo.USER_ID));
				String userName = cursor.getString(cursor.getColumnIndex(UserInfo.USER_NAME));
				String token = cursor.getString(cursor.getColumnIndex(UserInfo.TOKEN));
				String tokenSecret = cursor.getString(cursor.getColumnIndex(UserInfo.TOKEN_SECRET));
				String isDefault = cursor.getString(cursor.getColumnIndex(UserInfo.IS_DEFAULT));
				byte[] byteIcon = cursor.getBlob(cursor.getColumnIndex(UserInfo.USER_ICON));
				
				userInfo.setId(id);
				userInfo.setUserId(uId);
				userInfo.setIsDefault(isDefault);
				userInfo.setToken(token);
				userInfo.setTokenSecret(tokenSecret);
				userInfo.setToken(token);
				userInfo.setUserName(userName);
				

				if(null !=byteIcon)
				{
					
					ByteArrayInputStream is = new ByteArrayInputStream(byteIcon);
					Drawable userIcon=Drawable.createFromStream(is, "image");
					
					userInfo.setUserIcon(userIcon);
				}
			}
		}
		cursor.close();
		db.close();
		return userInfo;
	}
	
	
	
	*//**
	 * 查询�?��的用户信�?
	 * @return
	 *//*
	public List<UserInfo> findAllUsers()
	{
		
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		
		List<UserInfo> users=null;
		
		Cursor cursor = db.query(UserInfo.TB_NAME, columns, null, null, null, null, null);
		
		if(null != cursor &&  cursor.getCount() >0)
		{
			
			users = new ArrayList<UserInfo>(cursor.getCount());
			
			UserInfo userInfo = null;
			while(cursor.moveToNext())
			{
				
				userInfo = new UserInfo();
				
				Long id =cursor.getLong(cursor.getColumnIndex(UserInfo.ID));
				String uId = cursor.getString(cursor.getColumnIndex(UserInfo.USER_ID));
				String userName = cursor.getString(cursor.getColumnIndex(UserInfo.USER_NAME));
				String token = cursor.getString(cursor.getColumnIndex(UserInfo.TOKEN));
				String tokenSecret = cursor.getString(cursor.getColumnIndex(UserInfo.TOKEN_SECRET));
				String isDefault = cursor.getString(cursor.getColumnIndex(UserInfo.IS_DEFAULT));
				byte[] byteIcon = cursor.getBlob(cursor.getColumnIndex(UserInfo.USER_ICON));
				
				userInfo.setId(id);
				userInfo.setUserId(uId);
				userInfo.setIsDefault(isDefault);
				userInfo.setToken(token);
				userInfo.setTokenSecret(tokenSecret);
				userInfo.setToken(token);
				userInfo.setUserName(userName);
				
				if(null !=byteIcon)
				{
					
					ByteArrayInputStream is = new ByteArrayInputStream(byteIcon);
					Drawable userIcon=Drawable.createFromStream(is, "image");
					
					userInfo.setUserIcon(userIcon);
				}
				
				users.add(userInfo);
				
			}
			
		}
		
		cursor.close();
		db.close();
		
		return users;
		
	}
	
	
	
	
	
	
	*//**
	 * 更新用户Token信息
	 * @param user
	 *//*
	public void  updateUserInfo(UserInfo user)
	{
		
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(UserInfo.TOKEN, user.getToken());
		values.put(UserInfo.TOKEN_SECRET, user.getTokenSecret());
		
		db.update(UserInfo.TB_NAME, values, UserInfo.USER_ID+"=?", new String[]{user.getUserId()});
		
		db.close();
	}
	
	*//**
	 * 更新用户头像和用户昵�?
	 * @param userId
	 * @param userName
	 *//*
	public void updateUserInfo(String userId,String userName,Bitmap userIcon)
	{
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues(2);
		values.put(UserInfo.USER_NAME, userName);
		
		ByteArrayOutputStream os= new ByteArrayOutputStream();
		userIcon.compress(CompressFormat.PNG, 100, os);
		
		values.put(UserInfo.USER_ICON, os.toByteArray());
		
		db.update(UserInfo.TB_NAME, values, UserInfo.USER_ID+"=?", new String[]{userId});
		db.close();
	}
	*/
	
	
	
}
