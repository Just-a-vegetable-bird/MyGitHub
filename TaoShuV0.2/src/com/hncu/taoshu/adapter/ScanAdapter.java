package com.hncu.taoshu.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hncu.taoshu.R;
import com.hncu.taoshu.async.TaoShuAsync;
import com.hncu.taoshu.async.TaoShuListener;
import com.hncu.taoshu.bean.Book;
import com.hncu.taoshu.utils.ConstExt;

public class ScanAdapter extends BaseAdapter {

	public List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	private LayoutInflater mInflater;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			ScanAdapter.this.notifyDataSetChanged();
		}
		
	};

	public ScanAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.scan_item, null);
			holder = new ViewHolder();
			holder.bookName = (TextView) convertView
					.findViewById(R.id.scan_book_name);
			holder.state = (TextView) convertView
					.findViewById(R.id.scan_book_state);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.bookName.setText(data.get(position).get("ISBN"));
		holder.state.setText(data.get(position).get("STATE"));
		return convertView;
	}

	class ViewHolder {
		TextView bookName;
		TextView state;
	}

	public synchronized void add(final String isbn) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ISBN", isbn);
		map.put("STATE", "正在识别");
		data.add(0, map);
		notifyDataSetChanged();
		
		TaoShuAsync.getBookAsync(new TaoShuListener() {
			@Override
			public void onException(String msg, int type) {
				// TODO Auto-generated method stub
				System.out.println("onException-->"+msg);
			}
			
			@Override
			public void onEnd(String msg, Object obj, int type) {
//				System.out.println("sadasdfsf");
				Book book = (Book) obj;
				HashMap<String, String> map = new HashMap<String, String>();
				if(!book.getISBN().equals("") && book.getISBN().length()>0){
					map.put("ISBN", book.getTITLE());
					map.put("STATE", "已识别");
					ConstExt.bookRoomAddDdata.add(0,book);
				}
				else {
					map.put("ISBN", isbn);
					map.put("STATE", "识别失败");
				}
				data.remove(0);
				data.add(0, map);
				handler.sendEmptyMessage(0);
			}
		}, isbn);
	}
	
	
}
