package com.hncu.taoshu.async;

public interface TaoShuListener {
	public boolean onWait(String msg, int type);

	public void onEnd(String msg, Object obj, int type);

	public void onException(String msg, int type);
}
