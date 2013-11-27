package com.hncu.taoshu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hncu.taoshu.R;
import com.hncu.taoshu.ui.base.MyButton;

public class MenuAdapter extends BaseAdapter{

	private String[] items;
	private LayoutInflater mInflater;
	
	public MenuAdapter(Context mContext ,String[] items) {
		super();
		this.items = items;
		mInflater = LayoutInflater.from(mContext);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return items==null?0:items.length;
	}
	public Object getItem(int arg0) {
		return items==null?null:items[arg0];
	}
	public long getItemId(int position) {
		return items==null?0:position;
	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder= null;
		if (convertView==null) {
			convertView = mInflater.inflate(R.layout.menu_popupwindow_item, null);
			holder = new ViewHolder();
			holder.item = (MyButton) convertView.findViewById(R.id.menu_popup_btn);
			convertView.setTag(holder);
			
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.item.setText(items[position]);
		return convertView;
	}
	
	
	class ViewHolder{
		private MyButton item;
	}

}
