package com.example.toby.baimap.line.TakeView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import com.example.toby.baimap.line.utils.DisplayUtil;
import com.example.toby.baimap.line.utils.ScreenUtils;

public class TakeViewC extends View {

	private Context mContext;

	public TakeViewC(Context context) {
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
		path.moveTo(screenWidth- DisplayUtil.dip2px(mContext,25), DisplayUtil.dip2px(mContext,275));
		path.lineTo(screenWidth- DisplayUtil.dip2px(mContext,25), DisplayUtil.dip2px(mContext,225));
		path.lineTo(buttomWidth/2+ DisplayUtil.dip2px(mContext,50), DisplayUtil.dip2px(mContext,225));
		path.lineTo(buttomWidth/2+ DisplayUtil.dip2px(mContext,50), DisplayUtil.dip2px(mContext,250));
		canvas.drawPath(path, p);
	}
}
