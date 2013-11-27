package com.hncu.taoshu.async;

import com.hncu.taoshu.utils.TextUtils;



public abstract class AsyncTask implements Runnable {

	private TaoShuListener mTaoShuListener;
	private int mType; // ���������ͣ����û���¼�����Status��
	private int mMsgId;
	private int mLanguage; // ��ǰ������
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
			// mType + 1��ʾ�����ɹ���ID
			if (mTaoShuListener != null && mShowWaitMsg)
				if (mTaoShuListener.onWait(msgArray[0], mType) == false)
					return;
			Object obj = invoke();

			if (mTaoShuListener != null)
				mTaoShuListener.onEnd(msgArray[1], obj, mType + 1);
		} catch (Exception e) {
			// mType + 2��ʾ����ʧ�ܵ�ID
			if (mTaoShuListener != null)
			//	mTaoShuListener.onException(msgArray[2], mType + 2);
			mTaoShuListener.onException(e.getMessage(), mType + 2);
		}
	}

}
