package com.example.zakaria.myproducts;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zakaria.myproducts.models.UpdateProfile;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class UpdateProfileActivity extends AppCompatActivity {

    private Uri downloadUrl;
    private StorageReference storageReference;
    private static final int PIC_SELECT = 1;
    private TextInputLayout nameTextInputLayout, phoneNumberTextInputLayout, locationTextInputLayout;
    private EditText nameUpdateET, phoneUpdateET, locationUpdateET;
    private Button updateBtn, changeImgBtn;
    private TextView updateTV;
    private ImageView profileImageView;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    //private MenuItem editItem;
    private static String userName;
    private static String phoneNumber;
    private static String location;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        profileImageView = findViewById(R.id.profileImageView);
        nameUpdateET = findViewById(R.id.nameUpdateET);
        phoneUpdateET = findViewById(R.id.phoneUpdateET);
        locationUpdateET = findViewById(R.id.locationUpdateET);
        updateBtn = findViewById(R.id.updateBtnId);
        changeImgBtn = findViewById(R.id.changeImgBtn);
        updateTV = findViewById(R.id.myProfileTV);
        nameTextInputLayout = findViewById(R.id.nameTextInputLayout);
        phoneNumberTextInputLayout = findViewById(R.id.phoneNumberTextInputLayout);
        locationTextInputLayout = findViewById(R.id.locationTextInputLayout);

        //tv = findViewById(R.id.tv);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        //getProfileImage();
        getCurrentUserInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_profile_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.updateProIconId) {
            nameUpdateET.setEnabled(true);
            phoneUpdateET.setEnabled(true);
            locationUpdateET.setEnabled(true);
            updateTV.setText("Update My Profile");
            changeImgBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.VISIBLE);

            nameTextInputLayout.setHintEnabled(true);
            phoneNumberTextInputLayout.setHintEnabled(true);
            locationTextInputLayout.setHintEnabled(true);
            //editItem.setVisible(false);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeImgBtn(View view) {
//        Others others = new Others(this);
//        others.changeProfileImg();

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PIC_SELECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uriFile = data.getData();
        String filePath = uriFile.getPath();
        storageReference = FirebaseStorage.getInstance().getReference().child("profile_image").child(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        UploadTask uploadTask = storageReference.putFile(uriFile);

        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return storageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUrl = task.getResult();
                }
                else {
                    Toast.makeText(UpdateProfileActivity.this, "Profile image is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateProfileBtn(View view) {
        try {
            DatabaseReference databaseReference = firebaseDatabase.getReference("user_profile");
            UpdateProfile updateProfile = new UpdateProfile(nameUpdateET.getText().toString(), phoneUpdateET.getText().toString(), locationUpdateET.getText().toString());

            userName = nameUpdateET.getText().toString();
            phoneNumber = phoneUpdateET.getText().toString();
            location = locationUpdateET.getText().toString();

            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(updateProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.e("data", "success");
                        getCurrentUserInfo();
                    } else {
                        Log.e("data", "failed");
                    }
                }
            });
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void getCurrentUserInfo() {
            final DatabaseReference databaseReference = firebaseDatabase.getReference("user_profile").child(firebaseAuth.getCurrentUser().getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            UpdateProfile updateProfile = dataSnapshot.getValue(UpdateProfile.class);
                            ArrayList<String> updatedDataList = new ArrayList<>();

                            updatedDataList.add(updateProfile.getUserName());
                            updatedDataList.add(updateProfile.getPhoneNumber());
                            updatedDataList.add(updateProfile.getLocation());

                            nameUpdateET.setText(updatedDataList.get(0));
                            phoneUpdateET.setText(updatedDataList.get(1));
                            locationUpdateET.setText(updatedDataList.get(2));
                        }
                        else {
                            Toast.makeText(UpdateProfileActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(UpdateProfileActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            //editItem.setVisible(true);
            nameUpdateET.setEnabled(false);
            phoneUpdateET.setEnabled(false);
            locationUpdateET.setEnabled(false);
            updateTV.setText("My Profile");
            changeImgBtn.setVisibility(View.GONE);
            updateBtn.setVisibility(View.GONE);
    }

    public static String getUserName() {
        return userName;
    }
}
