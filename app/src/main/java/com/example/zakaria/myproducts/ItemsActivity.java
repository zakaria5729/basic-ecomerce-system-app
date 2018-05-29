package com.example.zakaria.myproducts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> itemList;
    private String type;
    private String checkItem;
    private TextView productType;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        listView=findViewById(R.id.itemListV);
        productType=findViewById(R.id.productType);
        itemList=new ArrayList<>();
        type=getIntent().getStringExtra("type");
        itemList=getIntent().getStringArrayListExtra("items");

        productType.setText(type);
        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(this,R.layout.item_list_row,R.id.item_row,itemList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                checkItem=adapterView.getItemAtPosition(position).toString();
                intent=new Intent(ItemsActivity.this,PostAdActivity.class);
                intent.putExtra("item",checkItem);
                startActivity(intent);
            }
        });
    }
}
