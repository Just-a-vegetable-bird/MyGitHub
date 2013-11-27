package com.hncu.taoshu.async;

import com.hncu.taoshu.bean.Book;
import com.hncu.taoshu.utils.TextUtils;



public abstract class AsyncTask implements Runnable {

	private TaoShuListener mTaoShuListener;
	private int mType; // ���������ͣ����û���¼�����Status��
	private int mMsgId;
	private int mLanguage; // ��ǰ������


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
			System.out.println("��������");
			Book book = (Book) obj;
			if(book!=null){
				System.out.println("auther-->"+book.getAUTHER());
			}
			if (mTaoShuListener != null){
				System.out.println("��Ҫ������");
				mTaoShuListener.onEnd(msgArray[1], obj, mType + 1);
				System.out.println("��ô���°�");
			}
				
		} catch (Exception e) {
			// mType + 2��ʾ����ʧ�ܵ�ID
			if (mTaoShuListener != null)
			//	mTaoShuListener.onException(msgArray[2], mType + 2);
			mTaoShuListener.onException(e.getMessage(), mType + 2);
		}
	}

}
