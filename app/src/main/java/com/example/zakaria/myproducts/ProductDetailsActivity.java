package com.example.zakaria.myproducts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView companyNameTextView, productNameTextView, priceTextView, modelTextView, brandTextView, categoryTextView, locationTextView, conditionTextView, postedTextView;
    private ImageView productImageDetails;
    private Button callNowBtn;
    private boolean isFavourite;
    private String number;
    private static final int REQUEST_CODE_FOR_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        companyNameTextView = findViewById(R.id.companyNameDetails);
        productNameTextView = findViewById(R.id.productNameDetails);
        priceTextView = findViewById(R.id.priceDetails);
        modelTextView = findViewById(R.id.modelTV);
        brandTextView = findViewById(R.id.brandTV);
        categoryTextView = findViewById(R.id.categoryTV);
        locationTextView = findViewById(R.id.locationTV);
        conditionTextView = findViewById(R.id.conditionsTV);
        postedTextView = findViewById(R.id.postedTV);
        callNowBtn = findViewById(R.id.callNowBtn);
        productImageDetails = findViewById(R.id.productImageDetails);

        String companyName = getIntent().getStringExtra("company_name");
        String productName = getIntent().getStringExtra("product_name");
        String model = getIntent().getStringExtra("product_model");
        String brand = getIntent().getStringExtra("product_brand");
        String category = getIntent().getStringExtra("product_category");
        String condition = getIntent().getStringExtra("product_condition");
        String location = getIntent().getStringExtra("product_location");
        String posted_time = getIntent().getStringExtra("product_posted_time");
        String price = getIntent().getStringExtra("product_price");
        String phoneNumber = getIntent().getStringExtra("product_phone_number");
        String imageUrl = getIntent().getStringExtra("product_image_url");

        companyNameTextView.setText(companyName);
        priceTextView.setText(price);
        productNameTextView.setText(productName);
        modelTextView.setText(model);
        brandTextView.setText(brand);
        categoryTextView.setText(category);
        conditionTextView.setText(condition);
        locationTextView.setText(location);
        postedTextView.setText(posted_time);
        Glide.with(this).
                load(imageUrl)
                .override(300, 800)
                .into(productImageDetails);

        callNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNow();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favourite_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.favorite_item) {
            if (isFavourite == false) {
                Toast.makeText(this, "Added to favourite", Toast.LENGTH_SHORT).show();
                item.setIcon(R.drawable.ic_favorite_black_24dp);
                isFavourite = true;
            }
            else {
                item.setIcon(R.drawable.ic_favorite_border_black_24dp);
                isFavourite = false;
            }
        }
        else if (id == R.id.shareApp) {
            shareApp();
        }
        return super.onOptionsItemSelected(item);
    }

    public void shareApp() {
        ApplicationInfo info = getApplicationContext().getApplicationInfo();
        String apkPath = info.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/vnd.android.package-archive");

        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
        startActivity(Intent.createChooser(intent, "Share Kenakata.com Using:"));
    }

    public void callNow() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_FOR_PHONE_CALL);
        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_FOR_PHONE_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callNow();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

