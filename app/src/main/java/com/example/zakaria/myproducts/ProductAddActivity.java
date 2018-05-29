package com.example.zakaria.myproducts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.zakaria.myproducts.models.Others;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductAddActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void electItem(View view) {
        arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("Mobile Phones", "Mobile Phone Accessories", "Computers & Tablets", "TVs"));
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("type", "Electronics");
        intent.putStringArrayListExtra("items", arrayList);
        startActivity(intent);
    }

    public void carItem(View view) {
        arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("Motorbikes & Scooters", "Cars", "Trucks, Vans & Buses"));
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("type", "Cars & Vehicles");
        intent.putStringArrayListExtra("items", arrayList);
        startActivity(intent);
    }

    public void propertyItem(View view) {
        arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("Apartment & Flats", "Houses", "Plots & Land"));
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("type", "Property");
        intent.putStringArrayListExtra("items", arrayList);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        if (firebaseAuth.getCurrentUser() == null) {
            menu.findItem(R.id.logout).setVisible(false);
            menu.findItem(R.id.myAccount).setVisible(false);
            menu.findItem(R.id.settings).setVisible(false);

            menu.findItem(R.id.accountIcon).setIcon(R.drawable.ic_account_circle_black_24dp);
            menu.findItem(R.id.accountIcon).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    return true;
                }
            });
        } else {
            menu.findItem(R.id.logout).setVisible(true);
            menu.findItem(R.id.myAccount).setVisible(true);

            menu.findItem(R.id.accountIcon).setIcon(R.drawable.user);
            menu.findItem(R.id.myAccount).setIcon(R.drawable.user);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.myAccount) {
            Toast.makeText(this, "my", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.logout) {
            if (firebaseAuth.getCurrentUser() != null) {
                firebaseAuth.signOut();
                Toast.makeText(this, "Log out successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProductAddActivity.this, ProductListActivity.class));
                finish();
            }
        } else if (id == R.id.shareApp) {
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.faq) {
            startActivity(new Intent(ProductAddActivity.this, FaqActivity.class));
        } else if (id == R.id.about) {
            Others others = new Others(this);
            others.aboutUs();
        } else if (id == R.id.send) {
            startActivity(new Intent(this, EmailActivity.class));
        } else if (id == R.id.updateProfile) {
            startActivity(new Intent(ProductAddActivity.this, UpdateProfileActivity.class));
        } else if (id == R.id.changePassword) {
            startActivity(new Intent(ProductAddActivity.this, PasswordChangeActivity.class).putExtra("password_key", "change_password"));
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onBackPressed() {
        startActivity(new Intent(ProductAddActivity.this, ProductListActivity.class));
        finish();
        super.onBackPressed();
    }*/
}
