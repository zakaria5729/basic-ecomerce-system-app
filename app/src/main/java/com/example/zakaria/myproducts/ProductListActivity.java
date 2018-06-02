package com.example.zakaria.myproducts;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zakaria.myproducts.models.MobileProduct;
import com.example.zakaria.myproducts.models.Others;
import com.example.zakaria.myproducts.models.UpdateProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class ProductListActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabaseRef;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<MobileProduct> productList;
    private AllProductAdapter allProductAdapter;

    private Button signUpButton, phoneVerifyButton;
    private TextView login, close, noDataFoundTV;
    private boolean isAddProductButton;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        FloatingTextButton addProductBtn = findViewById(R.id.postAddBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recylerAllV);
        noDataFoundTV = findViewById(R.id.noDataFoundTV);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(ProductListActivity.this, 2));
        productList = new ArrayList<>();

        if (firebaseAuth.getCurrentUser() != null) {
            noDataFoundTV.setVisibility(View.GONE);

            mDatabaseRef = FirebaseDatabase.getInstance().getReference("product_list").child(firebaseAuth.getCurrentUser().getUid());

            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            MobileProduct mobileProduct = postSnapshot.getValue(MobileProduct.class);
                            productList.add(mobileProduct);
                        }

                        allProductAdapter = new AllProductAdapter(ProductListActivity.this, productList);
                        recyclerView.setAdapter(allProductAdapter);

                    }
                    else {
                        Toast.makeText(ProductListActivity.this, "No data found", Toast.LENGTH_LONG).show();
                        noDataFoundTV.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ProductListActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            noDataFoundTV.setVisibility(View.VISIBLE);
        }

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(ProductListActivity.this, ProductAddActivity.class));
                    finish();
                } else {
                    isAddProductButton = true;
                    getSignUpFloatingAction();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        if (firebaseAuth.getCurrentUser() == null) {
            menu.findItem(R.id.logout).setVisible(false);
            menu.findItem(R.id.myAccount).setVisible(false);
            menu.findItem(R.id.settings).setVisible(false);
            menu.findItem(R.id.accountIconWithEmailOrPhone).setVisible(false);

            menu.findItem(R.id.accountIcon).setIcon(R.drawable.ic_account_circle_black_24dp);
            menu.findItem(R.id.accountIcon).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    isAddProductButton = false;
                    getSignUpFloatingAction();
                    return true;
                }
            });
        } else {
            String userEmail = firebaseAuth.getCurrentUser().getEmail();
            for (int i = 0; i < userEmail.length(); i++) {
                if (userEmail.charAt(i) != '@') {
                    userName += userEmail.charAt(i);
                }
                else if (userEmail.charAt(i) == '@') {
                    break;
                }
            }

            menu.findItem(R.id.logout).setVisible(true);
            menu.findItem(R.id.myAccount).setVisible(true);
            menu.findItem(R.id.accountIconWithEmailOrPhone).setVisible(true);

            menu.findItem(R.id.accountIcon).setIcon(R.drawable.user);
            menu.findItem(R.id.accountIconWithEmailOrPhone).setIcon(R.drawable.user);
            menu.findItem(R.id.accountIconWithEmailOrPhone).setTitle(userName);
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
                startActivity(new Intent(ProductListActivity.this, ProductListActivity.class));
                finish();
            }
        } else if (id == R.id.shareApp) {
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.faq) {
            startActivity(new Intent(ProductListActivity.this, FaqActivity.class));
        } else if (id == R.id.about) {
            Others others = new Others(this);
            others.aboutUs();
        } else if (id == R.id.send) {
            startActivity(new Intent(this, EmailActivity.class));
        } else if (id == R.id.updateProfile) {
            startActivity(new Intent(ProductListActivity.this, UpdateProfileActivity.class));
        } else if (id == R.id.changePassword) {
            startActivity(new Intent(ProductListActivity.this, PasswordChangeActivity.class).putExtra("password_key", "change_password"));
        }
        return super.onOptionsItemSelected(item);
    }

    public void getSignUpFloatingAction() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.sign_up_popup, null);
        builder.setView(view);

        signUpButton = view.findViewById(R.id.signUpPopUpId);
        phoneVerifyButton = view.findViewById(R.id.phoneVerifyPopUpId);
        login = view.findViewById(R.id.loginPopUpId);
        close = view.findViewById(R.id.closePopUpId);

        final AlertDialog alertDialog = builder.create();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAddProductButton) {
                    startActivity(new Intent(ProductListActivity.this, SignUpActivity.class).putExtra("sign_up_key", "login_for_add_product"));
                }
                else {
                    startActivity(new Intent(ProductListActivity.this, SignUpActivity.class).putExtra("sign_up_key", "login_for_my_account"));
                }
                alertDialog.dismiss();
            }
        });
        phoneVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductListActivity.this, "phone", Toast.LENGTH_SHORT).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAddProductButton) {
                    startActivity(new Intent(ProductListActivity.this, LoginActivity.class).putExtra("login_key", "login_for_add_product"));
                }
                else {
                    startActivity(new Intent(ProductListActivity.this, LoginActivity.class).putExtra("login_key", "login_for_my_account"));
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
