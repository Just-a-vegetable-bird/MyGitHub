package com.hncu.taoshu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hncu.taoshu.bean.User;


public class SharedPreferencesUtil
{
	private static final String LOGIN_USER="login_user";
	/**
	 * 保存用户登录信息
	 * @param context
	 * @param user
	 */
	public static void saveLoginUser(Context context,User user)
	{
		
		
		SharedPreferences sp = context.getSharedPreferences(LOGIN_USER, Context.MODE_PRIVATE);
		 Editor editor =	sp.edit();
		 editor.putInt(User.USER_ID, user.getUserId());
		 editor.putString(User.USER_NAME, user.getUserName());
		 editor.putString(User.QQ_TOKEN, user.getQqToken());
		 editor.putString(User.SINA_TOKEN, user.getSinaToken());
		 editor.putString(User.DOUBAN_TOKEN, user.getDoubanToken());
		 editor.putBoolean(User.IS_DEFAULT, true );
		 
		 editor.commit();
		
	}
	/**
	 * 获取当前用户信息
	 * @param context
	 * @return
	 */
	public static User getLoginUser(Context context)
	{
		
		SharedPreferences sp = context.getSharedPreferences(LOGIN_USER, Context.MODE_PRIVATE);
		int userId=sp.getInt(User.USER_ID, 0 );
		String userName=sp.getString(User.USER_NAME, "");
		String qqToken=sp.getString(User.QQ_TOKEN, "");
		String sinaToken=sp.getString(User.SINA_TOKEN, "");
		String doubanToken=sp.getString(User.DOUBAN_TOKEN, "");
		boolean isDefault=sp.getBoolean(User.IS_DEFAULT, true);
		
		if(userId==0)
			return null;
		return new User(userId, userName, sinaToken, doubanToken, qqToken, isDefault);
	}
	
	
}
