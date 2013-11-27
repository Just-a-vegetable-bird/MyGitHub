package com.hncu.taoshu.async;

import com.hncu.taoshu.bean.Book;
import com.hncu.taoshu.utils.TextUtils;



public abstract class AsyncTask implements Runnable {

	private TaoShuListener mTaoShuListener;
	private int mType; // 操作的类型，如用户登录、获得Status等
	private int mMsgId;
	private int mLanguage; // 当前的语言


	public AsyncTask(TaoShuListener mTaoShuListener, int mType, int mMsgId,
			int mLanguage) {
		super();
		this.mTaoShuListener = mTaoShuListener;
		this.mType = mType;
		this.mMsgId = mMsgId;
		this.mLanguage = mLanguage;

	}

	public abstract Object invoke() throws Exception;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[] msgArray = TextUtils.getString(mMsgId, mLanguage);
		try {
			Object obj = invoke();
			System.out.println("啦啦啦啦");
			Book book = (Book) obj;
			if(book!=null){
				System.out.println("auther-->"+book.getAUTHER());
			}
			if (mTaoShuListener != null){
				System.out.println("不要这样啊");
				mTaoShuListener.onEnd(msgArray[1], obj, mType + 1);
				System.out.println("怎么回事啊");
			}
				
		} catch (Exception e) {
			// mType + 2表示操作失败的ID
			if (mTaoShuListener != null)
			//	mTaoShuListener.onException(msgArray[2], mType + 2);
			mTaoShuListener.onException(e.getMessage(), mType + 2);
		}
	}

}
