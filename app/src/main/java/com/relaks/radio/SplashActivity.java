package com.relaks.radio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.postDelayed(mPendingLauncherRunnable, 2000);
    }
    private final Runnable mPendingLauncherRunnable = new Runnable() {
        @Override
        public void run() {
            Intent mIntent=new Intent(SplashActivity.this,HomeActivity.class);
            startActivity(mIntent);
            SplashActivity.this.finish();

        }
    };
}
