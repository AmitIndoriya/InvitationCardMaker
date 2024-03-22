package com.astoganit.invitationcardmaker.uicomponent.custom;

import android.content.Context;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;

import com.astoganit.invitationcardmaker.R;
import com.astoganit.invitationcardmaker.uicomponent.activity.MainActivity;

class OnTouchEvent implements View.OnTouchListener {
    private View dragMe;
    private View root;
    private TextView textView;
    Context context;
    private int initialHeight;
    private int initialWidth;
    private int initialX;
    private int initialY;
    private int _xDelta;
    private int _yDelta;
    ScaleGestureDetector scaleGestureDetector;
    float s1 = .1f, s2 = .1f;

    public OnTouchEvent(Context context, View root, View dragMe, TextView textView) {
        this.dragMe = dragMe;
        this.context = context;
        this.textView = textView;
        this.root = root;
        scaleGestureDetector = new ScaleGestureDetector(context, new simpleOnScaleGestureListener());

    }

    public OnTouchEvent(Context context, View dragMe) {
        this.dragMe = dragMe;
        this.context = context;
        scaleGestureDetector = new ScaleGestureDetector(context, new simpleOnScaleGestureListener());

    }

    private static String TAG = MainActivity.class.getSimpleName();

    float xDown = 0, yDown = 0;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = motionEvent.getX();
                yDown = motionEvent.getY();
                ((CustomTextView) dragMe).setCustomBackground(context, context.getResources().getDrawable(R.drawable.rect_dotted));
                ((MainActivity) context).setSelectedView(dragMe);
                ((MainActivity) context).changeBackground();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                float xMove, yMove;
                xMove = motionEvent.getX();
                yMove = motionEvent.getY();
                float distX = xMove - xDown;
                float distY = yMove - yDown;
                dragMe.setX(dragMe.getX() + distX);
                dragMe.setY(dragMe.getY() + distY);
                break;
        }
        return true;
    }

    class simpleOnScaleGestureListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float size = ((TextView) dragMe).getTextSize();
            float factor = detector.getScaleFactor();
            float product = size * factor;
            ((TextView) dragMe).setTextSize(TypedValue.COMPLEX_UNIT_PX, product);
            return true;
        }
    }

    float getDistanceNew(int height, int width) {
        return (float) (int) ((double) Math.sqrt((height * height + width * width)));
    }
}


