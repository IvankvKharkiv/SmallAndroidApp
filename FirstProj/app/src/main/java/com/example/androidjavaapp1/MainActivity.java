package com.example.androidjavaapp1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText et_KeyWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_KeyWord= findViewById(R.id.et_KeyWord);
    }


    public void btn_Show_Articles_OnClick(View v){
        Intent intent = new Intent(this, ResultsList.class);
        intent.putExtra("Key_Word", et_KeyWord.getText());
        startActivity(intent);
    }


}
