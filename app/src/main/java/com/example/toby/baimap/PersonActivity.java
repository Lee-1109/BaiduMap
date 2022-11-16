package com.example.toby.baimap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.toby.baimap.activity.AboutActivity;
import com.example.toby.baimap.activity.MessageActivity;
import com.example.toby.baimap.activity.SettingActivity;
import com.example.toby.baimap.activity.UserActivity;
import com.example.toby.baimap.login.LoginActivity;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar mToolbarTb = findViewById(R.id.toolbarIdea);
        mToolbarTb.setTitle("个人中心");
        setSupportActionBar(mToolbarTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTb.setOnClickListener(v -> {

        });
        init();
        findViewById(R.id.username).setOnClickListener(view -> startActivity(
                new Intent(PersonActivity.this, LoginActivity.class)));
        findViewById(R.id.setting).setOnClickListener(view -> startActivity(
                new Intent(PersonActivity.this, SettingActivity.class)));
        findViewById(R.id.about).setOnClickListener(view -> startActivity(
                new Intent(PersonActivity.this, AboutActivity.class)));
        findViewById(R.id.meMessage).setOnClickListener(view -> startActivity(
                new Intent(PersonActivity.this, MessageActivity.class)));
        findViewById(R.id.userId).setOnClickListener(view -> startActivity(
                new Intent(PersonActivity.this, UserActivity.class)));
    }
    private void init() {
        findViewById(R.id.satisfaction).setOnClickListener(v -> {//点击退出按钮
            AlertDialog.Builder builder = new AlertDialog.Builder(PersonActivity.this);
            builder.setTitle("提示");
            builder.setMessage("抱歉！！！此功能尚未开放，敬请期待！！！");
            builder.setPositiveButton("确认", (dialog, which) -> {
                // if (BmobUser.isLogin()) {
                //ContactsContract.CommonDataKinds.Nickname user = BmobUser.getCurrentUser(ContactsContract.CommonDataKinds.Nickname.class);
                //user.logOut();/*在bmob数据库中退出登录*/
                // }
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//跳转之后不要再返回了
                intent.setClass(PersonActivity.this, PersonActivity.class);
                startActivity(intent);
            }).setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
            builder.create().show();
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(PersonActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}