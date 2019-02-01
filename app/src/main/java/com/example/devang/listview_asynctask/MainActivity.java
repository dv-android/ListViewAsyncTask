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

    private HeadlessNetworkFragment mHeadlessNetworkFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        mHeadlessNetworkFragment = (HeadlessNetworkFragment)getFragmentManager()
                .findFragmentByTag("counter_fragment");
        if(mHeadlessNetworkFragment == null) {
            mHeadlessNetworkFragment = new HeadlessNetworkFragment();
            getFragmentManager().beginTransaction().add(mHeadlessNetworkFragment, "counter_fragment").commit();
        }
        //mHeadlessNetworkFragment.mAsyncResponse = this;
        Log.d("MainActivity", "onCreate called");
        Log.d("MainActivity","Build SDK version is "+ Build.VERSION.SDK_INT);



    }

    public void processFinish(ArrayList<Object> list) {

        Log.d("MainActivity","list is "+list);
        Log.d("MainActivity","process finish list is "+list);
        ListAdapter adapter = new ArrayAdapter<Object>(this,R.layout.listitemlayout,list);
        setListAdapter(adapter);
        final ListView listView;
        listView = getListView();
        listView.setItemChecked(0, true);

    }
}


