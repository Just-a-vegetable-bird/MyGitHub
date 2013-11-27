package com.hncu.taoshu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;

import com.hncu.taoshu.ui.SlideMenu.onChangeViewListener;
import com.hncu.taoshu.ui.base.FlipperLayout;
import com.hncu.taoshu.ui.base.FlipperLayout.OnOpenListener;
import com.hncu.taoshu.utils.View_Util;

public class MainActivity extends Activity implements OnOpenListener{
	private FlipperLayout mRoot;
	
	private SlideMenu mSlideMenu;
	private BookRoom mRoom;
	private BorrowRoom mBorrowRoom;
	private Information mInformation;
	private Scan mScan;
	private Nearby mNearby;
	private Message mMessage;
	private Search mSearch;
	private Friends mFriends;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mRoot = new FlipperLayout(MainActivity.this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mRoot.setLayoutParams(params);
		mSlideMenu = new SlideMenu(this);
		mRoom = new BookRoom(this, this);
		mBorrowRoom = new BorrowRoom(this,this);
		mInformation = new Information();
		mScan = new Scan();
		mNearby = new Nearby();
		mMessage = new Message();
		mSearch = new Search();
		
		mRoot.addView(mSlideMenu.getView(), params);
		mRoot.addView(mRoom.getView(), params);
		
		setContentView(mRoot);
		setListener();
	}
	
	private void setListener() {
		mRoom.setOnOpenListener(this);
		mBorrowRoom.setOnOpenListener(this);
//		mChat.setOnOpenListener(this);
		mSlideMenu.setOnChangeViewListener(new onChangeViewListener() {

			public void onChangeView(int arg0) {
				switch (arg0) {
//				case View_Util.Information:
//					mRoot.close(mInformation.getView());
//					break;
				case View_Util.BorrowRoom:
					mRoot.close(mBorrowRoom.getView());
					break;
				case View_Util.BookRoom:
					mRoot.close(mRoom.getView());
					break;
			/*	case View_Util.Message:
					mRoot.close(mMessage.getView());
					break;
				case View_Util.NearBy:
					mRoot.close(mNearby.getView());
					break;
					
				case View_Util.Scan:
					mRoot.close(mScan.getView());
					break;
				case View_Util.Search:
					mRoot.close(mSearch.getView());
					break;
					
				case View_Util.Friends:
					mRoot.close(mFriends.getView());
					break;*/
				}
			}
		});
	}

	@Override
	public void open() {
		if (mRoot.getScreenState() == FlipperLayout.SCREEN_STATE_CLOSE) {
			mRoot.open();
		}
	}
	
}
