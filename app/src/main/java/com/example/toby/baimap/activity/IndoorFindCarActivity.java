package com.example.toby.baimap.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.toby.baimap.R;
import com.example.toby.baimap.entity.UserEntity;
import com.example.toby.baimap.line.TakeView.TakeViewA;
import com.example.toby.baimap.line.TakeView.TakeViewB;
import com.example.toby.baimap.line.TakeView.TakeViewC;
import com.example.toby.baimap.line.TakeView.TakeViewD;
import com.example.toby.baimap.line.TakeView.TakeViewE;
import com.example.toby.baimap.line.TakeView.TakeViewF;
import com.example.toby.baimap.line.TakeView.TakeViewG;
import com.example.toby.baimap.line.TakeView.TakeViewH;
import com.example.toby.baimap.line.TakeView.TakeViewI;
import com.example.toby.baimap.line.TakeView.TakeViewJ;
import com.example.toby.baimap.line.TakeView.TakeViewK;
import com.example.toby.baimap.line.TakeView.TakeViewL;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class IndoorFindCarActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Button> mButtonList = new ArrayList<>();
    private FrameLayout mFrameLayout;
    private View addView;
    private String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_find_car);

        initButtom();

        mFrameLayout = findViewById(R.id.fl);

        UserEntity user = BmobUser.getCurrentUser(UserEntity.class);
        if (TextUtils.isEmpty(user.getParkNo())) {
            showErrorDialog();
        } else {
            for (int i = 0; i < mButtonList.size(); i++) {
                mButtonList.get(i).setOnClickListener(this);
                if (mButtonList.get(i).getText().equals(user.getParkNo())) {
                    index = user.getParkNo();
                    mButtonList.get(i).setBackgroundColor(Color.parseColor("#ff0000"));
                }
            }
        }
        findViewById(R.id.cha).setOnClickListener(v -> {//点击退出按钮
            AlertDialog.Builder builder = new AlertDialog.Builder(IndoorFindCarActivity.this);
            builder.setTitle("提示");
            builder.setMessage("您的车位信息：\n" +
                    "停车场：金山停车场.\n" +
                    "车位号：F.\n" +
                    "费用：20元\n");
            builder.setPositiveButton("确认", (dialog, which) -> {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//跳转之后不要再返回了
                intent.setClass(IndoorFindCarActivity.this, IndoorFindCarActivity.class);
                startActivity(intent);
            }).setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
            builder.create().show();
        });

    }
    private void initButtom() {
        mButtonList.add(findViewById(R.id.A));
        mButtonList.add(findViewById(R.id.B));
        mButtonList.add(findViewById(R.id.C));
        mButtonList.add(findViewById(R.id.D));
        mButtonList.add(findViewById(R.id.E));
        mButtonList.add(findViewById(R.id.F));
        mButtonList.add(findViewById(R.id.G));
        mButtonList.add(findViewById(R.id.H));
        mButtonList.add(findViewById(R.id.I));
        mButtonList.add(findViewById(R.id.J));
        mButtonList.add(findViewById(R.id.K));
        mButtonList.add(findViewById(R.id.L));
    }

    private void showErrorDialog() {
        new AlertDialog.Builder(IndoorFindCarActivity.this)
                .setTitle("抱歉！")
                .setMessage("你暂时没有停车...")
                .setCancelable(false)
                .setNegativeButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                }).show();
    }

    @Override
    public void onClick(View view) {
        mFrameLayout.removeView(addView);
        switch (view.getId()) {
            case R.id.A:
                if (index.equals("A")) {
                    final TakeViewA vA = new TakeViewA(this);
                    vA.invalidate();
                    addView = vA;
                    mFrameLayout.addView(vA);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.B:
                if (index.equals("B")) {
                    final TakeViewB vB = new TakeViewB(this);
                    vB.invalidate();
                    addView = vB;
                    mFrameLayout.addView(vB);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.C:
                if (index.equals("C")) {
                    final TakeViewC vC = new TakeViewC(this);
                    vC.invalidate();
                    addView = vC;
                    mFrameLayout.addView(vC);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.D:
                if (index.equals("D")) {
                    final TakeViewD vD = new TakeViewD(this);
                    vD.invalidate();
                    addView = vD;
                    mFrameLayout.addView(vD);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.E:
                if (index.equals("E")) {
                    final TakeViewE vE = new TakeViewE(this);
                    vE.invalidate();
                    addView = vE;
                    mFrameLayout.addView(vE);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.F:
                if (index.equals("F")) {
                    final TakeViewF vF = new TakeViewF(this);
                    vF.invalidate();
                    addView = vF;
                    mFrameLayout.addView(vF);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.G:
                if (index.equals("G")) {
                    final TakeViewG vG = new TakeViewG(this);
                    vG.invalidate();
                    addView = vG;
                    mFrameLayout.addView(vG);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.H:
                if (index.equals("H")) {
                    final TakeViewH vH = new TakeViewH(this);
                    vH.invalidate();
                    addView = vH;
                    mFrameLayout.addView(vH);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.I:
                if (index.equals("I")) {
                    final TakeViewI vI = new TakeViewI(this);
                    vI.invalidate();
                    addView = vI;
                    mFrameLayout.addView(vI);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.J:
                if (index.equals("J")) {
                    final TakeViewJ vJ = new TakeViewJ(this);
                    vJ.invalidate();
                    addView = vJ;
                    mFrameLayout.addView(vJ);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.K:
                if (index.equals("K")) {
                    final TakeViewK vK = new TakeViewK(this);
                    vK.invalidate();
                    addView = vK;
                    mFrameLayout.addView(vK);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.L:
                if (index.equals("L")) {
                    final TakeViewL vL = new TakeViewL(this);
                    vL.invalidate();
                    addView = vL;
                    mFrameLayout.addView(vL);
                    showDialog();
                } else {
                    Toast.makeText(IndoorFindCarActivity.this, "您已把车停在" + index + "车位", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void showDialog() {
        new AlertDialog.Builder(IndoorFindCarActivity.this)
                .setMessage("是否把车驶离"+index+"车位")
                .setCancelable(false)
                .setNeutralButton("取消", (dialogInterface, i) -> {
                })
                .setNegativeButton("确定", (dialog, which) -> {
                    UserEntity user = BmobUser.getCurrentUser(UserEntity.class);
                    UserEntity entity=new UserEntity();
                    entity.setNickName(user.getNickName());
                    entity.setUserInfo(user.getUserInfo());
                    entity.setParkNo("");
                    entity.update(user.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(IndoorFindCarActivity.this, "您已把车驶离" + index + "车位", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(IndoorFindCarActivity.this, "请重试", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }).show();
    }
}
