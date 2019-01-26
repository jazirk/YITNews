package com.example.android.newsapp;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by User on 10/22/2018.
 */

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: Add OneSignal initialization here

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
