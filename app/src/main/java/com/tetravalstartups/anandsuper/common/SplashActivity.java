package com.tetravalstartups.anandsuper.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.tetravalstartups.anandsuper.R;
import com.tetravalstartups.anandsuper.auth.LoginActivity;
import com.tetravalstartups.anandsuper.utils.Constant;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMain();
            }
        }, Constant.SPLASH_DELAY);
    }

    private void gotoLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    private void gotoMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}