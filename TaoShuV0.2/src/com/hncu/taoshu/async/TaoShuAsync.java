package com.hncu.taoshu.async;


public class TaoShuAsync extends TaoShu{

	public static void loginAsync(TaoShuListener listener,final String userName,final String password){
		ExecuteThread executeThread = new ExecuteThread(new AsyncTask(listener, OPERATION_TYPE_LOGIN, MESSAGE_LOGIN,
				LANGUAGE_ZH_CN) {
			@Override
			public Object invoke() throws Exception {
				// TODO Auto-generated method stub
				return login(userName, password);
			}
		});
		executeThread.execute();
	}
	
	public static void getBookAsync(TaoShuListener listener,final String isbn)  {
		ExecuteThread executeThread = new ExecuteThread(new AsyncTask(listener, OPERATION_TYPE_BOOK_DETAIL, MESSAGE_BOOK_DETAIL,
				LANGUAGE_ZH_CN) {
			public Object invoke() throws Exception {
				return getBook(isbn);
			}
		});
		executeThread.execute();
	}
}
