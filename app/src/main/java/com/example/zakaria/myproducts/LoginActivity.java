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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText emailLoginET, passwordLoginET;
    private TextView errorMsg;
    private String intentValue;
    private AppCompatCheckBox passwordShowCheckBox;
    //private TextView forgotTV, signUpTV, continueWithPhoneTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        errorMsg = findViewById(R.id.errorMsgId);
        emailLoginET = findViewById(R.id.emailLoginET);
        passwordLoginET = findViewById(R.id.passwordLoginET);
        passwordShowCheckBox = findViewById(R.id.passwordShowCheckBox);
        /*forgotTV = findViewById(R.id.forgotPassId);
        signUpTV = findViewById(R.id.signUpId);
        continueWithPhoneTV = findViewById(R.id.continueWithPhoneId);*/

        getPasswordShowHide();

        firebaseAuth = FirebaseAuth.getInstance();
        intentValue = getIntent().getStringExtra("login_key");
    }

    public void continueWithPhone(View view) {
        Toast.makeText(this, "ph", Toast.LENGTH_SHORT).show();
    }

    public void signUpTv(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    public void forgotPasswordTv(View view) {
        startActivity(new Intent(LoginActivity.this, PasswordChangeActivity.class).putExtra("password_key", "forgot_password"));
    }

    public void loginBtn(View view) {
        String email = emailLoginET.getText().toString().trim();
        String password = passwordLoginET.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailLoginET.setError("Empty Email Address");
            emailLoginET.setText(null);
        }
        if (TextUtils.isEmpty(password)) {
            passwordLoginET.setError("Empty Password");
            emailLoginET.setText(null);
        }
        else if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.e("tag_login", "success");
                        if (intentValue.equals("login_for_add_product")) {
                            startActivity(new Intent(LoginActivity.this, ProductAddActivity.class));
                            finish();
                        }
                        else if (intentValue.equals("login_for_my_account")) {
                            startActivity(new Intent(LoginActivity.this, ProductListActivity.class));
                            finish();
                        }
                    }
                    else {
                        /*Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();*/
                        errorMsg.setVisibility(View.VISIBLE);
                        errorMsg.setText(task.getException().getMessage());
                        errorMsg.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                errorMsg.setVisibility(View.GONE);
                            }
                        }, 8000);
                        Log.w("tag_login", "failed", task.getException());
                    }
                }
            });
        }
    }

    public void getPasswordShowHide() {
        passwordShowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    //show password
                    passwordLoginET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else {
                    //hide password
                    passwordLoginET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
}
