package com.example.androidjavaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultsList extends AppCompatActivity {
    ListView lv_Results;
    TextView tv_PageNum;
    JSON_Adapter jAdap;
    String ApiUrlString;
    String keyWord;
    JSONArray jArr;
    int pageNum, hitsInSearch;
    LoadDataFromApi_ListView LoadData1;
    //Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_list);
        pageNum=0;
        tv_PageNum =findViewById(R.id.tv_PageNum);
        lv_Results = findViewById(R.id.listView_CategoryResults);
        lv_Results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    JSONObject jItem = jArr.getJSONObject(position);
                    OpenWebPage(jItem.getString("web_url"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        Bundle bundle = getIntent().getExtras();
        keyWord= ""+bundle.get("Key_Word");
        ApiUrlString="https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=lead_paragraph%3A("+keyWord+")&page=" +pageNum+"&api-key=nUdwHwl6DoAM13wg2sA70PbjFH76pKE6";
        LoadData1 = new LoadDataFromApi_ListView(this);
        LoadData1.execute(ApiUrlString);
    }

    private void OpenWebPage(String urlStr){
        Intent intent = new Intent(this, WebPageView_Activity.class);
        intent.putExtra("url_String", urlStr);
        startActivity(intent);
    }


    public void btn_Next_Ten_Res_OnClick(View v){
        if((pageNum+1)*10<hitsInSearch){
            pageNum++;
            tv_PageNum.setText(""+(pageNum+1));
            ApiUrlString="https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=lead_paragraph%3A("+keyWord+")&page=" +pageNum+"&api-key=nUdwHwl6DoAM13wg2sA70PbjFH76pKE6";
            LoadData1 = new LoadDataFromApi_ListView(this);
            LoadData1.execute(ApiUrlString);
        }
    }

    public void btn_Previouse_Ten_Click(View v){
        if(pageNum!=0){
            --pageNum;
            tv_PageNum.setText(""+(pageNum+1));
            ApiUrlString="https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=lead_paragraph%3A("+keyWord+")&page=" +pageNum+"&api-key=nUdwHwl6DoAM13wg2sA70PbjFH76pKE6";
            LoadData1 = new LoadDataFromApi_ListView(this);
            LoadData1.execute(ApiUrlString);
        }
    }


}
