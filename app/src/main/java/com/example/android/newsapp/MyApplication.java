package com.example.android.newsapp;

import android.app.Application;

//import com.pushbots.push.Pushbots;

/**
 * Created by User on 10/2/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Pushbots Library
        //Pushbots.sharedInstance().init(this);
    }
}
