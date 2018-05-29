package com.example.zakaria.myproducts.models;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zakaria.myproducts.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Others extends AppCompatActivity {

    private StorageReference storageReference;
    private static final int PIC_SELECT = 1;
    private Context context;
    private static LinearLayout linearLayout1;
    private static LinearLayout linearLayout2;
    private static LinearLayout linearLayout3;

    public Others(Context context) {
        this.context = context;
    }

    /*
    public static void shareMyApp(Context context) {
        ApplicationInfo info = context.getApplicationInfo();
        String apkPath = info.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/vnd.android.package-archive");

        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
        context.startActivity(Intent.createChooser(intent, "Share this app using:"));
    }*/

    public void aboutUs() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.about_us_pop_up, null);
        dialogBuilder.setView(view);

         linearLayout1 = view.findViewById(R.id.layout1);
         linearLayout2 = view.findViewById(R.id.layout2);
         linearLayout3 = view.findViewById(R.id.layout3);


        dialogBuilder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public void changeProfileImg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.camera_pop_up, null);
        builder.setView(view);

        linearLayout1 = view.findViewById(R.id.takePhotoLayout);
        linearLayout2 = view.findViewById(R.id.galleryPhotoLayout);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "take", Toast.LENGTH_SHORT).show();
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PIC_SELECT);*/
                Toast.makeText(context, "get", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /*@Override
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
                    Uri downloadUrl = task.getResult();
                }
                else {
                    Toast.makeText(context, "Profile image is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
}
