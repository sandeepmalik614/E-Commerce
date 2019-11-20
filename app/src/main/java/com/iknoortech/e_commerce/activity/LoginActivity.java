package com.iknoortech.e_commerce.activity;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.FieldClassification;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.iknoortech.e_commerce.R;
import com.iknoortech.e_commerce.utils.AppUtils;

import java.util.regex.Matcher;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_email, edt_password;
    private boolean isDoublePressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_login_email);
        edt_password = findViewById(R.id.edt_login_password);
    }

    public void goToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void validateLogin(View view) {
        if(AppUtils.isConnectionAvailable(this)) {
            if (edt_email.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(edt_email.getText().toString()).matches()) {
                Toast.makeText(this, "Please enter a valid email id", Toast.LENGTH_SHORT).show();
            } else if (edt_password.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }
        }else{
            AppUtils.showNoInternetToast(this);
        }
    }

    public void goToForgot(View view) {
        startActivity(new Intent(this, ForgotActivity.class));
    }

    @Override
    public void onBackPressed() {
        if(isDoublePressed){
            super.onBackPressed();
        }else{
            isDoublePressed = true;
            Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isDoublePressed = false;
                }
            }, 2000);
        }
    }
}
