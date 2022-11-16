package com.example.toby.baimap.entity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;

//对应User表
public class UserEntity extends BmobUser {
    private String nickName;//昵称
    private String userInfo;//用户信息
    private String parkNo;//停车场编号
    private String sex;//性别
    private BmobDate birthday;//生日
    public String getParkNo() {
        return parkNo;
    }

    public void setParkNo(String parkNo) {
        this.parkNo = parkNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex=sex;
    }

    public BmobDate getBirthday() {
        return birthday;
    }

    public void setBirthday(BmobDate birthday) {
        this.birthday = birthday;
    }
}
