package com.example.toby.baimap.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.toby.baimap.MineActivity;
import com.example.toby.baimap.R;
import com.example.toby.baimap.login.LoginActivity;


public class SettingActivity extends AppCompatActivity {
    private RelativeLayout jumpToRetrievePassword,logout;
    private Boolean flag=false;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);
        getWindow().setStatusBarColor(0xff3399FF);
        jumpToRetrievePassword=findViewById(R.id.jumpToRetrievePassword);
        logout=findViewById(R.id.logout);
        Toolbar mToolbarTb = (Toolbar) findViewById(R.id.toolbarIdea);
        mToolbarTb.setTitle("设置");
        setSupportActionBar(mToolbarTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTb.setOnClickListener(v -> {

        });
        init();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void init(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击退出按钮
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确认注销?");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      // if (BmobUser.isLogin()) {
                            //ContactsContract.CommonDataKinds.Nickname user = BmobUser.getCurrentUser(ContactsContract.CommonDataKinds.Nickname.class);
                            //user.logOut();/*在bmob数据库中退出登录*/
                       // }
                        Intent intent = new Intent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//跳转之后不要再返回了
                        intent.setClass(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        jumpToRetrievePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MineActivity.class);
                startActivity(intent);
            }
        });

    }
}
