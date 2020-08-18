package com.example.swc_project;

import android.content.Context;

import java.util.ArrayList;

public class CustomAdapter {
    private ArrayList<userdata> arrayList;
    private Context context;


    public CustomAdapter(ArrayList<userdata> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
}
