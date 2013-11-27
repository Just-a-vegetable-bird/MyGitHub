package com.hncu.taoshu.ui;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.hncu.taoshu.R;
import com.hncu.taoshu.adapter.MenuAdapter;
import com.hncu.taoshu.ui.base.FlipperLayout.OnOpenListener;

public class BorrowRoom {
	private Activity mActivity;
	private View mBorrowRoom;

	private ImageView mFlip;
	private ImageView mMenu;

	private RelativeLayout mNoDisplay;
	private ProgressBar mBar;

	private PopupWindow mMenuPopupWindow;
	private View mMenuView;
	private ListView mMenuListView;

	private OnOpenListener mOnOpenListener;
	
	public BorrowRoom( Context context,
			Activity activity) {
		mActivity = activity;
		mBorrowRoom = LayoutInflater.from(context).inflate(R.layout.borrowroom,
				null);
		mMenuView = LayoutInflater.from(context).inflate(
				R.layout.menu_popupwindow, null);
		mMenuListView = (ListView) mMenuView.findViewById(R.id.menu_pop_black_list);
		
		String[] items = context.getResources().getStringArray(R.array.borrow_menu);
		
		mMenuPopupWindow = new PopupWindow(mMenuView, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);
		mMenuPopupWindow.setAnimationStyle(R.style.MenuPopupAnimation);
		
		findViewById();
		setListener();
		
		MenuAdapter adapter = new MenuAdapter(context, items);
		mMenuListView.setAdapter(adapter);

	}
	private void findViewById() {
		mFlip = (ImageView) mBorrowRoom.findViewById(R.id.borrowroom_flip);
		mMenu = (ImageView) mBorrowRoom.findViewById(R.id.borrow_title_menu);
		
	}
	
	private void setListener() {
		mFlip.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mOnOpenListener != null) {
					mOnOpenListener.open();
				}
			}
		});
		mMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mMenuPopupWindow.isShowing()){
					mMenuPopupWindow.dismiss();
				}
				else {
					mMenuPopupWindow.showAtLocation(mBorrowRoom, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				}
			}
		});
		mMenuListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					
					break;
				case 1:
					
					break;
				case 2:
					mMenuPopupWindow.dismiss();
					break;
				default:
					break;
				}

			}
		});
		
	}
	
	public void setOnOpenListener(OnOpenListener onOpenListener) {
		mOnOpenListener = onOpenListener;
	}
	public View getView() {
//		mDisplay.setSelection(0);
		return mBorrowRoom;
	}
}


