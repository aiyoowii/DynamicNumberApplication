package com.cegrano.android.dynamicnumber;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cegrano on 2017/6/8.
 * 动态变化的数字
 */
public class DynamicNumberView extends View{
    Paint mPaint = new Paint();
    int[] numbers = new int[]{1,0,0,0};
    private Rect mBounds = new Rect();
    private int mNumber = 0;
    private long mMillisUntilFinished = -1;

    public DynamicNumberView(Context context) {
        super(context);
        init();
    }

    public DynamicNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DynamicNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DynamicNumberView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(getMeasuredHeight());
        mPaint.setAntiAlias(true);

        String testString = "8";
        mPaint.setStrokeWidth(3);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.getTextBounds(testString, 0, testString.length(), mBounds);
    }

    public void setNumber(int number){
        mNumber = number;
        new CountDownTimer(6000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished<5500) {
                    mMillisUntilFinished = millisUntilFinished;
                    postInvalidate();
                }
            }

            @Override
            public void onFinish() {
                mMillisUntilFinished = 0;
                postInvalidate();
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        canvas.drawText(getNumString(0)+"",0,getNumY(0),mPaint);
        canvas.drawText(getNumString(0)+1+"",0,getNumY(0)+getMeasuredHeight(),mPaint);
        canvas.drawText(getNumString(1)+"",mBounds.width(),getNumY(1),mPaint);
        canvas.drawText(getNumString(1)+1+"",mBounds.width(),getNumY(1)+getMeasuredHeight(),mPaint);
        canvas.drawText(getNumString(2)+"",mBounds.width()*2,getNumY(2),mPaint);
        canvas.drawText(getNumString(2)+1+"",mBounds.width()*2,getNumY(2)+getMeasuredHeight(),mPaint);
        canvas.drawText(getNumString(3)+"",mBounds.width()*3,getNumY(3),mPaint);
        canvas.drawText(getNumString(3)+1+"",mBounds.width()*3,getNumY(3)+getMeasuredHeight(),mPaint);

    }


    private int getNumString(int i) {
        if (mMillisUntilFinished==-1)
            return 0;
        return (int)(mNumber/(int) Math.pow(10,(3-i)/2)%10+(0>=mMillisUntilFinished-1000*i?0:mMillisUntilFinished))%10;
    }

    private float getNumY(int i) {
        return getMeasuredHeight()/2 + mBounds.height()/2 - (0>=mMillisUntilFinished-1000*i?0:mMillisUntilFinished%(float)getHeight());
    }

}
