package com.iknoortech.e_commerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iknoortech.e_commerce.R;
import com.iknoortech.e_commerce.utils.AppUtils;

public class SplashActivity extends AppCompatActivity {

    private Button signIn;
    private TextView tv_skip;
    private boolean doubleClickDisabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AppUtils.setStatusBarTransparent(this);

        signIn = findViewById(R.id.button);
        tv_skip = findViewById(R.id.textView2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signIn.setVisibility(View.VISIBLE);
                tv_skip.setVisibility(View.VISIBLE);
                Animation fadeIn = AnimationUtils
                        .loadAnimation(SplashActivity.this, R.anim.fade_in);
                signIn.startAnimation(fadeIn);
                tv_skip.startAnimation(fadeIn);
            }
        }, 1500);
    }

    public void goToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (doubleClickDisabled) {
            finish();
            super.onBackPressed();
        } else {
            doubleClickDisabled = true;
            Toast.makeText(this, "Click BACK again to exit.", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleClickDisabled = false;
                }
            }, 2000);
        }
    }

    public void skipToMain(View view) {
        Toast.makeText(this, "Man at work", Toast.LENGTH_SHORT).show();
    }
}
