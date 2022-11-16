package com.example.toby.baimap.navigation;

import android.content.Context;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.utils.DistanceUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.toby.baimap.R;


public class AddressAdapter extends BaseQuickAdapter<PoiInfo, BaseViewHolder> {

    private Context context;
    private LatLng mLatLng;

    public AddressAdapter(Context context,LatLng latLng) {
        super(R.layout.item_address);
        this.context = context;
        mLatLng=latLng;
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiInfo item) {
        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.address, item.getAddress());


        LatLng llB = item.getLocation();

        helper.setText(R.id.distance, getDistance(DistanceUtil.getDistance(mLatLng, llB)));
    }

    private String getDistance(double dis) {
        int distance = (int) dis;
        String up;
        String down;
        if (dis < 1000) {
            up = "0";
            down = String.valueOf(distance).substring(0, 1);
        }else {
            up = String.valueOf(distance).substring(0,String.valueOf(distance).length() - 3);
            down = String.valueOf(distance).substring(String.valueOf(distance).length() - 3, String.valueOf(distance).length() - 2);
        }
        return up + "." + down + "km";
    }
}
