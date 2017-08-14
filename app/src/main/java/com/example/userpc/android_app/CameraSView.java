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
        //get the holder and set this class as the callback,so we can get camera data here
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            //setting the camera to draw images in this holder when surface has been created
            myCam.setPreviewDisplay(holder);
            myCam.startPreview();
        } catch (IOException e) {
            Log.d("Err", "Camera Preview Error: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        myCam.stopPreview();
        myCam.release();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
               if (surfaceHolder.getSurface() == null)//check if the surface is ready to receive the camera
                     return;



        try {
            myCam.stopPreview();
        } catch (Exception e){

        }//now recreate the camera preview
        try {
            myCam.setPreviewDisplay(surfaceHolder);
            myCam.startPreview();

        } catch (Exception e){
            Log.d("Err", "Camera Start Error: " + e.getMessage());
        }
    }
}


