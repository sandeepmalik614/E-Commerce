package com.iknoortech.e_commerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.iknoortech.e_commerce.R;
import com.iknoortech.e_commerce.utils.AppUtils;

public class ForgotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        AppUtils.setStatusBarTransparent(this);
    }
}
