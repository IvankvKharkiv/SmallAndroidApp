package com.example.androidjavaapp1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON_Adapter extends BaseAdapter {
    Activity mActivity;
    JSONArray jArr;

    public JSON_Adapter(Activity mActivity, JSONArray jArr) {
        this.mActivity = mActivity;
        this.jArr = jArr;
    }

    @Override
    public int getCount() {
        return jArr.length();
    }

    @Override
    public JSONObject getItem(int position) {
        JSONObject jsonObject = null;
        try {
            jsonObject = jArr.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View One_Line_Result;
        LayoutInflater LInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        One_Line_Result = LInflater.inflate(R.layout.adapter_view_layout, parent, false);

        TextView tvUrl = One_Line_Result.findViewById(R.id.tv_Url);
        TextView tvSnippet = One_Line_Result.findViewById(R.id.tv_Snippet);
        TextView tvHeadlineMain = One_Line_Result.findViewById(R.id.tv_Headline_Main);
        JSONObject jItem =this.getItem(position);

        try {
            tvUrl.setText(jItem.getString("web_url"));
            tvSnippet.setText(jItem.getJSONObject("headline").getString("main"));
            tvHeadlineMain.setText(jItem.getString("lead_paragraph"));
        } catch (JSONException e) {
            e.printStackTrace();
            Context context= mActivity.getApplicationContext();
            Toast toast = Toast.makeText(context, "jItem problems", Toast.LENGTH_LONG);
        }


        return One_Line_Result;
    }
}
