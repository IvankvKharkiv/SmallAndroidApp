package com.example.androidjavaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebPageView_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page_view_);
        Bundle bundle = getIntent().getExtras();
        //bundle.get("Key_Word")

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl((String) bundle.get("url_String"));
    }
}
