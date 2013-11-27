package com.hncu.taoshu.adapter;


import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hncu.taoshu.R;
import com.hncu.taoshu.bean.Book;
import com.hncu.taoshu.ui.BookAdd;
import com.hncu.taoshu.utils.ConstExt;
import com.hncu.taoshu.utils.MyUtils;

public class BookAddAdapter extends BaseAdapter{

	private List<Book> data ;
	private LayoutInflater mInflater;
	private Context mContext;
	
	public BookAddAdapter(int from,
			Context context) {
		super();
		mContext = context;
		this.data = (from==BookAdd.FROMBOOKROOM?ConstExt.bookRoomAddDdata:ConstExt.borrowRoomAddDdata);
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data == null ? null : data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return data == null ? 0 : position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.book_add_item, null);
			holder.bookImg = (ImageView) convertView.findViewById(R.id.book_item_img);
			holder.bookName = (TextView) convertView.findViewById(R.id.book_item_bookname);
			holder.mark = (RelativeLayout) convertView.findViewById(R.id.book_item_mark);
			holder.bookChoose = (ImageView) convertView.findViewById(R.id.book_item_choose);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.bookName.setText(data.get(position).getTITLE());
		holder.bookImg.setImageBitmap(MyUtils.getImageBitmap(mContext, data.get(position).getMedium_IMAGE()));
		return convertView;
	}
	
	class ViewHolder{
		ImageView bookImg;
		ImageView bookChoose;
		TextView bookName;
		RelativeLayout mark;
	}
	

}
