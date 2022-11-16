package com.example.toby.baimap.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toby.baimap.R;
import com.example.toby.baimap.utils.MapUtils;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        final EditText etStart = findViewById(R.id.et_start);
        final EditText etEnd = findViewById(R.id.et_end);

        findViewById(R.id.tv_start).setOnClickListener(view -> {

            String start = etStart.getText().toString();
            String end = etEnd.getText().toString();
            if (TextUtils.isEmpty(start) || TextUtils.isEmpty(end)) {
                Toast.makeText(NavigationActivity.this, "请完善目的地信息...", Toast.LENGTH_SHORT).show();
                return;
            }
            MapUtils.openBaiduMap(NavigationActivity.this, start, end);

        });
    }
}
