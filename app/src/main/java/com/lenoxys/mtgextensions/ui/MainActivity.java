package com.lenoxys.mtgextensions.ui;

import android.content.Intent;
import android.os.Bundle;

import com.lenoxys.mtgextensions.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private int delay = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    private void setup() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.loading_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, delay);
    }
}