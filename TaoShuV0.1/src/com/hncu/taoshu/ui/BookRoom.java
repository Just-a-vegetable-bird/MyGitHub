package com.hncu.taoshu.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hncu.taoshu.R;
import com.hncu.taoshu.adapter.GroupAdapter;
import com.hncu.taoshu.bean.CategoryBook;
import com.hncu.taoshu.ui.base.FlipperLayout.OnOpenListener;

public class BookRoom {
	private Activity mActivity;
	private Context mcontext;
	private View mBookRoom;
	private ImageView mFlip;
	private ImageView mMenu;
	private ImageView mTab;
	private ViewPager mPager;
	private TextView mTap1,mTap2;
	private int offest;
	private ListView listViewGroup;
	private ListView listViewAll;
	
	private int currIndex = 0;// 当前页卡编号
	ArrayList<View> views ;
	
	private RelativeLayout mNoDisplay;
	private ImageView mNoDisplayImg;
	private ProgressBar mProgressBar;

	private PopupWindow mModePopupWindow;

	private OnOpenListener mOnOpenListener;
	
	public BookRoom( Context context,
			Activity activity) {
		mActivity = activity;
		mcontext = context;
		mBookRoom = LayoutInflater.from(context).inflate(R.layout.bookroom,
				null);
        Display currDisplay = mActivity.getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
        int displayWidth = currDisplay.getWidth();
        
        offest = displayWidth/2; //设置水平动画平移大小
        
        LayoutInflater mInflater = LayoutInflater.from(context);
       
        listViewAll = (ListView) mInflater.inflate(R.layout.bookroom_list_all, null);
        listViewGroup= (ListView) mInflater.inflate(R.layout.bookroom_list_group, null);
        
//        listViewGroup = (ListView) mBookRoom.findViewById(R.id.bookroom_list_group1);
        views = new ArrayList<View>();
        views.add(listViewGroup);
        views.add(listViewAll);
        
		findViewById();
		bindEvent();

	}
	private void findViewById() {
		mFlip = (ImageView) mBookRoom.findViewById(R.id.bookroom_flip);
		mMenu = (ImageView)mBookRoom.findViewById(R.id.bookroom_title_menu);
		mTab = (ImageView) mBookRoom.findViewById(R.id.bookroom_tap);
		mTap1 = (TextView) mBookRoom.findViewById(R.id.bookroom_tap1);
		mTap2 = (TextView)mBookRoom.findViewById(R.id.bookroom_tap2);
		mPager = (ViewPager) mBookRoom.findViewById(R.id.bookroom_tabpager);
		mProgressBar = (ProgressBar) mBookRoom.findViewById(R.id.bookroom_progressbar);
		mNoDisplay =  (RelativeLayout) mBookRoom.findViewById(R.id.bookroom_nodisplay);
		mNoDisplayImg = (ImageView) mNoDisplay.findViewById(R.id.bookroom_nodisplay_img);
	}
	
	private void bindEvent() {
		
		mFlip.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mOnOpenListener != null) {
					mOnOpenListener.open();
				}
			}
		});
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mTap1.setOnClickListener(new MyOnClickListener(0));
		mTap2.setOnClickListener(new MyOnClickListener(1));
		
	    //填充ViewPager的数据适配器
	    PagerAdapter mPagerAdapter = new PagerAdapter() {
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			public int getCount() {
				return views.size();
			}
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		mPager.setAdapter(mPagerAdapter);

	refresh();
	
}
	public void refresh(){
		List<CategoryBook> categoryBooks = new ArrayList<CategoryBook>();
		CategoryBook categoryBook1 = new CategoryBook(1, "工业技术", "Android应用程序开发、Oracle数据库、JAVA程序设计、软件工程", "http://img3.douban.com/spic/s11362613.jpg", 100);
		CategoryBook categoryBook2 = new CategoryBook(2, "小说", "英国史、二月河文集、典当全集、我想和这个世界谈谈", "http://img35.ddimg.cn/68/24/23301995-1_l.jpg", 150);
		CategoryBook categoryBook3 = new CategoryBook(3, "工业技术", "Android应用程序开发、Oracle数据库、JAVA程序设计、软件工程", "http://img3.douban.com/spic/s11362613.jpg", 120);

		CategoryBook categoryBook4 = new CategoryBook(4, "小说", "英国史、二月河文集、典当全集、我想和这个世界谈谈", "http://img35.ddimg.cn/68/24/23301995-1_l.jpg", 150);
		categoryBooks.add(categoryBook1);
		categoryBooks.add(categoryBook2);
		categoryBooks.add(categoryBook3);
		categoryBooks.add(categoryBook4);
		GroupAdapter adapter = new GroupAdapter(mcontext, categoryBooks);

		listViewGroup.setAdapter(adapter);
		
		System.out.println("第一可见view-->"+listViewGroup.getFirstVisiblePosition()+ "有"+adapter.getCount());
		
	}
    /**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};
	
	class MyOnPageChangeListener implements OnPageChangeListener{
		public void onPageScrollStateChanged(int arg0) {
		}
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(offest, 0, 0, 0);
				} 
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(0, offest, 0, 0);
				} 
				break;
		}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(150);
			mTab.startAnimation(animation);
	}
}
	
	public void setOnOpenListener(OnOpenListener onOpenListener) {
		mOnOpenListener = onOpenListener;
	}
	public View getView() {
//		mDisplay.setSelection(0);
		return mBookRoom;
	}
}
