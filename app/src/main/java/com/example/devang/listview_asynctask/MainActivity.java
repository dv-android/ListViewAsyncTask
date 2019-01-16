package com.example.devang.listview_asynctask;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;


public class MainActivity extends ListActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadFeedData loadFeedData = new LoadFeedData();
        loadFeedData.delegate = this;
        loadFeedData.execute();


    }

    @Override
    public void processFinish(ArrayList<Object> list) {
        Log.d("MainActivity","list is "+list);
        ListAdapter adapter = new ArrayAdapter<Object>(this,android.R.layout.simple_list_item_1,list);
        setListAdapter(adapter);
    }
}


