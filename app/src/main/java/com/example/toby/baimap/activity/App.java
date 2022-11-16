package com.example.toby.baimap.activity;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import cn.bmob.v3.Bmob;


public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Bmob.initialize(this,"2b20d3f08200f93b52b7aa11fbcc7916");
        //Bmob.initialize(this, "1c99d26ffcbf12cbf1f1560d9ab09345");
        SDKInitializer.initialize(this);
    }
}

