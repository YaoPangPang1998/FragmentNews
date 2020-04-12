package com.ypp.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ypp.newsapp.fragments.NewsContentFragment;

public class NewsContentActivity extends AppCompatActivity {
    private static final String TAG = "ssssss";
    public static void actionStart(Context context,String newsTitle,String newsContent){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("newsTitle",newsTitle);
        intent.putExtra("newsContent",newsContent);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle=getIntent().getStringExtra("newsTitle");
        String newsContent=getIntent().getStringExtra("newsContent");
        NewsContentFragment newsContentFragment=(NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        //调用Fragment初始化方法刷新数据
        newsContentFragment.refresh(newsTitle,newsContent);
        Log.i(TAG, "onCreate: ");
    }

}
