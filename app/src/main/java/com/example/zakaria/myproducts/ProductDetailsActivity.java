package com.example.zakaria.myproducts;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView companyNameTextView, productNameTextView, priceTextView;
    private boolean isFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        companyNameTextView = findViewById(R.id.companyNameDetails);
        productNameTextView = findViewById(R.id.productNameDetails);
        priceTextView = findViewById(R.id.priceDetails);

        String companyName = getIntent().getStringExtra("company_name");
        String productName = getIntent().getStringExtra("product_name");
        String price = getIntent().getStringExtra("price");

        companyNameTextView.setText(companyName);
        productNameTextView.setText(productName);
        priceTextView.setText(price);
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
}

