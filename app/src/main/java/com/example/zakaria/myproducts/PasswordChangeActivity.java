package com.example.zakaria.myproducts;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseUser;

public class PasswordChangeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView errorMsg, passChangeHeaderTV, passChangeSubTV;
    private EditText newPassword, confirmPassword, emailAddress;
    private Button changePasswordBtn;
    private String passIntentValue;

    private String newPass, confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        errorMsg = findViewById(R.id.errorMsgId);
        passChangeHeaderTV = findViewById(R.id.passChangeHeaderTV);
        passChangeSubTV = findViewById(R.id.passChangeSubTV);
        changePasswordBtn = findViewById(R.id.changePasswordBtn);
        emailAddress = findViewById(R.id.emailAddress);
        newPassword = findViewById(R.id.newPass);
        confirmPassword = findViewById(R.id.confirmPass);

        firebaseAuth = FirebaseAuth.getInstance();

        passIntentValue = getIntent().getStringExtra("password_key");
        if (passIntentValue.equals("forgot_password")) {
            forgotPasswordEmailVerification();
        } else if (passIntentValue.equals("change_password")) {
            changePassword();
        }
    }

    public void changePassword() {
        setTitle("Change Password");
        passChangeHeaderTV.setText("Change Your Password");
        passChangeSubTV.setText("To change your password, enter a new password, confirm password and press the change password button");
        newPassword.setVisibility(View.VISIBLE);
        confirmPassword.setVisibility(View.VISIBLE);
        changePasswordBtn.setText("Change Password");

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPass = newPassword.getText().toString().trim();
                confirmPass = confirmPassword.getText().toString().trim();
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if (newPass.length() <= 0) {
                    newPassword.setError("New password can not be empty or white space");
                }
                if (confirmPass.trim().length() <= 0) {
                    confirmPassword.setError("Confirm password can not be empty or white space");
                } else if (newPass.length() > 0 && confirmPass.length() > 0) {

                    if (newPass.equals(confirmPass)) {
                        user.updatePassword(confirmPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.e("pass", "success");
                                } else {
                                    if (task.getException() instanceof FirebaseAuthRecentLoginRequiredException) {
                                        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), confirmPass);
                                        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.e("reAuth", "User re-authenticated.");
                                                } else {
                                                    Log.e("reAuth", "fail");
                                                    errorMsg.setVisibility(View.VISIBLE);
                                                    errorMsg.setText(task.getException().getMessage());
                                                    errorMsg.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            errorMsg.setVisibility(View.GONE);
                                                        }
                                                    }, 8000);
                                                }
                                            }
                                        });
                                    } else {
                                        Log.e("pass", "fail");
                                        errorMsg.setVisibility(View.VISIBLE);
                                        errorMsg.setText(task.getException().getMessage());
                                        errorMsg.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                errorMsg.setVisibility(View.GONE);
                                            }
                                        }, 8000);
                                    }
                                }
                            }
                        });
                    } else {
                        errorMsg.setVisibility(View.VISIBLE);
                        errorMsg.setText("password not matched");
                        errorMsg.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                errorMsg.setVisibility(View.GONE);
                            }
                        }, 8000);
                    }
                }
            }
        });
    }

    public void forgotPasswordEmailVerification() {
        setTitle("Reset Password");
        passChangeHeaderTV.setText("Forgot your password?");
        passChangeSubTV.setText("To reset your password, enter your email, press the button and check mail to follow instruction");
        emailAddress.setVisibility(View.VISIBLE);
        changePasswordBtn.setText("Reset Password");

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailAddress.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    errorMsg.setVisibility(View.VISIBLE);
                    errorMsg.setText("Email field can not be empty");
                    errorMsg.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errorMsg.setVisibility(View.GONE);
                        }
                    }, 8000);
                } else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                errorMsg.setVisibility(View.VISIBLE);
                                errorMsg.setTextColor(Color.BLACK);
                                errorMsg.setText("Check your email to reset your password!");
                                errorMsg.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        errorMsg.setVisibility(View.GONE);
                                        startActivity(new Intent(PasswordChangeActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                }, 8000);
                                Log.e("sent_email", "yes");
                            } else {
                                errorMsg.setVisibility(View.VISIBLE);
                                errorMsg.setText("Fail to send reset password email!");
                                errorMsg.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        errorMsg.setVisibility(View.GONE);
                                    }
                                }, 8000);
                                Log.e("sent_email", "no");
                            }
                        }
                    });
                }
            }
        });
    }
}
