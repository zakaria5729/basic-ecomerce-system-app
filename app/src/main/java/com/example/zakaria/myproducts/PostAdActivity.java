package com.example.zakaria.myproducts;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zakaria.myproducts.models.MobileProduct;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class PostAdActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private static final int PIC_IMAGE_REQUEST = 1;

    private TextView productType, conditionTV, deviceTpTV, transmissionTV, newNumberTV, addAnotherNmbTV, addNumbTV, nameTV, emailTV, landTypeTV, landUnitsTV, houseUnitsTV;

    private EditText userNameET, locationET, brandET, modelET, fuelTpET, engineET, kilometersET, modelYrET, titleET, descriptionET, priceET, anotherNumbET, bedsET, bathsET, landsizeET, sizeET, housesizeET, productNameET;

    private Button addPhotoBtn, addPostBtn;

    private TextInputLayout brandEtLayout, modelEtLayout, fuelTpEtLayout, engineEtLayout, kilometersEtLayout, modelYrEtLayout, titleEtLayout, anotherNumbEtLayout, bedsEtLayout, bathsEtLayout, landsizeEtLayout, sizeEtLayout, housesizeEtLayout, productNameEtLayout;

    private RadioGroup conditionRG, deviceTpRG, transmissionRG;
    private RadioButton reconRB;
    private RadioButton radioButton;

    private ImageView imageProductView;
    private Spinner landUitsSP, landTypeSP, houseUitsSP;
    private CheckBox negotiableCB;
    private String checkItem;
    private String selectedCategory;
    private ProgressBar progressBar;

    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ad);

        progressBar = findViewById(R.id.progressBar);
        addPhotoBtn = findViewById(R.id.addPhotoBtn);
        addPostBtn = findViewById(R.id.addPostBtn);
        productType = findViewById(R.id.productType);
        conditionTV = findViewById(R.id.conditionTV);
        deviceTpTV = findViewById(R.id.deviceTpTV);
        transmissionTV = findViewById(R.id.transmissionTV);
        landTypeTV = findViewById(R.id.landTypeTV);
        landUnitsTV = findViewById(R.id.landUnitsTV);
        houseUnitsTV = findViewById(R.id.houseUnitsTV);
        userNameET = findViewById(R.id.userNameET);
        productNameET = findViewById(R.id.productNameET);
        locationET = findViewById(R.id.locationET);
        brandET = findViewById(R.id.brandET);
        modelET = findViewById(R.id.modelET);
        fuelTpET = findViewById(R.id.fuelTpET);
        engineET = findViewById(R.id.engineET);
        kilometersET = findViewById(R.id.kilometersET);
        modelYrET = findViewById(R.id.modelYrET);
        titleET = findViewById(R.id.titleET);
        descriptionET = findViewById(R.id.descriptionET);
        priceET = findViewById(R.id.priceET);
        anotherNumbET = findViewById(R.id.anotherNumbET);
        bedsET = findViewById(R.id.bedsET);
        bathsET = findViewById(R.id.bathsET);
        landsizeET = findViewById(R.id.landsizeET);
        sizeET = findViewById(R.id.sizeET);
        housesizeET = findViewById(R.id.housesizeET);
        imageProductView = findViewById(R.id.imageProdctId);
        conditionRG = findViewById(R.id.conditionRG);
        deviceTpRG = findViewById(R.id.deviceTpRG);
        transmissionRG = findViewById(R.id.transmissionRG);
        reconRB = findViewById(R.id.recommRB);
        negotiableCB = findViewById(R.id.negotiableCB);
        landUitsSP = findViewById(R.id.landUitsSP);
        landTypeSP = findViewById(R.id.landTypeSP);
        houseUitsSP = findViewById(R.id.houseUitsSP);

        brandEtLayout = findViewById(R.id.brandEtLayout);
        modelEtLayout = findViewById(R.id.modelEtLayout);
        fuelTpEtLayout = findViewById(R.id.fuelTpEtLayout);
        engineEtLayout = findViewById(R.id.engineEtLayout);
        kilometersEtLayout = findViewById(R.id.kilometersEtLayout);
        modelYrEtLayout = findViewById(R.id.modelYrEtLayout);
        titleEtLayout = findViewById(R.id.titleEtLayout);
        anotherNumbEtLayout = findViewById(R.id.anotherNumbEtLayout);
        bedsEtLayout = findViewById(R.id.bedsEtLayout);
        bathsEtLayout = findViewById(R.id.bathsEtLayout);
        landsizeEtLayout = findViewById(R.id.landsizeEtLayout);
        sizeEtLayout = findViewById(R.id.sizeEtLayout);
        housesizeEtLayout = findViewById(R.id.housesizeEtLayout);
        //productNameEtLayout = findViewById(R.id.productNameEtLayout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference("product_image");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("product_list");

        checkItem = getIntent().getStringExtra("item");
        productType.setText(checkItem);

        if (checkItem.equals("Mobile Phones")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            brandEtLayout.setVisibility(View.VISIBLE);
            modelEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Mobile Phone Accessories")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            titleEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Computers & Tablets")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            deviceTpTV.setVisibility(View.VISIBLE);
            deviceTpRG.setVisibility(View.VISIBLE);
            brandEtLayout.setVisibility(View.VISIBLE);
            modelEtLayout.setVisibility(View.VISIBLE);
            titleEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("TVs")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            brandEtLayout.setVisibility(View.VISIBLE);
            modelEtLayout.setVisibility(View.VISIBLE);
            titleEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Motorbikes & Scooters")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            brandEtLayout.setVisibility(View.VISIBLE);
            modelEtLayout.setVisibility(View.VISIBLE);
            modelYrEtLayout.setVisibility(View.VISIBLE);
            engineEtLayout.setVisibility(View.VISIBLE);
            kilometersEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Cars")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            reconRB.setVisibility(View.VISIBLE);
            brandEtLayout.setVisibility(View.VISIBLE);
            modelEtLayout.setVisibility(View.VISIBLE);
            modelYrEtLayout.setVisibility(View.VISIBLE);
            transmissionTV.setVisibility(View.VISIBLE);
            transmissionRG.setVisibility(View.VISIBLE);
            fuelTpEtLayout.setVisibility(View.VISIBLE);
            engineEtLayout.setVisibility(View.VISIBLE);
            kilometersEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Trucks, Vans & Buses")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            reconRB.setVisibility(View.VISIBLE);
            brandEtLayout.setVisibility(View.VISIBLE);
            modelEtLayout.setVisibility(View.VISIBLE);
            modelYrEtLayout.setVisibility(View.VISIBLE);
            kilometersEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Apartment & Flats")) {
            bedsEtLayout.setVisibility(View.VISIBLE);
            bathsEtLayout.setVisibility(View.VISIBLE);
            sizeEtLayout.setVisibility(View.VISIBLE);
            titleEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Houses")) {
            bedsEtLayout.setVisibility(View.VISIBLE);
            bathsEtLayout.setVisibility(View.VISIBLE);
            landsizeEtLayout.setVisibility(View.VISIBLE);
            landUnitsTV.setVisibility(View.VISIBLE);
            landUitsSP.setVisibility(View.VISIBLE);
            housesizeEtLayout.setVisibility(View.VISIBLE);
            houseUnitsTV.setVisibility(View.VISIBLE);
            houseUitsSP.setVisibility(View.VISIBLE);
            titleEtLayout.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Plots & Land")) {
            landTypeTV.setVisibility(View.VISIBLE);
            landTypeSP.setVisibility(View.VISIBLE);
            landsizeET.setVisibility(View.VISIBLE);
            landUnitsTV.setVisibility(View.VISIBLE);
            landUitsSP.setVisibility(View.VISIBLE);
            titleEtLayout.setVisibility(View.VISIBLE);
        }

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.land_type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landTypeSP.setAdapter(typeAdapter);
        landTypeSP.setSelection(2);
        landTypeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PostAdActivity.this, "" + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> HouseUnitsAdapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        HouseUnitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        houseUitsSP.setAdapter(HouseUnitsAdapter);
        houseUitsSP.setSelection(2);
        houseUitsSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PostAdActivity.this, "" + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> LandUnitsAdapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        LandUnitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landUitsSP.setAdapter(LandUnitsAdapter);
        landUitsSP.setSelection(2);
        landUitsSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PostAdActivity.this, "" + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFIleChooser();
            }
        });

        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(PostAdActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadProduct();
                }
            }
        });
    }

    public void addNewNumb(View view) {
        newNumberTV.setVisibility(View.VISIBLE);
        newNumberTV.setText(anotherNumbET.getText().toString());
        addAnotherNmbTV.setVisibility(View.GONE);
        anotherNumbET.setVisibility(View.GONE);
        addNumbTV.setVisibility(View.GONE);
    }

    public void postAd(View view) {
    }

    public void addAnotherNmb(View view) {
        anotherNumbEtLayout.setVisibility(View.VISIBLE);
        addNumbTV.setVisibility(View.VISIBLE);
    }

    private void openFIleChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PIC_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PIC_IMAGE_REQUEST && resultCode == RESULT_OK && data.getData() != null && data != null) {
            mImageUri = data.getData();

            Glide.with(this).
                    load(mImageUri)
                    .override(300, 800)
                    .into(imageProductView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mine = MimeTypeMap.getSingleton();
        return mine.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadProduct() {
            if (checkItem.equals("Mobile Phones")) {
                selectedCategory = "Mobile Phones";
                uploadSelectedCategoryWise(selectedCategory);
            }

            if (checkItem.equals("Mobile Phone Accessories")) {
                selectedCategory = "Mobile Phone Accessories";
                uploadSelectedCategoryWise(selectedCategory);
            }

            if (checkItem.equals("Computers & Tablets")) {
                selectedCategory = "Computers & Tablets";
                uploadSelectedCategoryWise(selectedCategory);
            }

            if (checkItem.equals("TVs")) {
                selectedCategory = "TVs";
                uploadSelectedCategoryWise(selectedCategory);
            }

            if (checkItem.equals("Motorbikes & Scooters")) {
                selectedCategory = "Motorbikes & Scooters";
                uploadSelectedCategoryWise(selectedCategory);
            }

            if (checkItem.equals("Cars")) {
                selectedCategory = "Cars";
                uploadSelectedCategoryWise(selectedCategory);
            }

            if (checkItem.equals("Trucks, Vans & Buses")) {
                selectedCategory = "Trucks, Vans & Buses";
            }

            if (checkItem.equals("Apartment & Flats")) {
                selectedCategory = "Apartment & Flats";
                uploadSelectedCategoryWise(selectedCategory);
            }

            if (checkItem.equals("Houses")) {
                selectedCategory = "Houses";
                uploadSelectedCategoryWise(selectedCategory);
            }

            if (checkItem.equals("Plots & Land")) {
                selectedCategory = "Plots & Land";
                uploadSelectedCategoryWise(selectedCategory);
            }
    }

    public String getCurrentDateAndTime() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm a");
        Date date = new Date();
        return String.valueOf(dateFormat.format(date));
    }

    private String getCondition() {
        int seletedId = conditionRG.getCheckedRadioButtonId();
        radioButton = findViewById(seletedId);
        return radioButton.getText().toString();
    }

    public void uploadSelectedCategoryWise(final String category) {
        Toast.makeText(this, "up click", Toast.LENGTH_SHORT).show();
        Log.e("up_tag", "up_click");

        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));

            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    }, 4000);
                    Toast.makeText(PostAdActivity.this, "Upload Successful img", Toast.LENGTH_SHORT).show();

                    String price;
                    String userName = userNameET.getText().toString();
                    String productName = productNameET.getText().toString();
                    String location = locationET.getText().toString();
                    String productCondition = getCondition();
                    String brand = brandET.getText().toString();
                    String model = modelET.getText().toString();
                    String description = descriptionET.getText().toString();
                    String posted = getCurrentDateAndTime();
                    String phoneNumbe = anotherNumbET.getText().toString();
                    String imageUrl = taskSnapshot.getDownloadUrl().toString();

                    if (negotiableCB.isChecked()) {
                        price = "\u09F3 " + priceET.getText().toString() + " (" + negotiableCB.getText().toString() + ")";
                    } else {
                        price = "\u09F3 " + priceET.getText().toString();
                    }

                    String upLoadId = mDatabaseRef.push().getKey();
                    String getUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    MobileProduct mobileProduct = new MobileProduct(userName, productName, location, productCondition, brand, model, category, description, posted, phoneNumbe, price, imageUrl, upLoadId);

                    mDatabaseRef.child(getUserId).child(upLoadId).setValue(mobileProduct);
                    Toast.makeText(PostAdActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PostAdActivity.this, ProductListActivity.class));

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PostAdActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);
                    progressBar.isShown();
                }
            });
        }
        else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_LONG).show();
        }
    }
}
