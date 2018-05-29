package com.example.zakaria.myproducts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PostAdActivity extends AppCompatActivity {

    private TextView productType, conditionTV, deviceTpTV, transmissionTV, newNumberTV, addAnotherNmbTV, addNumbTV, nameTV, emailTV, landTypeTV, landUnitsTV, houseUnitsTV;

    private EditText locationET, brandET, modelET, fuelTpET, engineET, kilometersET, modelYrET, titleET, descriptionET, priceET, anotherNumbET, bedsET, bathsET, landsizeET, sizeET, housesizeET;

    private RadioGroup conditionRG, deviceTpRG, transmissionRG;
    private RadioButton reconRB;

    private ImageView imageProdct;
    private Spinner landUitsSP, landTypeSP, houseUitsSP;
    private CheckBox negotiableCB;
    private String checkItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ad);

        productType = findViewById(R.id.productType);
        conditionTV = findViewById(R.id.conditionTV);
        deviceTpTV = findViewById(R.id.deviceTpTV);
        transmissionTV = findViewById(R.id.transmissionTV);
        newNumberTV = findViewById(R.id.newNumberTV);
        addAnotherNmbTV = findViewById(R.id.addAnotherNmbTV);
        addNumbTV = findViewById(R.id.addNumbTV);
        nameTV = findViewById(R.id.nameTV);
        emailTV = findViewById(R.id.emailTV);
        landTypeTV = findViewById(R.id.landTypeTV);
        landUnitsTV = findViewById(R.id.landUnitsTV);
        houseUnitsTV = findViewById(R.id.houseUnitsTV);
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
        imageProdct = findViewById(R.id.imageProdct);
        conditionRG = findViewById(R.id.conditionRG);
        deviceTpRG = findViewById(R.id.deviceTpRG);
        transmissionRG = findViewById(R.id.transmissionRG);
        reconRB = findViewById(R.id.reconRB);
        negotiableCB = findViewById(R.id.negotiableCB);
        landUitsSP = findViewById(R.id.landUitsSP);
        landTypeSP = findViewById(R.id.landTypeSP);
        houseUitsSP = findViewById(R.id.houseUitsSP);

        checkItem = getIntent().getStringExtra("item");
        productType.setText(checkItem);

        if (checkItem.equals("Mobile Phones")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            brandET.setVisibility(View.VISIBLE);
            modelET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Mobile Phone Accessories")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            titleET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Computers & Tablets")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            deviceTpTV.setVisibility(View.VISIBLE);
            deviceTpRG.setVisibility(View.VISIBLE);
            brandET.setVisibility(View.VISIBLE);
            modelET.setVisibility(View.VISIBLE);
            titleET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("TVs")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            brandET.setVisibility(View.VISIBLE);
            modelET.setVisibility(View.VISIBLE);
            titleET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Motorbikes & Scooters")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            brandET.setVisibility(View.VISIBLE);
            modelET.setVisibility(View.VISIBLE);
            modelYrET.setVisibility(View.VISIBLE);
            engineET.setVisibility(View.VISIBLE);
            kilometersET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Cars")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            reconRB.setVisibility(View.VISIBLE);
            brandET.setVisibility(View.VISIBLE);
            modelET.setVisibility(View.VISIBLE);
            modelYrET.setVisibility(View.VISIBLE);
            transmissionTV.setVisibility(View.VISIBLE);
            transmissionRG.setVisibility(View.VISIBLE);
            fuelTpET.setVisibility(View.VISIBLE);
            engineET.setVisibility(View.VISIBLE);
            kilometersET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Trucks, Vans & Buses")) {
            conditionTV.setVisibility(View.VISIBLE);
            conditionRG.setVisibility(View.VISIBLE);
            reconRB.setVisibility(View.VISIBLE);
            brandET.setVisibility(View.VISIBLE);
            modelET.setVisibility(View.VISIBLE);
            modelYrET.setVisibility(View.VISIBLE);
            kilometersET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Apartment & Flats")) {
            bedsET.setVisibility(View.VISIBLE);
            bathsET.setVisibility(View.VISIBLE);
            sizeET.setVisibility(View.VISIBLE);
            titleET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Houses")) {
            bedsET.setVisibility(View.VISIBLE);
            bathsET.setVisibility(View.VISIBLE);
            landsizeET.setVisibility(View.VISIBLE);
            landUnitsTV.setVisibility(View.VISIBLE);
            landUitsSP.setVisibility(View.VISIBLE);
            housesizeET.setVisibility(View.VISIBLE);
            houseUnitsTV.setVisibility(View.VISIBLE);
            houseUitsSP.setVisibility(View.VISIBLE);
            titleET.setVisibility(View.VISIBLE);
        }

        if (checkItem.equals("Plots & Land")) {
            landTypeTV.setVisibility(View.VISIBLE);
            landTypeSP.setVisibility(View.VISIBLE);
            landsizeET.setVisibility(View.VISIBLE);
            landUnitsTV.setVisibility(View.VISIBLE);
            landUitsSP.setVisibility(View.VISIBLE);
            titleET.setVisibility(View.VISIBLE);
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

    }


    public void addPhoto(View view) {
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
        anotherNumbET.setVisibility(View.VISIBLE);
        addNumbTV.setVisibility(View.VISIBLE);
    }
}
