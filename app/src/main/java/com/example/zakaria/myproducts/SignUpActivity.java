package com.example.zakaria.myproducts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText emailSignUpET, passwordSignUpET;
    private TextView errorMsg;
    private String intentValue;
    private AppCompatCheckBox passwordShowCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        errorMsg = findViewById(R.id.errorMsgId);
        emailSignUpET = findViewById(R.id.emailSignUpET);
        passwordSignUpET = findViewById(R.id.passwordSignUpET);
        passwordShowCheckBox = findViewById(R.id.passwordShowCheckBox);

        getPasswordShowHide();

        firebaseAuth = FirebaseAuth.getInstance();
        intentValue = getIntent().getStringExtra("login_key");
    }

    public void signUpBtn(View view) {
        String email = emailSignUpET.getText().toString().trim();
        String password = passwordSignUpET.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailSignUpET.setError("Empty Email Address");
            emailSignUpET.setText(null);
        }
        if (TextUtils.isEmpty(password)) {
            passwordSignUpET.setError("Empty Password");
            emailSignUpET.setText(null);
        }
        else if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull final Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Log.e("tag_login", "success");
                        if (intentValue.equals("login_for_add_product")) {
                            startActivity(new Intent(SignUpActivity.this, ProductAddActivity.class));
                            finish();
                        }
                        else if (intentValue.equals("login_for_my_account")) {
                            startActivity(new Intent(SignUpActivity.this, ProductListActivity.class));
                            finish();
                        }
                    }
                    else {
                       /* Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();*/
                        errorMsg.setVisibility(View.VISIBLE);
                        errorMsg.setText(task.getException().getMessage());
                        errorMsg.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                errorMsg.setVisibility(View.GONE);
                            }
                        }, 8000);
                        Log.w("tag_sign_up", "failed", task.getException());
                    }
                }
            });
        }
    }

    public void loginTv(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    public void continueWithPhone(View view) {
        Toast.makeText(this, "ph", Toast.LENGTH_SHORT).show();
    }

    public void getPasswordShowHide() {
        passwordShowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    //show password
                    passwordSignUpET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else {
                    //hide password
                    passwordSignUpET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
}
