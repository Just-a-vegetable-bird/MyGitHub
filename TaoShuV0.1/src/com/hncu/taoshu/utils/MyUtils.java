package com.hncu.taoshu.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.hncu.taoshu.async.ImageFileCache;
import com.hncu.taoshu.async.ImageGetFromHttp;
import com.hncu.taoshu.async.ImageMemoryCache;

public class MyUtils {
	/**
	 * �ж������Ƿ�����
	 * @param context
	 * @return
	 */
	public static boolean checkNet(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
//		Toast.makeText(context, "��ǰ���������ӣ�", Toast.LENGTH_LONG).show();
		return false;
	}
	
	public static Bitmap getImageBitmap(Context context, String url) {
		ImageMemoryCache memoryCache;
		ImageFileCache fileCache;
		memoryCache = new ImageMemoryCache(context);
		fileCache = new ImageFileCache();
		// ���ڴ滺���л�ȡͼƬ
		Bitmap result = memoryCache.getBitmapFromCache(url);
		if (result == null) {
			// �ļ������л�ȡ
			result = fileCache.getImage(url);
			if (result == null) {
				// �������ȡ
				result = ImageGetFromHttp.downloadBitmap(url);
				if (result != null) {
					fileCache.saveBitmap(result, url);
					memoryCache.addBitmapToCache(url, result);
				}
			} else {
				// ��ӵ��ڴ滺��
				memoryCache.addBitmapToCache(url, result);
			}
		}
		return result;
	}
}
