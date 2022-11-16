package com.example.toby.baimap.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.toby.baimap.MainActivity;
import com.example.toby.baimap.R;
import com.example.toby.baimap.entity.UserEntity;
import com.example.toby.baimap.line.DrawView.DrawViewA;
import com.example.toby.baimap.line.DrawView.DrawViewB;
import com.example.toby.baimap.line.DrawView.DrawViewC;
import com.example.toby.baimap.line.DrawView.DrawViewD;
import com.example.toby.baimap.line.DrawView.DrawViewE;
import com.example.toby.baimap.line.DrawView.DrawViewF;
import com.example.toby.baimap.line.DrawView.DrawViewG;
import com.example.toby.baimap.line.DrawView.DrawViewH;
import com.example.toby.baimap.line.DrawView.DrawViewI;
import com.example.toby.baimap.line.DrawView.DrawViewJ;
import com.example.toby.baimap.line.DrawView.DrawViewK;
import com.example.toby.baimap.line.DrawView.DrawViewL;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class IndoorCarActivity extends AppCompatActivity implements View.OnClickListener {


    private FrameLayout mFrameLayout;
    private View addView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_car);
        findViewById(R.id.A).setOnClickListener(this);
        findViewById(R.id.B).setOnClickListener(this);
        findViewById(R.id.C).setOnClickListener(this);
        findViewById(R.id.D).setOnClickListener(this);
        findViewById(R.id.E).setOnClickListener(this);
        findViewById(R.id.F).setOnClickListener(this);
        findViewById(R.id.G).setOnClickListener(this);
        findViewById(R.id.H).setOnClickListener(this);
        findViewById(R.id.I).setOnClickListener(this);
        findViewById(R.id.J).setOnClickListener(this);
        findViewById(R.id.K).setOnClickListener(this);
        findViewById(R.id.L).setOnClickListener(this);
        mFrameLayout = findViewById(R.id.fl);
        findViewById(R.id.Ba).setOnClickListener(view -> startActivity(
                new Intent(IndoorCarActivity.this, MainActivity.class)));
    }

    @Override
    public void onClick(View view) {
        mFrameLayout.removeView(addView);
        switch (view.getId()) {
            case R.id.A:
                final DrawViewA vA = new DrawViewA(this);
                vA.invalidate();
                addView = vA;
                mFrameLayout.addView(vA);
                showErrorDialog("A");
                break;
            case R.id.B:
                final DrawViewB vB = new DrawViewB(this);
                vB.invalidate();
                addView = vB;
                mFrameLayout.addView(vB);
                showErrorDialog("B");
                break;
            case R.id.C:
                final DrawViewC vC = new DrawViewC(this);
                vC.invalidate();
                addView = vC;
                mFrameLayout.addView(vC);
                showErrorDialog("C");
                break;
            case R.id.D:
                final DrawViewD vD = new DrawViewD(this);
                vD.invalidate();
                addView = vD;
                mFrameLayout.addView(vD);
                showErrorDialog("D");
                break;
            case R.id.E:
                final DrawViewE vE = new DrawViewE(this);
                vE.invalidate();
                addView = vE;
                mFrameLayout.addView(vE);
                showErrorDialog("E");
                break;
            case R.id.F:
                final DrawViewF vF = new DrawViewF(this);
                vF.invalidate();
                addView = vF;
                mFrameLayout.addView(vF);
                showErrorDialog("F");
                break;
            case R.id.G:
                final DrawViewG vG = new DrawViewG(this);
                vG.invalidate();
                addView = vG;
                mFrameLayout.addView(vG);
                showErrorDialog("G");
                break;
            case R.id.H:
                final DrawViewH vH = new DrawViewH(this);
                vH.invalidate();
                addView = vH;
                mFrameLayout.addView(vH);
                showErrorDialog("H");
                break;
            case R.id.I:
                final DrawViewI vI = new DrawViewI(this);
                vI.invalidate();
                addView = vI;
                mFrameLayout.addView(vI);
                showErrorDialog("I");
                break;
            case R.id.J:
                final DrawViewJ vJ = new DrawViewJ(this);
                vJ.invalidate();
                addView = vJ;
                mFrameLayout.addView(vJ);
                showErrorDialog("J");
                break;
            case R.id.K:
                final DrawViewK vK = new DrawViewK(this);
                vK.invalidate();
                addView = vK;
                mFrameLayout.addView(vK);
                showErrorDialog("K");
                break;
            case R.id.L:
                final DrawViewL vL = new DrawViewL(this);
                vL.invalidate();
                addView = vL;
                mFrameLayout.addView(vL);
                showErrorDialog("L");
                break;
        }
    }

    private void showErrorDialog(final String message) {
        UserEntity user = BmobUser.getCurrentUser(UserEntity.class);

        if (TextUtils.isEmpty(user.getParkNo())) {
            new AlertDialog.Builder(IndoorCarActivity.this)
                    .setMessage("是否将车停在" + message + "车位?")
                    .setCancelable(false)
                    .setNegativeButton("确定", (dialog, which) -> {
                        UserEntity user1 = BmobUser.getCurrentUser(UserEntity.class);
                        UserEntity userEntity = new UserEntity();
                        userEntity.setNickName(user1.getNickName());
                        userEntity.setUserInfo(user1.getUserInfo());
                        userEntity.setParkNo(message);
                        userEntity.update(user1.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(IndoorCarActivity.this, "您已成功把车停在" + message + "车位", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(IndoorCarActivity.this, "停车失败请重试", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    })
                    .setNeutralButton("取消", (dialogInterface, i) -> {
                    }).show();
        } else {//已经停车
            Toast.makeText(IndoorCarActivity.this, "停车失败！您的爱车已在" + user.getParkNo() + "车位，快去看看吧", Toast.LENGTH_SHORT).show();
        }

    }
}
