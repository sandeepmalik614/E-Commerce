package com.iknoortech.e_commerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.iknoortech.e_commerce.R;
import com.iknoortech.e_commerce.utils.AppUtils;

public class ForgotActivity extends AppCompatActivity {

    private EditText edt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        edt_email = findViewById(R.id.edt_forgot_email);
    }

    public void sendForgotEmail(View view) {
        if (AppUtils.isConnectionAvailable(this)) {
            if(edt_email.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show();
            }else if(!Patterns.EMAIL_ADDRESS.matcher(edt_email.getText().toString()).matches()){
                Toast.makeText(this, "Please enter a valid email id", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "All done", Toast.LENGTH_SHORT).show();
            }
        }else{
            AppUtils.showNoInternetToast(this);
        }
    }
}
