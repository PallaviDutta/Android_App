package com.example.userpc.android_app;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by user pc on 12-08-2017.
 */

public class CameraSView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Camera myCam;

    public CameraSView(Context context, Camera camera) {
        super(context);
        myCam = camera;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            myCam.setPreviewDisplay(holder);
            myCam.startPreview();
        } catch (IOException e) {
            Log.d("Err", "Camera Preview Error: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
               if (surfaceHolder.getSurface() == null)
                     return;



        try {
            myCam.stopPreview();
        } catch (Exception e){

        }
        try {
            myCam.setPreviewDisplay(surfaceHolder);
            myCam.startPreview();

        } catch (Exception e){
            Log.d("Err", "Camera Start Error: " + e.getMessage());
        }
    }
}
