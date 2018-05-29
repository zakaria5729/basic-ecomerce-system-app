package com.example.zakaria.myproducts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaqActivity extends AppCompatActivity {

    private ExpandableListView faqExpandableLitView;
    private List<String> listHeaderData;
    private Map<String, List<String>> listChildData;
    private Map<String, List<String>> listTestData;
    private CustomExpandableAdapter customExpandableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        faqExpandableLitView = findViewById(R.id.faqExpandableLitViewId);
        prepareListData();

        customExpandableAdapter = new CustomExpandableAdapter(this, listHeaderData, listChildData);
        faqExpandableLitView.setAdapter(customExpandableAdapter);
    }

    public void prepareListData() {
        List<String> child;

        String[] faqHeaderString = getResources().getStringArray(R.array.faq_header);
        String[] faqChildString = getResources().getStringArray(R.array.faq_child);

        listHeaderData = new ArrayList<>();
        listChildData = new HashMap<>();

        for (int i=0; i<faqHeaderString.length; i++) {
            listHeaderData.add(faqHeaderString[i]);

            child = new ArrayList<>();
            child.add(faqChildString[i]);

            listChildData.put(listHeaderData.get(i), child);
        }
    }
}
