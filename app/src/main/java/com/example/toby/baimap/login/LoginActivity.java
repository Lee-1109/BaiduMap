package com.example.toby.baimap.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.toby.baimap.MainActivity;
import com.example.toby.baimap.R;
import com.example.toby.baimap.entity.UserEntity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends Activity {
    //使用BindView 替代FindViewById()
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_user_register)
    TextView tvUserRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    private String userName;
    private String passWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
    //用注解来注册点击事件
    @OnClick({R.id.tv_user_register, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_user_register:
                startActivity(new Intent(LoginActivity.this, UserRegisterActivity.class));
                break;
            case R.id.tv_login:
                doLogin();
                break;
        }
    }
    private void doLogin() {
        userName = etUsername.getText().toString();
        passWord = etPassword.getText().toString();
        if (TextUtils.isEmpty(userName) ) {
            Toast.makeText(this, "请输入账户名", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(passWord)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
        }else{
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userName);
            userEntity.setPassword(passWord);
            userEntity.login(new SaveListener<UserEntity>() {
                @Override
                public void done(UserEntity userEntity, BmobException e) {
                    if (e == null) {//Bomb数据库无异常
                       /** 使用Activity类的getSharedPreferences方法获得SharedPreferences对象
                        * 使用SharedPreferences接口的edit获得SharedPreferences.Editor对象
                        * 通过SharedPreferences.Editor接口的putXXX方法保存key-value对
                        * 通过过SharedPreferences.Editor接口的commit方法保存key-value对。
                        * 根据名字查找SharedPreferences对象 找到则获取 找不到就写入*/
                        SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString("userName", userName).apply();
                        sharedPreferences.edit().putString("password", passWord).apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "登陆失败...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
