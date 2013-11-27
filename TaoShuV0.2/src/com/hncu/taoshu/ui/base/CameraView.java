package com.hncu.taoshu.ui.base;


import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Author: Artemiy Garin
 * Date: 16.10.13
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private Camera.PreviewCallback previewCallback;

    
	public void init(Context context, Camera.PreviewCallback previewCallback){
        this.previewCallback = previewCallback;
        
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        setKeepScreenOn(true);
        getCamera();
    }

    public CameraView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CameraView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CameraView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        stopCamera();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        startCamera();
    }

    public Camera getCamera() {
        try {
            if (camera == null) {
                camera = Camera.open();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return camera;
    }

    public void stopCamera() {
        try {

            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startCamera() {
        try {

            if (surfaceHolder.getSurface() == null) {
                return;
            }

            camera.reconnect();
            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceHolder);
            camera.setPreviewCallback(previewCallback);
            camera.startPreview();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
