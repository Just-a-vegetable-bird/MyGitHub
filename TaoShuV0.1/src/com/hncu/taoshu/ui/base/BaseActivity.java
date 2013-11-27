package com.hncu.taoshu.ui.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}
	public abstract void findViewById();
	public abstract void setListenser();
}
