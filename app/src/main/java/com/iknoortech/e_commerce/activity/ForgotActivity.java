package com.iknoortech.e_commerce.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.iknoortech.e_commerce.R;
import com.iknoortech.e_commerce.utils.AppUtils;

public class ForgotActivity extends AppCompatActivity {

    private EditText edt_email;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        AppUtils.setStatusBarTransparent(this);

        edt_email = findViewById(R.id.editText);

        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        pd.setMessage("Sending mail...");
        pd.setCancelable(false);
    }

    public void sendForgotEmail(View view) {
        if(edt_email.getText().toString().isEmpty()){
            edt_email.setError("Please enter your registered email id");
        }else {
            pd.show();
            mAuth.sendPasswordResetEmail(edt_email.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    pd.dismiss();
                    if(task.isSuccessful()){
                        openCodeSentDialog();
                    }else{
                        Toast.makeText(ForgotActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void openCodeSentDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_code_sent);

        Button btn_done = dialog.findViewById(R.id.btn_dialogDone);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
    }
}
