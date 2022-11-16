package com.example.toby.baimap.line.DrawView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import com.example.toby.baimap.line.utils.DisplayUtil;
import com.example.toby.baimap.line.utils.ScreenUtils;

public class DrawViewE extends View {

	private Context mContext;
 
	public DrawViewE(Context context) {
		super(context);
		mContext=context;
	}
 
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 创建画笔
		int screenWidth = ScreenUtils.getScreenWidth(mContext);


		Paint p = new Paint();
		p.setColor(Color.RED);// 设置红色

 
		// 你可以绘制很多任意多边形，比如下面画六连形
		p.reset();//重置
		p.setColor(Color.WHITE);
		p.setStyle(Paint.Style.STROKE);//设置空心
		p.setStrokeWidth(3);
		Path path=new Path();
		path.moveTo(screenWidth/2, 0);
		path.lineTo(screenWidth/2, DisplayUtil.dip2px(mContext,50));
		canvas.drawPath(path, p);


	}
}
