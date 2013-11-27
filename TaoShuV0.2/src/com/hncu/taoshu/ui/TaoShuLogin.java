package com.hncu.taoshu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hncu.taoshu.R;
import com.hncu.taoshu.async.TaoShuAsync;
import com.hncu.taoshu.async.TaoShuListener;
import com.hncu.taoshu.bean.User;
import com.hncu.taoshu.ui.base.BaseActivity;
import com.hncu.taoshu.utils.MyUtils;

public class TaoShuLogin extends BaseActivity{

	private EditText edtUserName;
	private EditText edtPassword;
	private Button btnLogin;
	private TextView txtRigester,txtForgetPasword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taoshu_login);
		findViewById();
		setListenser();
	}
	
	@Override
	public void findViewById() {
		// TODO Auto-generated method stub
		edtUserName = (EditText) findViewById(R.id.login_username);
		edtPassword = (EditText)findViewById(R.id.login_password);
		btnLogin = (Button) findViewById(R.id.taoshu_login);
		txtForgetPasword = (TextView) findViewById(R.id.forget_password);
		txtRigester = (TextView) findViewById(R.id.register);
	}

	@Override
	public void setListenser() {
		// TODO Auto-generated method stub
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!MyUtils.checkNet(TaoShuLogin.this)){
					Toast.makeText(TaoShuLogin.this, getResources().getString(R.string.no_connect), Toast.LENGTH_SHORT).show();
				}
				else {
					checkBeforeLogin();
				}

			}
		});
	}
	
	
	private void  checkBeforeLogin(){
		String userName = edtUserName.getText().toString();
		String password = edtPassword.getText().toString();
		if(userName.equals("")){
			Toast.makeText(this, getResources().getString(R.string.no_username),Toast.LENGTH_SHORT).show();
		}
		else if(password.equals("")){
			Toast.makeText(this, getResources().getString(R.string.no_password),Toast.LENGTH_SHORT).show();
		}
		else {
			login(userName, password);
		}
	}
	private void  login(String userName,String password){

		try {
			TaoShuAsync.loginAsync(new TaoShuListener() {
				
				@Override
				public void onException(String msg, int type) {
					// TODO Auto-generated method stub
					System.out.println("¥ÌŒÛ–≈œ¢£∫"+msg);
				}
				@Override
				public void onEnd(String msg, Object obj, int type) {
					// TODO Auto-generated method stub
					User user = (User) obj;
				}
			}, userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(user!=null && user.getUserId()!=0){
			Intent intent = new Intent(TaoShuLogin.this, MainActivity.class);
//			Bundle bundle = new Bundle();
//			bundle.putParcelable("User", (Parcelable) user);
//			intent.putExtras(bundle);
			startActivity(intent);
//		}
//		else {
//			Toast.makeText(this, getResources().getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
//		}

		
	}

}
