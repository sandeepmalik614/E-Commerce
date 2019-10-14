package com.iknoortech.e_commerce.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.iknoortech.e_commerce.R;

import java.util.HashMap;
import java.util.Map;

import static com.iknoortech.e_commerce.utils.AppConstant.userTabel;
import static com.iknoortech.e_commerce.utils.AppUtils.setStatusBarTransparent;

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_name, edt_email, edt_phone, edt_pass, edt_con_pass;
    private CheckBox cb;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;
    private FirebaseFirestore mFireStore;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setStatusBarTransparent(this);
        
        edt_name = findViewById(R.id.edt_registerName);
        edt_email = findViewById(R.id.edt_registerEmail);
        edt_phone = findViewById(R.id.edt_registerPhone);
        edt_pass = findViewById(R.id.edt_registerPass);
        edt_con_pass = findViewById(R.id.edt_registerConPass);
        cb = findViewById(R.id.cb_regirster);

        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        
    }

    public void goToLoginFromRegister(View view) {
        finish();
    }

    public void validateRegister(View view) {
        if(edt_name.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
        }else if(edt_email.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter email-id", Toast.LENGTH_SHORT).show();
        }else if(edt_phone.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
        }else if(edt_pass.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter pass", Toast.LENGTH_SHORT).show();
        }else if(!edt_pass.getText().toString().equals(edt_con_pass.getText().toString())){
            Toast.makeText(this, "Password and Confirm Password should be same", Toast.LENGTH_SHORT).show();
        }else if(!cb.isChecked()){
            Toast.makeText(this, "You have to accept the terms", Toast.LENGTH_SHORT).show();
        }else {
            registerUser(view, edt_name.getText().toString(), edt_email.getText().toString(), edt_phone.getText().toString(), edt_pass.getText().toString());
        }
    }

    private void registerUser(final View view, final String name, final String email, final String phone, String password) {
        view.setClickable(false);
        pd.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();
                    HashMap<Object, String> userData = new HashMap<>();
                    userData.put("UserName", name);
                    userData.put("UserEmail", email);
                    userData.put("UserPhone", phone);
                    userData.put("UserId", user.getUid());

                    mFireStore.collection(userTabel)
                            .add(userData)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    pd.dismiss();
                                    view.setClickable(false);
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(RegisterActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    Toast.makeText(RegisterActivity.this, "Welcome: "+task.getResult().getUser().getEmail(), Toast.LENGTH_SHORT).show();
                }else{
                    pd.dismiss();
                    view.setClickable(true);
                    Toast.makeText(RegisterActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
