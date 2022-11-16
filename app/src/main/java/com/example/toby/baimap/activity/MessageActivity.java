package com.example.toby.baimap.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.toby.baimap.R;

public class MessageActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_message);
        Toolbar myToolbarTb = findViewById(R.id.toolbarMessage);
        myToolbarTb.setTitle("我的消息");
        setSupportActionBar(myToolbarTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbarTb.setOnClickListener(v -> {
                //顶部Toolbar的监听器
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
