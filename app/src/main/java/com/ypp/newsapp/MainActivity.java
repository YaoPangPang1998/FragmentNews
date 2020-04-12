package com.ypp.newsapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者：create by Administrator on 2020/4/12
 * TIME BY 21:07
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ssssss";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
    }
}
