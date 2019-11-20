package com.iknoortech.e_commerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.iknoortech.e_commerce.R;
import com.iknoortech.e_commerce.utils.AppUtils;

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_firstName, edt_lastName, edt_email, edt_password, edt_conPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_firstName = findViewById(R.id.edt_register_firstName);
        edt_lastName = findViewById(R.id.edt_register_lastName);
        edt_email = findViewById(R.id.edt_register_email);
        edt_password = findViewById(R.id.edt_register_password);
        edt_conPass = findViewById(R.id.edt_register_conPass);

    }

    public void validateRegister(View view) {
        if(AppUtils.isConnectionAvailable(this)) {
            if (edt_firstName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter your first name", Toast.LENGTH_SHORT).show();
            } else if (edt_lastName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter your last name", Toast.LENGTH_SHORT).show();
            } else if (edt_email.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter your email id", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(edt_email.getText().toString()).matches()) {
                Toast.makeText(this, "Please enter a valid email id", Toast.LENGTH_SHORT).show();
            } else if (edt_password.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            } else if (!edt_password.getText().toString().equals(edt_conPass.getText().toString())) {
                Toast.makeText(this, "Password and confirm password should be same", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "All done", Toast.LENGTH_SHORT).show();
            }
        }else {
            AppUtils.showNoInternetToast(this);
        }
    }

    public void goToLogin(View view) {
        finish();
    }
}
