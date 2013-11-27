package com.hncu.taoshu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.hncu.taoshu.R;
import com.hncu.taoshu.adapter.BookAddAdapter;
import com.hncu.taoshu.bean.Book;
import com.hncu.taoshu.utils.ConstExt;

public class BookAdd extends Activity{

	private Button btnBack;
	private ImageView confirm;
	private GridView dispaly;
	public static final String FROM = "froom";
	public static int FROMBOOKROOM = 1;
	public static int FROMBORROWROOM = FROMBOOKROOM+1;
	private int fromWhere;
	private BookAddAdapter adapter;
//	List<Map<String, String>> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_add);
		Intent intent = getIntent();
		fromWhere = intent.getIntExtra(FROM, FROMBOOKROOM);
		findViewById();
		setClickListener();
	}
	private  void findViewById() {
		// TODO Auto-generated method stub
		dispaly = (GridView) findViewById(R.id.book_add_display);
		adapter = new BookAddAdapter(fromWhere,this);
		dispaly.setAdapter(adapter);
	}
	private void setClickListener() {
		dispaly.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				System.out.println("Selected-->"+arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		dispaly.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(BookAdd.this,BookDetail.class);
				Book book = (Book) adapter.getItem(arg2);
				intent.putExtra("DATA", book);
				startActivity(intent);
			}
		});
	}
	private boolean update(){
		return false;
	}
}
