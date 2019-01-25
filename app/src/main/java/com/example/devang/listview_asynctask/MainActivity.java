package com.example.devang.listview_asynctask;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ListActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        Log.d("MainActivity", "onCreate called");
        Log.d("MainActivity","Build SDK version is "+ Build.VERSION.SDK_INT);
        LoadLocalData loadLocalData = new LoadLocalData();
        loadLocalData.execute();

        LoadFeedData loadFeedData = new LoadFeedData();
        loadFeedData.delegate = this;
        loadFeedData.execute();


    }

    @Override
    public void processFinish(ArrayList<Object> list) {
        Log.d("MainActivity","list is "+list);
        ListAdapter adapter = new ArrayAdapter<Object>(this,R.layout.listitemlayout,list);
        setListAdapter(adapter);
        final ListView listView;
        listView = getListView();
        listView.setItemChecked(0, true);
        /**listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("MainActivity","checkBox clicked");

            }
        }); */
    }
}


