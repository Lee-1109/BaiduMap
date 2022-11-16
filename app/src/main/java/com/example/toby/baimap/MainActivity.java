package com.example.toby.baimap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.toby.baimap.activity.CarParkingActivity;
import com.example.toby.baimap.activity.FujinActivity;
import com.example.toby.baimap.activity.IndoorFindCarActivity;
import com.example.toby.baimap.navigation.SearchActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.person)
    ImageView person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.qu).setOnClickListener(view -> startActivity(
                new Intent(MainActivity.this, IndoorFindCarActivity.class)));
        findViewById(R.id.tv_parking).setOnClickListener(view -> startActivity(
                new Intent(MainActivity.this, CarParkingActivity.class)));

       findViewById(R.id.tv_find_my_car).setOnClickListener(v -> startActivity(
                new Intent(MainActivity.this,FindMyCarActivity.class)));

        findViewById(R.id.person).setOnClickListener(v -> startActivity(
                new Intent(MainActivity.this, PersonActivity.class)));
        findViewById(R.id.ting).setOnClickListener(view -> startActivity(
                new Intent(MainActivity.this, FujinActivity.class)));
        findViewById(R.id.search).setOnClickListener(view -> startActivity(
                new Intent(MainActivity.this, SearchActivity.class)));
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            ArrayList<String> permissions = new ArrayList<>();
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }

            if (permissions.size() != 0) {
                requestPermissionsForM(permissions);
            }
        }
    }

    private void requestPermissionsForM(final ArrayList<String> per) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(per.toArray(new String[per.size()]), 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
