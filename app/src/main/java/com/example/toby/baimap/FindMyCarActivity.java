package com.example.toby.baimap;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.toby.baimap.entity.ParkingEntity;
import com.example.toby.baimap.entity.UserEntity;
import com.example.toby.baimap.utils.ImageToBase64;
import com.example.toby.baimap.utils.MapUtils;
import com.example.toby.baimap.utils.MyLocationUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class FindMyCarActivity extends AppCompatActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_navigrtion)
    TextView tvNavigrtion;
    @BindView(R.id.tv_finish_parking)
    TextView tvFinishParking;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    private ParkingEntity myCarInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_car);
        ButterKnife.bind(this);

        getMyCarInfo();
    }

    private void getMyCarInfo() {
        BmobQuery<ParkingEntity> query = new BmobQuery<>();
        query.findObjects(new FindListener<ParkingEntity>() {
            @Override
            public void done(List<ParkingEntity> list, BmobException e) {
                if (e == null) {
                    for (ParkingEntity parkingEntity : list) {
                        if (parkingEntity.getUserEntity().getObjectId().equals(BmobUser.getCurrentUser(UserEntity.class).getObjectId())) {
                            myCarInfo = parkingEntity;
                            break;
                        }
                    }

                    if (myCarInfo == null) {
                        showErrorDialog();
                    } else {
                        tvTime.setText("停车时间：" + myCarInfo.getCreatedAt());
                        tvName.setText("停车场：" + myCarInfo.getParkingName());
                        if (myCarInfo.getCarImgUrl().contains("http")) {
                            Glide.with(FindMyCarActivity.this).load(myCarInfo.getCarImgUrl()).into(ivCar);
                        } else {
                            ivCar.setImageBitmap(ImageToBase64.base64ToBitmap(myCarInfo.getCarImgUrl()));
                        }
                    }
                } else {
                    showErrorDialog();
                }
            }
        });
    }

    private void showErrorDialog() {
        new AlertDialog.Builder(FindMyCarActivity.this)
                .setTitle("抱歉！")
                .setMessage("你暂时没有停车记录...")
                .setCancelable(false)
                .setNegativeButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                }).show();
    }

    @OnClick({R.id.tv_navigrtion, R.id.tv_finish_parking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_navigrtion:
                MapUtils.openBaiduMap(FindMyCarActivity.this, MyLocationUtil.mLatitude, MyLocationUtil.mLongtitude, myCarInfo.getLatitude(), myCarInfo.getLongitude(), "我的位置", myCarInfo.getParkingName());
                break;
            case R.id.tv_finish_parking:

                new AlertDialog.Builder(FindMyCarActivity.this)
                        .setTitle("请支付停车费")
                        .setMessage("改停车场的计费价格为 每小时" + myCarInfo.getPrice() + "元\n您的停车时间开始时间为：" + myCarInfo.getCreatedAt() + "\n请联系停车场管理人员付费！！")
                        .setNegativeButton("去支付", (dialog, which) -> myCarInfo.delete(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    dialog.dismiss();
                                    finish();
                                }
                            }
                        })).setPositiveButton("取消", (dialog, which) -> dialog.dismiss()).show();
                break;
        }
    }
}