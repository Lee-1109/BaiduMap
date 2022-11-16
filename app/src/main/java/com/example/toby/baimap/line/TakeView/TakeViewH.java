package com.example.toby.baimap.line.TakeView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import com.example.toby.baimap.line.utils.DisplayUtil;
import com.example.toby.baimap.line.utils.ScreenUtils;

public class TakeViewH extends View {

	private Context mContext;

	public TakeViewH(Context context) {
		super(context);
		mContext=context;
	}
 
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int screenWidth = ScreenUtils.getScreenWidth(mContext);
		int buttomWidth=(screenWidth- DisplayUtil.dip2px(mContext,200))/3;
		Paint p = new Paint();
		p.setColor(Color.RED);
		p.reset();
		p.setColor(Color.WHITE);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(3);
		Path path=new Path();
		path.moveTo(DisplayUtil.dip2px(mContext,50)+buttomWidth/2, DisplayUtil.dip2px(mContext,25));
		path.lineTo(DisplayUtil.dip2px(mContext,75)+buttomWidth, DisplayUtil.dip2px(mContext,25));
		path.lineTo(DisplayUtil.dip2px(mContext,75)+buttomWidth, DisplayUtil.dip2px(mContext,375));
		path.lineTo(DisplayUtil.dip2px(mContext,100)+buttomWidth, DisplayUtil.dip2px(mContext,375));

		canvas.drawPath(path, p);
	}
}
