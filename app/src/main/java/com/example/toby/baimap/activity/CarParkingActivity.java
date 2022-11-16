package com.example.toby.baimap.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.toby.baimap.ParkActivity;
import com.example.toby.baimap.ParkingDetailActivity;
import com.example.toby.baimap.R;
import com.example.toby.baimap.login.LoginActivity;
import com.example.toby.baimap.utils.MyLocationUtil;

import java.util.ArrayList;
import java.util.List;


public class CarParkingActivity extends AppCompatActivity {
    MapView mMapView = null;
    BaiduMap mBaiduMap = null;
    //关于交互后台的
    private int num;
    public static App count = null;
    public static String npark, str = "001", str3;
    //定位
    private MyLocationListener mLocationListener;
    private LocationClient mLocationClient;
    private boolean isFirstIn = true;

    private Context context;
    MapStatusUpdate msu;
    //经纬度
    public double mLatitude;
    public double mLongtitude;
    //添加覆盖物
    private BitmapDescriptor mMarker;
    private RelativeLayout mMarkerLy;
    //调用外部导航
    private Button mButton;
    private ArrayList<Info> infos;
    private Info currentParkingInfo;
    //扫描


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_car_parking);
        //后台交互
        //find();
        //
        this.context = this;
        initMap();
        //definePoint();
        //定位
        initLocation();

        initMarker();
        //添加覆盖
        //find(str);
//        addOverlays(infos);
        //导航按钮
        mButton = findViewById(R.id.button_go);
        //创建监听
        mButton.setOnClickListener(v -> {
            if (currentParkingInfo == null) {
                Toast.makeText(context, "请先选择停车点...", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(CarParkingActivity.this, ParkingDetailActivity.class);
            intent.putExtra("info",currentParkingInfo);
            startActivity(intent);

//                openBaiduMap(39.981567, 116.431011, 40.981567, 120.431011, "我的位置", "天安门");
        });


        mBaiduMap.setOnMarkerClickListener(marker -> {
            Bundle extraInfo = marker.getExtraInfo();
            currentParkingInfo = (Info) extraInfo.getSerializable("currentParkingInfo");    //获取当前选择的停车场的各种信息
            ImageView iv = mMarkerLy
                    .findViewById(R.id.id_info_img);
            TextView distance = mMarkerLy.findViewById(R.id.id_info_distance);
            TextView name = mMarkerLy
                    .findViewById(R.id.id_info_name);
            TextView zan = mMarkerLy
                    .findViewById(R.id.id_info_zan);
            iv.setImageResource(currentParkingInfo.getImgId());
            distance.setText(currentParkingInfo.getDistance());
            name.setText(currentParkingInfo.getName());
            zan.setText(currentParkingInfo.getDistance());
            str = currentParkingInfo.getUmber();
            //count.setC("001");
            Log.e("parkid", str);



            mMarkerLy.setVisibility(View.VISIBLE);

            return true;
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public void onMapPoiClick(MapPoi arg0) {

            }

            @Override
            public void onMapClick(LatLng arg0) {
                mMarkerLy.setVisibility(View.GONE);
                mBaiduMap.hideInfoWindow();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }


    private void initMarker() {
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        mMarkerLy = findViewById(R.id.id_maker_ly);
    }

    private void initLocation() {
        // mLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);


        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
//初始化图标
        //mIconLocation = BitmapDescriptorFactory.fromResource(R.drawable.arrow);
        //myOrientationListener = new MyOrientationListener(context);
/*
        myOrientationListener
                .setOnOrientationListener(new MyOrientationListener.OnOrientationListener()
                {
                    @Override
                    public void onOrientationChanged(float x)
                    {
                        mCurrentX = x;
                    }
                });
                */
//设置定位点
        LatLng point2 = new LatLng(mLatitude, mLongtitude);
        BitmapDescriptor bitloc = BitmapDescriptorFactory.fromResource(R.drawable.arrow);
        OverlayOptions option2 = new MarkerOptions().position(point2).icon(bitloc);
        mBaiduMap.addOverlay(option2);
//添加标记


    }


    private void initMap() {
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //定义层级
        msu = MapStatusUpdateFactory.zoomTo(17.0f);
        mBaiduMap.setMapStatus(msu);
    }

    //我的位置的监听
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            MyLocationData data = new MyLocationData.Builder()
                    //.direction(mCurrentX)//
                    .accuracy(location.getRadius())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(data);

            //设置图标，经纬
            // MyLocationConfiguration config = new MyLocationConfiguration(mLocationMode, true, mIconLocation );
            //mBaiduMap.setMyLocationConfiguration(config);

//            mLatitude = 39.98181;
            mLatitude = location.getLatitude();//39.98181;
//            mLongtitude = 116.426876;
            mLongtitude = location.getLongitude();//116.426876;

            MyLocationUtil.mLatitude = mLatitude;
            MyLocationUtil.mLongtitude = mLongtitude;
            if (isFirstIn) {
                LatLng latlng = new LatLng(mLatitude, mLongtitude);//经纬度
                msu = MapStatusUpdateFactory.newLatLng(latlng);
                mBaiduMap.animateMapStatus(msu);
                isFirstIn = false;

                infos = new ArrayList<>();


                //count = (App) getApplication();
                infos.add(new Info(mLatitude + 0.001, mLongtitude, R.drawable.car1, "南大停车场",//004
                        "距离1000米", 10 , "004"));
                infos.add(new Info(mLatitude - 0.001, mLongtitude, R.drawable.car2, "江科停车",//003
                        "距离800米", 12 , "003"));
                infos.add(new Info(mLatitude, mLongtitude + 0.001, R.drawable.car3, "南航停车场",//002
                        "距离500米", 20 , "002"));
                infos.add(new Info(mLatitude, mLongtitude - 0.001, R.drawable.car4, "易停停车场",//001
                        "距离2000m", 25, "001"));


                addOverlays(infos);

                Toast.makeText(context, "我的位置",
                        //location.getAddrStr(),
                        Toast.LENGTH_SHORT).show();
            }
        }

      
    }


    private void centerToMyLocation() {
        LatLng latLng = new LatLng(mLatitude, mLongtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    //添加覆盖物
    private void addOverlays(List<Info> infos) {
        //mBaiduMap.clear();
        LatLng latLng = null;
        Marker marker;
        OverlayOptions options;
        for (Info info : infos) {
            // æ≠Œ≥∂»
            latLng = new LatLng(info.getLatitude(), info.getLongitude());
            // Õº±Í
            options = new MarkerOptions().position(latLng).icon(mMarker)
                    .zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle arg0 = new Bundle();
            arg0.putSerializable("currentParkingInfo", info);
            marker.setExtraInfo(arg0);
        }

        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(msu);

    }


    //生命周期管理及菜单管理
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted())
            mLocationClient.start();
        //方向传感器
        //myOrientationListener.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        //关闭方向传感器
        //myOrientationListener.stop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        switch (id) {
            case R.id.map_location:
                centerToMyLocation();
                break;
            case R.id.car_start:

                if (currentParkingInfo == null) {
                    Toast.makeText(context, "请先选择停车点...", Toast.LENGTH_SHORT).show();
                   break;
                }

                Intent intent = new Intent();
                intent.putExtra("info",currentParkingInfo);
                intent.setClass(CarParkingActivity.this, ParkActivity.class);
                CarParkingActivity.this.startActivity(intent);
                break;
            case R.id.exit:
                jumpTo(LoginActivity.class);

        }

        return super.onOptionsItemSelected(item);
    }

    private void jumpTo(Class<LoginActivity> m) {
        Intent intent = new Intent();
        intent.setClass(CarParkingActivity.this, m);
        CarParkingActivity.this.startActivity(intent);
    }
}

