package com.iknoortech.e_commerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iknoortech.e_commerce.R;
import com.iknoortech.e_commerce.utils.AppUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppUtils.setStatusBarTransparent(this);
    }

    public void goToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void validateLogin(View view) {
    }

    public void goToForgot(View view) {
        startActivity(new Intent(this, ForgotActivity.class));
    }

    public void fbLogin(View view) {
    }

    public void googleLogin(View view) {
    }
}
