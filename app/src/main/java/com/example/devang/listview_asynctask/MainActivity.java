package com.example.devang.listview_asynctask;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends ListActivity implements AsyncResponse {

    private HeadlessNetworkFragment mHeadlessNetworkFragment;

    private ListAdapter adapter;

    ArrayList<Object> arrayList;

    Parcelable state;

    String listState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("D :- MainActivity", "onCreate");
        mHeadlessNetworkFragment = (HeadlessNetworkFragment)getFragmentManager()
                .findFragmentByTag("counter_fragment");
        if(mHeadlessNetworkFragment == null) {
            Log.d("D:- Main Activity ","else part executed");
            mHeadlessNetworkFragment = new HeadlessNetworkFragment();
            getFragmentManager().beginTransaction().add(mHeadlessNetworkFragment, "counter_fragment").commit();
        }

        //Log.d("MainActivity","Build SDK version is "+ Build.VERSION.SDK_INT);



    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("D :- Main Activity","-onStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("D :- Main Activity","-onPause");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("D :- Main Activity","-onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("D :- Main Activity","-onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("D :- Main Activity","-onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
       super.onSaveInstanceState(outState);
       state =  getListView().onSaveInstanceState();
       Log.d("D :- Main Activity","-onSaveInstaceState "+state);
       outState.putParcelable("listViewState",state);
       outState.putSerializable("ARRAYLIST",(Serializable) arrayList);
    }

    @Override
    public void onRestoreInstanceState(Bundle bundleState){
        state = bundleState.getParcelable("listViewState");
        ArrayList<Object> arrayList = (ArrayList<Object>)bundleState.getSerializable("ARRAYLIST");
        adapter = new ArrayAdapter<Object>(this,R.layout.listitemlayout,arrayList);
        setListAdapter(adapter);
         Log.d("D :- Main Activity","-onRestoreInstanceState  "+state);
        Log.d("D :- Main Activity","-onRestoreInstanceState  listview is"+getListView());
        getListView().onRestoreInstanceState(state);
        Log.d("D :- Main Activity","-onRestoreInstanceState  listview is"+getListView());
    }

    public void processFinish(ArrayList<Object> list) {

        Log.d("D :- Main Activity","processFinish");
        //Log.d("MainActivity","list is "+list);
        //Log.d("MainActivity","process finish list is "+list);
        arrayList =list;
        adapter = new ArrayAdapter<Object>(this,R.layout.listitemlayout,list);
        setListAdapter(adapter);
        final ListView listView;
        listView = getListView();
        listView.setItemChecked(0, true);

    }
}


