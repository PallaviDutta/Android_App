package com.example.userpc.android_app;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {

    private Camera myCam;
    private CameraSView surfaceView;
    public static final int IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        myCam = getCameraInstance();
        myCam.setDisplayOrientation(90);

        // Create our Preview view and set it as the content of our activity.
        surfaceView = new CameraSView(this, myCam);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(surfaceView);

        Button captureButton = (Button) findViewById(R.id.capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myCam.takePicture(null, null, mPicture);
                        startActivity(new Intent(CameraActivity.this,Details.class));
                    }
                }
        );
    }
    protected Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e){
        }
        return c;
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(IMAGE);
            if (pictureFile == null){
//                Log.d("", "Error creating media file, check storage permissions: " +
//                        e.getMessage());
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d("File Error", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("File Error", "Error accessing file: " + e.getMessage());
            }
        }
    };

    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Inventrom");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("CamApp", "failed");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type ==IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "Cam_" + timeStamp + ".jpg");
        }else {
            return null;
        }

        return mediaFile;
    }
}
