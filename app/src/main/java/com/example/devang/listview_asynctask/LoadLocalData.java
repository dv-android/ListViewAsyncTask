package com.example.devang.listview_asynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoadLocalData extends AsyncTask<Void,Void,String> {

    private final String localUrl = "http://10.0.2.2:9999/ClinicSoln/echo/getPatientDetail";

    public String result;

    public LoadLocalData(){

    }

    @Override
    protected String doInBackground(Void... params){

        InputStream stream = null;
        HttpURLConnection connection = null;
        result = null;
        try{
            URL url = new URL(localUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int resCode = connection.getResponseCode();

            Log.d("LoadLocalData","response code ="+resCode);

            if (resCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + resCode);
            }
            stream = connection.getInputStream();

            Log.d("LoadLocalData","stream is  ="+stream);

            if (stream!=null){
                result = readStream(stream,5000);
            }
        }
        catch(Exception e){
            Log.d("LoadLocalData","Exception is"+e);
        }

        return "dfjds";
    }

    public String readStream(InputStream stream, int maxLength) throws Exception{
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[maxLength];
        int readSize;
        StringBuffer buffer = new StringBuffer();

        while (((readSize = reader.read(rawBuffer)) != -1) && maxLength > 0) {

            if (readSize > maxLength) {
                readSize = maxLength;
            }

            buffer.append(rawBuffer, 0, readSize);
            maxLength -= readSize;

        }
        String conString = buffer.toString();
        Log.d("LoadLocalData","final op string is"+conString);

        return conString;
    }

    protected void onPostExecute(String output){
        Log.d("LoadLocalData","ouput is "+output);

    }
}
