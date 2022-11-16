package com.example.toby.baimap.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.toby.baimap.R;


public class EditTextWithDelete extends android.support.v7.widget.AppCompatEditText {
    Drawable drawable;

    public EditTextWithDelete(Context context) {
        super(context);
        init();
    }
    public EditTextWithDelete(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
    public EditTextWithDelete(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }

    void init() {
        drawable = getResources().getDrawable(R.mipmap.delete);
        drawable.setBounds(1, 1 , 50, 50);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if (length() > 0)
                    setCompoundDrawables(null,null, drawable,null);
                else//无输入不显示
                    setCompoundDrawables(null,null,null,null);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (drawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);//取得整个EditText矩形
            rect.left = rect.right - 50;//取整个矩形的最后一部分:宽50dp,EditText同高
            //System.out.println("x = " + x + ";y = " + y + ";" + rect.left + ";" + rect.right + ";" + rect.bottom + ";" + rect.top);
            if (rect.contains(x, y)) {//点击抬起的部分位于EditText最后一部分,即删除按钮所在处
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

