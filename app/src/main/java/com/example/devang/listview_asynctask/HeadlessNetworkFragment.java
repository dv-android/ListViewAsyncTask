package com.example.devang.listview_asynctask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HeadlessNetworkFragment extends Fragment implements LoadFeedData.TaskCallback{

    private boolean mDownloading = false;

    public AsyncResponse mAsyncResponse;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setRetainInstance(true);
       Log.d("D :- Fragment","-onCreate");
       //Log.d("HeadlessNetworkFragment","mDownloading = "+mDownloading);

       LoadFeedData loadFeedData = new LoadFeedData();
       loadFeedData.mTaskCallback = this;
       loadFeedData.execute();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d("D :- Fragment","-onActivityCreated");
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        Log.d("D :- Fragment","-onAttach "+activity);
        mAsyncResponse = (AsyncResponse) activity;

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("D :- Fragment","-onStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("D :- Fragment","-onPause");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("D :- Fragment","-onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("D :- Fragment","-onStop");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Log.d("D :- Fragment","-onDetach");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("D :- Fragment","-onDestroy");
    }

    @Override
    public void taskFinish(ArrayList<Object> list){
        mAsyncResponse.processFinish(list);
    }
}
