package com.example.devang.listview_asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;


import javax.net.ssl.HttpsURLConnection;

public class LoadFeedData extends AsyncTask <Void,Void,String>{

    private final String mUrl =
            "https://picasaweb.google.com/data/feed/api/all?kind=photo&q=" +
            "sunset%20landscape&alt=json&max-results=20&thumbsize=144c";

    private final String gitUrl = "https://api.github.com/users/dv-android";
   // private final ImageListAdapter mAdapter;

    //public LoadFeedData(ImageListAdapter adapter) {
      //  mAdapter = adapter;
    //}
    public LoadFeedData(){}



    @Override
    protected String doInBackground(Void... params) {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        Log.i("LoadFeedData","inside doInBackground");
        try {
            Log.i("LoadFeedData","inside doInBackground 1");
            URL url = new URL(mUrl);
            Log.i("LoadFeedData","inside doInBackground 2");
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            Log.i("LoadFeedData","inside doInBackground 3");
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            Log.i("LoadFeedData","inside doInBackground 4");
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            Log.i("LoadFeedData","inside doInBackground 5");
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            Log.i("LoadFeedData","inside doInBackground 6");
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            Log.i("LoadFeedData","inside doInBackground 7");
            connection.connect();
            Log.i("LoadFeedData","inside doInBackground 8");
            int responseCode = connection.getResponseCode();
            Log.i("LoadFeedData","inside doInBackground 9 response code ="+responseCode);
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                Log.i("LoadFeedData","inside doInBackground 10 connection not established"+responseCode);
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            Log.i("LoadFeedData","inside doInBackground 10");
            stream = connection.getInputStream();
            Log.i("LoadFeedData","inside doInBackground 11");
            Log.i("LoadFeedData","Stream inside"+stream );
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readStream(stream, 500);
                Log.i("LoadFeedData","Result inside"+result );
            }

        }
        catch(Exception e){
            Log.i("LoadFeedData","Exception is "+e);
        }
        finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                //stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    public String readStream(InputStream stream, int maxReadSize)
            throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[maxReadSize];
        int readSize;
        StringBuffer buffer = new StringBuffer();
        while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
            if (readSize > maxReadSize) {
                readSize = maxReadSize;
            }
            buffer.append(rawBuffer, 0, readSize);
            maxReadSize -= readSize;
        }
        return buffer.toString();
    }

    protected void onPostExecute(String result) {
        Log.d("LoadFeedData","Result is "+result);
    }

}
