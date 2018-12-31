package com.identitynumber.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.identitynumber.app.di.AppComponent;
import com.identitynumber.app.di.DaggerAppComponent;

public class MyApplication extends MultiDexApplication {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
