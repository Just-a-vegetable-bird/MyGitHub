package com.hncu.taoshu.ui.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.hncu.taoshu.R;

public class ViewFinderView extends View {
/**
 * 动画延时时间
 */
	private static final long ANIMATION_DELAY = 10L;
	private final Paint paint;
	private final int maskColor;

	/**
	 * 扫描框下方的习题大小及距扫描框的距离
	 */
	private static final int TEXT_SIZE = 16;
	private static final int TEXT_PADDING_TOP = 30;
	/**
	 * 扫描条的路径
	 */
	private static int path = 0;
	private static final  int LENGTH = 3;
	/**
	 * 手机的屏幕密度
	 */
	private static float density;

	private Rect frame;
	
/**
 * 四个边角框对应的长度
 */
	private int ScreenRate = 20;
	private static final int CORNER_WIDTH = 10;

	// This constructor is used when the class is built from an XML resource.
	public ViewFinderView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// Initialize these once for performance rather than calling them every
		// time in onDraw().
		paint = new Paint();
		Resources resources = getResources();
		maskColor = resources.getColor(R.color.viewfinder_mask);

		density = context.getResources().getDisplayMetrics().density;

		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		DisplayMetrics dmDisplayMetrics = new DisplayMetrics();
		display.getMetrics(dmDisplayMetrics);
		Point screenResolution = new Point(dmDisplayMetrics.widthPixels,
				dmDisplayMetrics.heightPixels);
		int width = (int) (200 * density);
		int height = (int) (200 * density);
		int leftOffset = (screenResolution.x - width) / 2;
		int topOffset = 80;
		frame = new Rect(leftOffset, topOffset, leftOffset + width, topOffset
				+ height);
	}


	@Override
	public void onDraw(Canvas canvas) {

		if (frame == null) {
			return;
		}
		if (path == 0) {
			path = frame.top;
		}
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		// 画扫描边框外部阴影部分

		paint.setColor(maskColor);
		canvas.drawRect(0, 0, width, frame.top, paint);
		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
				paint);
		canvas.drawRect(0, frame.bottom + 1, width, height, paint);



		// 画四个小框
		paint.setColor(Color.GREEN);
		canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
				frame.top + CORNER_WIDTH, paint);
		canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH,
				frame.top + ScreenRate, paint);
		canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
				frame.top + CORNER_WIDTH, paint);
		canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right,
				frame.top + ScreenRate, paint);
		canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
				+ ScreenRate, frame.bottom, paint);
		canvas.drawRect(frame.left, frame.bottom - ScreenRate, frame.left
				+ CORNER_WIDTH, frame.bottom, paint);
		canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
				frame.right, frame.bottom, paint);
		canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
				frame.right, frame.bottom, paint);



		path += LENGTH;
		if (path >= frame.bottom) {
			path = frame.top;
		}

		Rect lineRect = new Rect();
		lineRect.left = frame.left;
		lineRect.right = frame.right;
		lineRect.top = path;
		lineRect.bottom = path + 18;
		canvas.drawBitmap(((BitmapDrawable) (getResources()
				.getDrawable(R.drawable.qrcode_scan_line))).getBitmap(), null,
				lineRect, paint);

		paint.setColor(Color.WHITE);
		paint.setTextSize((int) (200 * TEXT_SIZE));
		paint.setAlpha(0x40);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		String text = "请将条形码对准扫描框";
		float textWidth = paint.measureText(text);

		canvas.drawText(text, (width - textWidth) / 2,
				(float) (frame.bottom + (float) TEXT_PADDING_TOP * density),
				paint);

		/**
		 * 仅更新扫描框里的内容
		 */
		postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
				frame.right, frame.bottom);
	}

	public Point getCameraPoint() {
		return new Point(frame.left, frame.top);
	}

}
