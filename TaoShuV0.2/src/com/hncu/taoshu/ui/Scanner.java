package com.hncu.taoshu.ui;

import java.io.IOException;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.hncu.taoshu.R;
import com.hncu.taoshu.adapter.ScanAdapter;
import com.hncu.taoshu.ui.base.CameraView;
import com.hncu.taoshu.utils.ConstExt;

public class Scanner extends Activity implements OnClickListener {

	private static final String Z_BAR_LIBRARY = "iconv";
	private static final String GREY_COLOR_SPACE = "Y800";
	private static final long REFRESH_TIME = 2000;

	private boolean IsScanning = true;
	private boolean IsFlashLightOpen = false;
	private long startPreView;

	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;

	private ImageScanner scanner;
	private CameraView cameraView;
	private PackageManager packageManager;
	private Handler autoFocusHandler = new Handler();
	private Runnable runAutoFocus = new CustomAutoFocusRunnable();
	private Camera.PreviewCallback previewCallback = new CustomPreviewCallback();
	private Camera.AutoFocusCallback autoFocusCallback = new CustomAutoFocusCallback();

	private Button btnBack;
	private Button btnAdd;
	private ImageView flashLight;
	private ListView display;
	private ScanAdapter adapter;
	private int fromWhere = BookAdd.FROMBOOKROOM;

	static {
		System.loadLibrary(Z_BAR_LIBRARY);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		scanner = new ImageScanner();
		scanner.setConfig(0, Config.X_DENSITY, 3);
		scanner.setConfig(0, Config.Y_DENSITY, 3);

		fromWhere = getIntent().getIntExtra(BookAdd.FROM, 0);
		if (isHaveAutoFocus()) {
			autoFocusHandler.postDelayed(runAutoFocus, REFRESH_TIME);
		}
		findViewById();
		setListener();

	}

	private void setListener() {
		btnBack.setOnClickListener(this);
		flashLight.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
		
		adapter = new ScanAdapter(this);
		
		display.setAdapter(adapter);
	}

	private void findViewById() {
		// TODO Auto-generated method stub
		cameraView = (CameraView) findViewById(R.id.cameraview);
		cameraView.init(this, previewCallback);
		flashLight = (ImageView) findViewById(R.id.scan_flashlight);
		btnBack = (Button) findViewById(R.id.scan_back);
		display = (ListView) findViewById(R.id.scan_dispaly);
		btnAdd = (Button) findViewById(R.id.scan_add);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (isHaveAutoFocus())
			cameraView.getCamera().cancelAutoFocus();
	}

	@Override
	public void onResume() {
		super.onResume();
		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
		try {
			if (isHaveAutoFocus())
				cameraView.getCamera().autoFocus(autoFocusCallback);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isHaveAutoFocus() {
		if (packageManager == null) {
			packageManager = this.getPackageManager();
		}
		return packageManager
				.hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS);
	}

	private class CustomAutoFocusRunnable implements Runnable {
		public void run() {
			try {
				if (cameraView != null && cameraView.getCamera() != null
						&& isHaveAutoFocus())
					cameraView.getCamera().autoFocus(autoFocusCallback);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class CustomAutoFocusCallback implements Camera.AutoFocusCallback {
		public void onAutoFocus(boolean success, Camera camera) {
			autoFocusHandler.postDelayed(runAutoFocus, REFRESH_TIME);
		}
	}

	private class CustomPreviewCallback implements Camera.PreviewCallback {
		public void onPreviewFrame(byte[] data, Camera incomingCamera) {
			if (IsScanning) {
				startPreView = System.currentTimeMillis();
				IsScanning = false;
				Camera.Parameters cameraParameters = incomingCamera
						.getParameters();
				Camera.Size previewSize = cameraParameters.getPreviewSize();
				Image barcode = new Image(previewSize.width,
						previewSize.height, GREY_COLOR_SPACE);
				barcode.setData(data);
				int result = scanner.scanImage(barcode);

				if (result != 0) {
					SymbolSet scannerResults = scanner.getResults();
					for (Symbol symbol : scannerResults) {
						playBeepSoundAndVibrate();
						// Toast.makeText(Scanner.this, symbol.getData(),
						// Toast.LENGTH_SHORT).show();
						adapter.add(symbol.getData());
						break;
					}

				}
			}
			long endPreview = System.currentTimeMillis();
			if ((endPreview - startPreView) >= 2000) {
				IsScanning = true;
			}
		}
	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.scan_back:
			onBackPressed();
			break;
		case R.id.scan_flashlight:
			if (IsFlashLightOpen) {
				Camera.Parameters parameter = cameraView.getCamera()
						.getParameters();
				parameter.setFlashMode(Parameters.FLASH_MODE_OFF);
				IsFlashLightOpen = false;
				cameraView.getCamera().setParameters(parameter);
			} else {
				Camera.Parameters parameter = cameraView.getCamera()
						.getParameters();
				parameter.setFlashMode(Parameters.FLASH_MODE_TORCH);
				IsFlashLightOpen = true;
				cameraView.getCamera().setParameters(parameter);
			}
			break;
		case R.id.scan_add:
			Intent intent = new Intent(this,BookAdd.class);
			intent.putExtra(BookAdd.FROM, fromWhere);
			startActivity(intent);
		default:
			break;
		}

	}

	@Override
	public void onBackPressed() {
		if (adapter.data.size() > 0) {
			new AlertDialog.Builder(this).setMessage("当前有未保存的书籍，确定要不保存直接退出吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if(fromWhere== BookAdd.FROMBOOKROOM){
						ConstExt.bookRoomAddDdata=null;
					}
					else {
						ConstExt.borrowRoomAddDdata=null;
					}
					finish();
				}
			}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			}).show();
					
		} else {
			super.onBackPressed();
		}
	}

}
