package com.example.toby.baimap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.example.toby.baimap.R;

public class TingActivity extends AppCompatActivity {
    Toolbar mytoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ting);
        findViewById(R.id.dao).setOnClickListener(view -> startActivity(
                new Intent(TingActivity.this, IndoorCarActivity.class)));
    }
}
