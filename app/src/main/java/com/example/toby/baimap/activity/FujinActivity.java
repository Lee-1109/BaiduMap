package com.example.toby.baimap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.toby.baimap.R;

public class FujinActivity extends AppCompatActivity {
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fujin);
        myToolbar=findViewById(R.id.toolbar_fujing);
        myToolbar.setTitle("附近的停车场");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setOnClickListener(v -> {

        });

        findViewById(R.id.jin).setOnClickListener(view -> startActivity(
                new Intent(FujinActivity.this, TingActivity.class))
        );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
