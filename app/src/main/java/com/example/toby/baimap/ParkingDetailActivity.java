package com.example.toby.baimap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.toby.baimap.activity.Info;
import com.example.toby.baimap.utils.MapUtils;
import com.example.toby.baimap.utils.MyLocationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParkingDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_navigrtion)
    TextView tvNavigrtion;
    @BindView(R.id.tv_parking)
    TextView tvParking;
    private Info currentParkingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_detail);
        ButterKnife.bind(this);

        currentParkingInfo = (Info) getIntent().getSerializableExtra("info");

        tvPrice.setText("停车场单价：" + currentParkingInfo.getPrice() + "/小时");
        tvName.setText("停车场名字：" + currentParkingInfo.getName());


    }

    @OnClick({R.id.tv_navigrtion, R.id.tv_parking, R.id.iv_parking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_navigrtion:
            case R.id.iv_parking:
                MapUtils.openBaiduMap(ParkingDetailActivity.this, MyLocationUtil.mLatitude, MyLocationUtil.mLongtitude, currentParkingInfo.getLatitude(), currentParkingInfo.getLongitude(), "我的位置", currentParkingInfo.getName());
                break;
            case R.id.tv_parking:
                Intent intent = new Intent(this, ParkActivity.class);
                intent.putExtra("info", currentParkingInfo);
                startActivity(intent);
                break;
        }
    }
}
