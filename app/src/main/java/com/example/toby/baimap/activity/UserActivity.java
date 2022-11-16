package com.example.toby.baimap.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toby.baimap.MineActivity;
import com.example.toby.baimap.R;
import com.example.toby.baimap.entity.UserEntity;
import com.example.toby.baimap.login.LoginActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class UserActivity extends AppCompatActivity {

    String type;
    TextView phoneNumber, birthday, sex, username, email;
    LinearLayout  sexLL, usernameLL, jumpToRetrievePassword;
    RoundedImageView head;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS };
    public static String sessionId;
    public static String parentPath = Environment.getExternalStorageDirectory() + "/englishLearning";
    //public static String headImgPath = parentPath + "/head.png";

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar mToolbarTb = findViewById(R.id.toolbarIdea);
        mToolbarTb.setTitle("个人信息");
        setSupportActionBar(mToolbarTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTb.setOnClickListener(v -> {
        });
        init();
        head.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });
        usernameLL.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, MineActivity.class);
            intent.putExtra("phoneNumber", getPhoneNumber());
            intent.putExtra("nikeName", username.getText().toString().substring(0, username.length() - 2));
            startActivity(intent);
        });
        sexLL.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
            builder.setTitle("选择性别");
            final String[] items = getResources().getStringArray(R.array.sexType);
            builder.setItems(items, (dialog, which) -> {
                sex.setText(items[which] + " >");
                type = "sex";
                UserEntity user1=new UserEntity();
                UserEntity user=UserEntity.getCurrentUser(UserEntity.class);
                user1.setSex(items[which]);
                user1.update(user.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            System.out.println("性别修改成功");
                            Toast.makeText(UserActivity.this,"性别修改成功",Toast.LENGTH_SHORT).show();
                        }else {
                            System.out.println("系统错误");
                            Toast.makeText(UserActivity.this,"系统发生未知错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            });
            builder.create().show();
        });

        jumpToRetrievePassword.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, MineActivity.class);
            intent.putExtra("phoneOrEmail", getPhoneNumber());
            startActivity(intent);
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //初始化
    @SuppressLint("SetTextI18n")
    void init() {
        Bmob.initialize(UserActivity.this, "0b7bcfd0b0c910c4b7e7a2841ba18cf9");
        //Android6.0以上检查权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, 1);
        }
        File file = new File(parentPath);
        if (!file.exists())
            file.mkdir();
        phoneNumber = findViewById(R.id.phoneNumber);
        //birthday = findViewById(R.id.birthday);
        sex = findViewById(R.id.sex);
        username = findViewById(R.id.username);
        //email = findViewById(R.id.email);
        //emailLL = findViewById(R.id.emailLL);
        usernameLL = findViewById(R.id.usernameLL);
        //birthdayLL = findViewById(R.id.birthdayLL);
        sexLL = findViewById(R.id.sexLL);
        jumpToRetrievePassword = findViewById(R.id.jumpToRetrievePassword);
        head = findViewById(R.id.head);
        /*查询用户信息*/
        if(!BmobUser.isLogin()){
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//跳转之后不要再返回了
            intent.setClass(UserActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            show();
        }
    }
    Bitmap bitmap;
    public void onResume() {    //返回进Activity时重新执行部分
        super.onResume();
        BmobUser.fetchUserInfo(new FetchUserInfoListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                if (e == null) {
                    System.out.println("更新本地信息成功");
                } else {
                    System.out.println("更新本地信息失败");
                }
            }
        });
        show();
    }
    private void show(){
        BmobQuery<UserEntity> bmobQuery = new BmobQuery<>();
        UserEntity user=UserEntity.getCurrentUser(UserEntity.class);
        bmobQuery.getObject(user.getObjectId(), new QueryListener<UserEntity>() {
            @Override
            public void done(UserEntity object,BmobException e) {
                if(e==null){
                    System.out.println(object.getNickName());
                    //if(object.getHeadImage()!=null) {
                       // Glide.with(UserActivity.this).load(object.getHeadImage().getUrl()).error(R.mipmap.ic_launcher_round)//图片加载失败后，显示的图片
                                //.into(head);
                   // }
                    phoneNumber.setText(object.getMobilePhoneNumber());
                    //if (object.getEmail() == null || object.getEmail().equals(""))
                        //email.setText("添加邮箱 >");
                    //else
                        //email.setText(object.getEmail() + " >");
                    if (object.getBirthday().getDate() == null || object.getBirthday().getDate().equals(""))
                        birthday.setText("设置 >");
                    else
                        birthday.setText(object.getBirthday().getDate().substring(0,10) + " >");
                    if (object.getSex() == null || object.getSex().equals(""))
                        sex.setText("保密 >");
                    else
                        sex.setText(object.getSex() + " >");
                    if (object.getNickName() == null || object.getNickName().equals(""))
                        username.setText("设置 >");
                    else
                        username.setText(object.getNickName() + " >");
                }else{
                    System.out.println("出错了");
                }
            }
        });
    }
   // @Override
   //protected void onActivityResult(int requestCode, int resultCode, Intent data) {//选择照片之后执行的方法
       // super.onActivityResult(requestCode, resultCode, data);
       // if (data != null && requestCode == 1 && resultCode == RESULT_OK) {
          //  Uri selectImg = data.getData();
            //String[] filePathColumn = {MediaStore.Images.Media.DATA};
            //Cursor cursor = null;
            //if (selectImg != null) {
                //cursor = getContentResolver().query(selectImg, filePathColumn, null, null, null);
           // }
            //if (cursor != null) {
                //cursor.moveToFirst();
            //}
           // assert cursor != null;
            //String imgPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            //cursor.close();

            //bitmap = BitmapFactory.decodeFile(imgPath);
            //if (bitmap != null) {
                //final BmobFile bmobFile = new BmobFile(new File(imgPath));
                //bmobFile.uploadblock(new UploadFileListener() {
                    //@Override
                   // public void done(BmobException e) {
                      //  if(e==null){
                            //bmobFile.getFileUrl()--返回的上传文件的完整地址
                           // UserEntity user=UserEntity.getCurrentUser(UserEntity.class);
                           // user.setHeadImage(bmobFile);
                           // user.update(new UpdateListener() {
                               // @Override
                                //public void done(BmobException e) {
                                    //if(e==null){
                                       // System.out.println("同步成功");
                                       // head.setImageBitmap(bitmap);
                                    //}else {
                                      //  System.out.println("同步失败");
                                   // }
                                //}
                           // });
                       // }else{
                          //  System.out.println("失败");
                       // }
                  //  }
                    //@Override
                   // public void onProgress(Integer value) {
                        // 返回的上传进度（百分比）
                  //  }
                //});
          //  }
           // else
              //  Toast.makeText(getApplicationContext(), "头像设置失败", Toast.LENGTH_SHORT).show();
       // }
    //}
    public String getInfoByType() {   //取得性别或生日信息
        switch (type) {
            case "sex":
                return sex.getText().toString().substring(0, sex.length() - 2);
            case "birthday":
                return getBirthday().substring(0,10);
            default:
                return "";
        }
    }
    public String addZero(int number) {
        if (number < 10)
            return "0" + number;
        else
            return "" + number;
    }
    public String getPhoneNumber() {
        return phoneNumber.getText().toString();
    }
    public String getBirthday() {
        return birthday.getText().toString();
    }
}
