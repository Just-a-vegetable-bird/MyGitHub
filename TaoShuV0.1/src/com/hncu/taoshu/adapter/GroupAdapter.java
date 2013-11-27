package com.hncu.taoshu.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hncu.taoshu.R;
import com.hncu.taoshu.bean.CategoryBook;
import com.hncu.taoshu.utils.MyUtils;

public class GroupAdapter extends BaseAdapter{
	private List<CategoryBook> categoryBooks;
	private LayoutInflater mInflater;
	private Context mContext;
	
	public GroupAdapter(Context context,List<CategoryBook> categoryBooks) {
		// TODO Auto-generated constructor stub
		mContext = context;
		this.categoryBooks = categoryBooks;
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return categoryBooks==null?0:categoryBooks.size();
	}
	@Override
	public Object getItem(int position) {
		return categoryBooks.get(position);
	}
	@Override
	public long getItemId(int position) {
		return categoryBooks==null?0:categoryBooks.get(position).getCategory();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder holder;
		if (convertView==null) {
			holder = new viewHolder();
			convertView = mInflater.inflate(R.layout.bookroom_group_item, null);
			System.out.println(1);
			holder.imageView = (ImageView) convertView.findViewById(R.id.bookroom_group_img);
			holder.category= (TextView) convertView.findViewById(R.id.bookroom_group_title);
			holder.detail = (TextView) convertView.findViewById(R.id.bookroom_group_desc);
			holder.count = (TextView) convertView.findViewById(R.id.bookroom_group_count);
			convertView.setTag(holder);
		}
		else {
			holder = (viewHolder) convertView.getTag();
		}
		holder.category.setText(categoryBooks.get(position).getCategoryName());
		holder.detail.setText(categoryBooks.get(position).getBooksname());
		holder.count.setText(categoryBooks.get(position).getCount()+"");
		
		String httpUrl   = categoryBooks.get(position).getPictureUrl();
		if(!httpUrl.equals("") && httpUrl.length()>0){
			Bitmap bitmap = MyUtils.getImageBitmap(mContext, httpUrl);
			holder.imageView.setImageBitmap(bitmap);
		}
		return convertView;
	}
	
	class viewHolder{
		ImageView imageView;
		TextView category;
		TextView detail;
		TextView count;
	}

}
