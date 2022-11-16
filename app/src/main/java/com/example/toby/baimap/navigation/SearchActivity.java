package com.example.toby.baimap.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.example.toby.baimap.R;

import java.util.List;


//实现poi（导航地图信息）检索结果回调接口，第二个接口提供在线建议查询功能
public class SearchActivity extends AppCompatActivity implements OnGetPoiSearchResultListener,
        OnGetSuggestionResultListener, TextWatcher {

    private PoiSearch mPoiSearch = null;
    private List<PoiInfo> mAllPoi;
    private EditText mSearch;
    private RecyclerView mList;
    private AddressAdapter mAdapter;
    private MyLocationListener mLocationListener;
    private LocationClient mLocationClient;
    //经纬度
    public double mLatitude;
    public double mLongtitude;
    private LatLng mLatlng;
    private String city;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //定位
        initLocation();
        // 创建poi检索实例，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        mSearch = findViewById(R.id.search);
        mSearch.addTextChangedListener(this);
        mList = findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initLocation() {
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();

    }
    //我的位置的监听
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            mLatitude = location.getLatitude();
            mLongtitude = location.getLongitude();
            //经纬度
            mLatlng = new LatLng(mLatitude, mLongtitude);
            city=location.getCity();
        }
    }

    @Override
    public void onGetPoiResult(final PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {

        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            // 获取poi结果
            mAllPoi = result.getAllPoi();
            mAdapter = new AddressAdapter(SearchActivity.this,mLatlng);
            mAdapter.setNewData(mAllPoi);
            mList.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((adapter, view, position) -> {
                double longitude = mAllPoi.get(position).getLocation().longitude;
                double latitude = mAllPoi.get(position).getLocation().latitude;
                String name = mAllPoi.get(position).getName();
                String address = mAllPoi.get(position).getAddress();

                Intent intent=new Intent(SearchActivity.this,MapPoiActivity.class);
                intent.putExtra("longitude",longitude);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude1",mLongtitude);
                intent.putExtra("latitude1",mLatitude);
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                startActivity(intent);
            });
            return;
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult result) {
    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPoiSearch.destroy();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (mSearch.getText().toString().length() != 0) {

            mPoiSearch.searchInCity((new PoiCitySearchOption())
                    .city("city")
                    .keyword(mSearch.getText().toString())
                    .pageNum(0)
                    .cityLimit(false)
                    .scope(1));
        }
    }
}
