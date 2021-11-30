package com.zanaJmartAK.jmart_android;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textmain = (TextView) findViewById(R.id.textView2);
        textmain.setText("Welcome to main activity, " + LoginActivity.getLoggedAccount().name);
    }
}