package com.example.devang.listview_asynctask;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoadLocalData extends AsyncTask<Void,Void,String> {

    private final String localUrl = "http://10.0.2.2:9999/ClinicSoln/cliniclogin/dologin";
    private final String echoUrl = "http://10.0.2.2:9999/ClinicSoln/echo";

    public String result , resultGet;

    public LoadLocalData(){

    }

    @Override
    protected String doInBackground(Void... params){

        InputStream stream = null , getStream;
        HttpURLConnection connection = null;
        HttpURLConnection getConnection = null;
        result = null;
        String loginData = "username=devang&password=devang";
        try{
            URL url = new URL(localUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            byte[] outputInBytes = loginData.getBytes("UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write( outputInBytes );
            os.close();
            connection.connect();
            int resCode = connection.getResponseCode();
            String bearerToken = connection.getHeaderField("authorization");
            Log.d("LoadLocalData","response code ="+resCode);
            Log.d("LoadLocalData","bearer token is ="+bearerToken);

            if (resCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + resCode);
            }
            stream = connection.getInputStream();

            Log.d("LoadLocalData","stream is  ="+stream);

            if (stream!=null){
                result = readStream(stream,5000);
            }
            URL urlForEcho = new URL(echoUrl);
            getConnection = (HttpURLConnection) urlForEcho.openConnection();
            getConnection.setReadTimeout(3000);
            getConnection.setConnectTimeout(3000);
            getConnection.setRequestMethod("GET");
            getConnection.setDoInput(true);
            getConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            getConnection.setRequestProperty("Authorization", bearerToken);
            Log.d("LoadLocalData","request header is"+getConnection.getRequestProperty("Authorization"));
            getConnection.connect();
            getStream = getConnection.getInputStream();
            resultGet = readStream(getStream , 5000);
            resCode = getConnection.getResponseCode();
            Log.d("LoadLocalData","get rest service response code is "+resCode);
        }
        catch(Exception e){
            Log.d("LoadLocalData","Exception is"+e);
        }

        return resultGet;
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
