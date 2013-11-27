package com.hncu.taoshu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hncu.taoshu.db.DBInfo.DB;



/**
 * 数据库操作帮助类
 * @author <a href="http://bbs.droidstouch.com">Touch Android</a>
 *
 */
public class DBHelper extends SQLiteOpenHelper
{

	

	
	public DBHelper(Context context) 
	{
		///第三个参数CursorFactory指定在执行查询时获得�?��游标实例的工厂类,设置为null,代表使用系统默认的工厂类

		super(context, DBInfo.DB.DB_NAME, null, DB.VERSION);
	}

	
	//用于初次使用软件时生成数据库�?
	//当调用SQLiteOpenHelper的getWritableDatabase()或�?getReadableDatabase()方法获取用于操作数据库的SQLiteDatabase实例的时候，
	//如果数据库不存在，Android系统会自动生成一个数据库，接着调用onCreate()方法
	//onCreate()方法在初次生成数据库时才会被调用
	//在onCreate()方法里可以生成数据库表结构及添加�?��应用使用到的初始化数�?
	public void onCreate(SQLiteDatabase db)
	{
		
		//String create_cus="CREATE TABLE IF NOT EXISTS Customer (_id integer primary key autoincrement, name varchar(20), age INTEGER)";
		db.execSQL(DBInfo.Table.USER_INFO_CREATE);
	}

	//onUpgrade()方法在数据库的版本发生变化时会被调用，一般在软件升级时才�?��变版本号
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		
		
		//db.execSQL("DROP TABLE IF EXISTS UserInfo");
   // onCreate(db);

    //上面两句在数据库版本每次发生变化时都会把用户手机上的数据库表删除，然后再重新创建�?
    	//�?��在实际项目中是不能这样做的，正确的做法是在更新数据库表结构时，还要�?虑用户存放于数据库中的数据不会丢�?
    
	}

}
