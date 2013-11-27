package com.hncu.taoshu.async;

import com.hncu.taoshu.utils.TextUtils;



public abstract class AsyncTask implements Runnable {

	private TaoShuListener mTaoShuListener;
	private int mType; // 操作的类型，如用户登录、获得Status等
	private int mMsgId;
	private int mLanguage; // 当前的语言
	private boolean mShowWaitMsg;

	public AsyncTask(TaoShuListener mTaoShuListener, int mType, int mMsgId,
			int mLanguage) {

		this(mTaoShuListener, mType, mMsgId, mLanguage, true);
	}

	public AsyncTask(TaoShuListener mTaoShuListener, int mType, int mMsgId,
			int mLanguage, boolean mShowWaitMsg) {
		super();
		this.mTaoShuListener = mTaoShuListener;
		this.mType = mType;
		this.mMsgId = mMsgId;
		this.mLanguage = mLanguage;
		this.mShowWaitMsg = mShowWaitMsg;
	}

	public abstract Object invoke() throws Exception;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[] msgArray = TextUtils.getString(mMsgId, mLanguage);
		try {
			// mType + 1表示操作成功的ID
			if (mTaoShuListener != null && mShowWaitMsg)
				if (mTaoShuListener.onWait(msgArray[0], mType) == false)
					return;
			Object obj = invoke();

			if (mTaoShuListener != null)
				mTaoShuListener.onEnd(msgArray[1], obj, mType + 1);
		} catch (Exception e) {
			// mType + 2表示操作失败的ID
			if (mTaoShuListener != null)
			//	mTaoShuListener.onException(msgArray[2], mType + 2);
			mTaoShuListener.onException(e.getMessage(), mType + 2);
		}
	}

}
