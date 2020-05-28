package com.example.androidjavaapp1;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class LoadDataFromApi_ListView extends AsyncTask<String, Void, String> {
    private WeakReference<ResultsList> ActivityWeakRef;
    //constructor
    LoadDataFromApi_ListView(ResultsList activity){
        ActivityWeakRef = new WeakReference<ResultsList>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ResultsList activity = ActivityWeakRef.get();
        if(activity==null || activity.isFinishing()){
            return;
        }
    }


    @Override
    protected String doInBackground(String... strings) {
        String returnDataStr ="";
        try {
            URL ApiUrl = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) ApiUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int respCode = conn.getResponseCode();
            if(respCode!=200) {
                throw new RuntimeException("Httpcoderesponse: " + respCode);
            }

            Scanner sc = new Scanner(ApiUrl.openStream());
            while (sc.hasNext()){
                returnDataStr+= sc.nextLine();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return returnDataStr;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ResultsList activity = ActivityWeakRef.get();
        if(activity==null || activity.isFinishing()){
            return;
        }

        try {
            JSONObject jobj = new JSONObject(s);
            JSONArray jArr= jobj.getJSONObject("response").getJSONArray("docs");
            activity.jAdap = new JSON_Adapter(activity, jArr);
            activity.lv_Results.setAdapter(activity.jAdap);
            activity.jArr=jArr;
            activity.hitsInSearch = Integer.parseInt(jobj.getJSONObject("response").getJSONObject("meta").getString("hits"));

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
