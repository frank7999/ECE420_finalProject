package com.example.finalproject.opencvtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
// import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.finalproject.opencvtest.R;

import java.io.IOException;
import java.lang.Math;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class page1 extends Activity implements SurfaceHolder.Callback{

    private Camera camera;
    boolean previewing = false;
    private int width = 640;
    private int height = 480;
    // Kernels
    private double[][] kernelS = new double[][] {{-1,-1,-1},{-1,9,-1},{-1,-1,-1}};
    private double[][] kernelX = new double[][] {{1,0,-1},{1,0,-1},{1,0,-1}};
    private double[][] kernelY = new double[][] {{1,1,1},{0,0,0},{-1,-1,-1}};
    public static int appFlag = 0;
    private Button button2, button20;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    //private Button button2;
    private TextView textView3;
    //private TextView textView2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.logo_selection);
        //super.setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Assigning buttons and text view to their respective views in XML layout
        button2 = (Button) findViewById(R.id.button2);
        button20 = (Button) findViewById(R.id.button20);
        textView3 = (TextView) findViewById(R.id.textView3);
        //textView2 = (TextView) findViewById(R.id.textView2);

        surfaceView = (SurfaceView)findViewById(R.id.View);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback((SurfaceHolder.Callback) this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appFlag = 1;
                Intent intent2 = new Intent(page1.this, starbucks.class);
                startActivity(intent2);
            }
        });

        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appFlag = 1;
                Intent intent3 = new Intent(page1.this, ge.class);
                startActivity(intent3);
            }
        });

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                appFlag = 1;
//                Intent intent = new Intent(MainActivity.this, book.class);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Must have to override native method
        return;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            if(!previewing) {
                camera = Camera.open();
                if (camera != null) {
                    try {
                        // Modify Camera Settings
                        Camera.Parameters parameters = camera.getParameters();
                        parameters.setPreviewSize(width, height);
                        // Following lines could log possible camera resolutions, including
                        // 2592x1944;1920x1080;1440x1080;1280x720;640x480;352x288;320x240;176x144;
                        // List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
                        // for(int i=0; i<sizes.size(); i++) {
                        //     int height = sizes.get(i).height;
                        //     int width = sizes.get(i).width;
                        //     Log.d("size: ", Integer.toString(width) + ";" + Integer.toString(height));
                        // }
                        camera.setParameters(parameters);
                        //camera.setDisplayOrientation(90);
                        camera.setPreviewDisplay(surfaceHolder);
                        camera.setPreviewCallback(new PreviewCallback() {
                            public void onPreviewFrame(byte[] data, Camera camera)
                            {
                                // Lock canvas
                                //Canvas canvas = surfaceHolder2.lockCanvas(null);
                                // Where Callback Happens, camera preview frame ready
                                //onCameraFrame(canvas,data);
                                // Unlock canvas
                                //surfaceHolder2.unlockCanvasAndPost(canvas);
                            }
                        });
                        camera.startPreview();
                        previewing = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Cleaning Up
        if (camera != null && previewing) {
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
            previewing = false;
        }
    }

    // Camera Preview Frame Callback Function
//    protected void onCameraFrame(Canvas canvas, byte[] data) {
//
//        Matrix matrix = new Matrix();
//        matrix.postRotate(90);
//        int retData[] = new int[width * height];
//
//        // Apply different processing methods
////        if(MainActivity.appFlag == 1){
////            byte[] histeqData = histEq(data, width, height);
////            retData = yuv2rgb(histeqData);
////        }
////        else if (MainActivity.appFlag == 2){
////
////            int[] sharpData = conv2(data, width, height, kernelS);
////            retData = merge(sharpData, sharpData);
////        }
////        else if (MainActivity.appFlag == 3){
////            int[] xData = conv2(data, width, height, kernelX);
////            int[] yData = conv2(data, width, height, kernelY);
////            retData = merge(xData, yData);
////        }
//
//        // Create ARGB Image, rotate and draw
//        Bitmap bmp = Bitmap.createBitmap(retData, width, height, Bitmap.Config.ARGB_8888);
//        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
//        canvas.drawBitmap(bmp, new Rect(0,0, height, width), new Rect(0,0, canvas.getWidth(), canvas.getHeight()),null);
//    }





}


