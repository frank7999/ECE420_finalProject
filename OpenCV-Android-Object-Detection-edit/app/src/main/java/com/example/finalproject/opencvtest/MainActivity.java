package com.example.finalproject.opencvtest;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.opencvtest.R;


public class MainActivity extends Activity {

    public static int appFlag = 0;
    private Button button1;


    //private Button button2;
    private TextView textView;
    private TextView textView2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_selection);
        //super.setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Assigning buttons and text view to their respective views in XML layout
        button1 = (Button) findViewById(R.id.button1);
        //button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appFlag = 1;
                Intent intent = new Intent(MainActivity.this, page1.class);
                startActivity(intent);
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
}