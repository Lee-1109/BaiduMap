package com.example.toby.baimap.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class MapUtils {

    //百度地图调用
    public static void openBaiduMap(Context context, double startLat, double startLon, double endLat, double endLon, String title, String destination) {
        try {
            StringBuilder loc = new StringBuilder();
            loc.append("intent://map/direction?origin=latlng:");
            loc.append(startLat);
            loc.append(",");
            loc.append(startLon);
            loc.append("|name:");
            loc.append(title);
            loc.append("&destination=latlng:");
            loc.append(endLat);
            loc.append(",");
            loc.append(endLon);
            loc.append("|name:");
            loc.append(destination);
            loc.append("&mode=driving");
            loc.append("&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            Intent intent = Intent.getIntent(loc.toString());
            if (isInstallPackage("com.baidu.BaiduMap")) {
                context.startActivity(intent); //启动调用
                Log.e("GasStation", "百度地图客户端已经安装");
            } else {
                //Log.e("GasStation", "没有安装百度地图客户端");
                Toast.makeText(context, "没有安装百度地图客户端",
                        //location.getAddrStr(),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void openBaiduMap(Context context, String origin, String destination) {
        if (isInstallPackage("com.baidu.BaiduMap")) {
            Intent i1 = new Intent();

            i1.setData(Uri.parse("baidumap://map/direction?origin=" + origin + "&destination=" + destination + "&coord_type=bd09ll&mode=driving&src=andr.baidu.openAPIdemo"));

            context.startActivity(i1);
            Log.e("GasStation", "百度地图客户端已经安装");
        } else {
            //Log.e("GasStation", "没有安装百度地图客户端");
            Toast.makeText(context, "没有安装百度地图客户端",
                    //location.getAddrStr(),
                    Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean isInstallPackage(String s) {
        return new File("/data/data/" + s).exists();
    }

}
