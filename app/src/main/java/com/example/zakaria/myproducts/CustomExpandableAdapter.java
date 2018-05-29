package com.example.zakaria.myproducts;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

class CustomExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeaderData;
    private Map<String, List<String>> listChildData;

    public CustomExpandableAdapter(Context context, List<String> listHeaderData, Map<String, List<String>> listChildData) {
        this.context = context;
        this.listHeaderData = listHeaderData;
        this.listChildData = listChildData;
    }

    @Override
    public int getGroupCount() {
        return listHeaderData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listChildData.get(listHeaderData.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listHeaderData.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listChildData.get(listHeaderData.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean isExpand, View view, ViewGroup viewGroup) {
        String headerString = (String) getGroup(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.header_layout, null);
        }
        TextView headerTextView = view.findViewById(R.id.groupTextViewId);
        headerTextView.setText(headerString);

        if (isExpand) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                headerTextView.setTextColor(context.getColor(R.color.colorExpand));
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                headerTextView.setTextColor(context.getColor(android.R.color.primary_text_light));
            }
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String childString = (String) getChild(i, i1);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_layout, null);
        }
        TextView headerTextView = view.findViewById(R.id.childTextViewId);
        headerTextView.setText(childString);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
