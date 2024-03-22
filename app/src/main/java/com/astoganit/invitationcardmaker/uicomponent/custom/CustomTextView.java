package com.astoganit.invitationcardmaker.uicomponent.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.astoganit.invitationcardmaker.R;


public class CustomTextView extends TextView {
    public boolean fancyText;


    public CustomTextView(Context context) {
        super(context);
        setOnTouchListener(new OnTouchEvent(context, this));
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("CustomViewStyleable") TypedArray a = context.obtainStyledAttributes(attrs, com.astoganit.invitationcardmaker.R.styleable.DateView);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            fancyText = a.getBoolean(attr, false);
            setCustomBackground(context, context.getResources().getDrawable(R.drawable.background_list));
            new Handler().postDelayed(this::changeBackground, 1000);
        }
        //setTouchListener();
        setOnTouchListener(new OnTouchEvent(context, this));
        a.recycle();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setCustomBackground(Context context, Drawable drawable) {
        textSize = getTextSize();

        if (this.fancyText) {
            //setShadowLayer(9, 1, 1, Color.rgb(44, 44, 40));
            setBackground(drawable);
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) getLayoutParams();
        if (lp != null) {
            marginLeft = lp.leftMargin;
            marginRight = lp.rightMargin;
            marginTop = lp.topMargin;
            marginBottom = lp.bottomMargin;
        }

    }

    void changeBackground() {
        AnimationDrawable animationDrawable = (AnimationDrawable) getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
    }


    public void scaleText(float factor) {
        if (textSize < factor) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, factor);
        }
    }

    float textSize;
    int marginLeft;
    int marginRight;
    int marginTop;
    int marginBottom;

    public void setLeftMargin() {
        marginLeft--;
        ViewParent view = getParent();
        if (view instanceof LinearLayout) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
            setLayoutParams(params);
        } else if (view instanceof RelativeLayout) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
            setLayoutParams(params);
        }

    }
}