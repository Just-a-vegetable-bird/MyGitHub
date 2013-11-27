package com.hncu.taoshu.async;

import java.util.ArrayList;
import java.util.List;

import com.hncu.taoshu.bean.Book;
import com.hncu.taoshu.bean.User;
import com.hncu.taoshu.http.HttpClient;
import com.hncu.taoshu.http.PostParameter;
import com.hncu.taoshu.utils.Const;
import com.hncu.taoshu.utils.ConstExt;

public class TaoShu implements Const{
	static HttpClient httpClient;
	static {
		 httpClient = new HttpClient();
	}
	public static  User login(String userName,String password) throws Exception{
		PostParameter postParams = new PostParameter("username", userName);
		PostParameter passParams = new PostParameter("password", password);
		List<PostParameter> postParameters = new ArrayList<PostParameter>();
		postParameters.add(postParams);
		postParameters.add(passParams);
//		System.out.println(userName);
		return User.constructUser(httpClient.post(ConstExt.LOGIN, postParameters.toArray(new PostParameter[postParameters.size()]),true));
	}
	public static Book getBook(String isbn) throws Exception{
		return Book.constructBook(httpClient.get(ConstExt.GETBOOKINFO+isbn));
	}
}
