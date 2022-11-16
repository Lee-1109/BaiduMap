package com.example.toby.baimap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.toby.baimap.activity.App;
import com.example.toby.baimap.activity.Info;
import com.example.toby.baimap.entity.ParkingEntity;
import com.example.toby.baimap.entity.UserEntity;
import com.example.toby.baimap.utils.ImageToBase64;
import com.example.toby.baimap.utils.PictureSelectorUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class ParkActivity extends AppCompatActivity {
    @BindView(R.id.EditParkid)
    EditText EditParkid;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    @BindView(R.id.ButtonPark)
    Button ButtonPark;

    public static App app = null;
    private String carString;
    private Info currentParkingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);
        ButterKnife.bind(this);
        currentParkingInfo = (Info) getIntent().getSerializableExtra("info");
    }


    //链接数据库，插入停车表
    private void parking() {

        final String park = EditParkid.getText().toString();

        if (TextUtils.isEmpty(park)) {
            Toast.makeText(this, "请输入停车场编号", Toast.LENGTH_LONG).show();
            return;
        }

        ParkingEntity parkingEntity = new ParkingEntity();
        parkingEntity.setCarImgUrl(carString);
        parkingEntity.setParkingNumber(park);
        parkingEntity.setLatitude(currentParkingInfo.getLatitude());
        parkingEntity.setLongitude(currentParkingInfo.getLongitude());
        parkingEntity.setParkingName(currentParkingInfo.getName());
        parkingEntity.setPrice(currentParkingInfo.getPrice());
        parkingEntity.setUserEntity(BmobUser.getCurrentUser(UserEntity.class));
        parkingEntity.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(ParkActivity.this, "停车成功...", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }


    @OnClick({R.id.iv_car, R.id.ButtonPark})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_car:
                PictureSelectorUtils.selectImg(PictureSelector.create(this), 1);
                break;
            case R.id.ButtonPark:
                parking();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() > 0) {
                    String imagePath = selectList.get(0).getCompressPath();
                    decodePath2Bitmap(imagePath);
                }
            }
        }
    }


    /**
     * 把指定路径的image资源转成Bitmap
     *      *
     * @param path
     */
    private void decodePath2Bitmap(String path) {
        Bitmap bm = BitmapFactory.decodeFile(path);
        if (bm != null) {
            ivCar.setImageBitmap(bm);
            carString = ImageToBase64.bitmap2Base64String(bm);
        }
    }
}
