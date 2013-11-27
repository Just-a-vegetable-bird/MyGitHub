package com.hncu.taoshu.async;

public class ExecuteThread
{
	private AsyncTask mAsyncTask;
	private Thread mThread;
	public ExecuteThread(AsyncTask asyncTask)
	{
		mAsyncTask = asyncTask;
	}
    public void execute()
    {
    	mThread = new Thread(mAsyncTask);
    	mThread.start();
    }
    public Thread getThread()
    {
    	return mThread;
    }
}
