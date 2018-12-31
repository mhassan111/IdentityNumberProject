package com.identitynumber.app.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ActionMenuView;

import com.identitynumber.app.R;
import com.identitynumber.app.util.ActivityUtil;
import com.orhanobut.logger.Logger;

public class SplashActivity extends AppCompatActivity {

    private static final int mDelay = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Logger.d(SplashActivity.class.getSimpleName() + ": launching login activity");
                ActivityUtil.launchSignInActivity(SplashActivity.this);
                finish();
            }
        }, mDelay);
    }
}
