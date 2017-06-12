package com.cegrano.android.dynamicnumber;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cegrano on 2017/6/8.
 * 老虎机变化的数字
 */
public class ShakeNumberView extends View {
    Paint mPaint = new Paint();
    int[] numbers = new int[]{1, 0, 0, 0};
    private Rect mBounds = new Rect();
    private int mNumber = 0;
    private long mMillisUntilFinished = -1;
    private float mDensity;

    public ShakeNumberView(Context context) {
        super(context);
        init();
    }

    public ShakeNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShakeNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShakeNumberView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        mDensity = getResources().getDisplayMetrics().density;
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(44 * mDensity);
        mPaint.setAntiAlias(true);

        String testString = "8";
        mPaint.setStrokeWidth(3);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.getTextBounds(testString, 0, testString.length(), mBounds);
    }

    public void setNumber(int number) {
        mNumber = number;
        new CountDownTimer(6000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 5500) {
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
        canvas.drawText(getNumString(0, 0), 0, getNumY(0), mPaint);
        canvas.drawText(getNumString(1, 0), mBounds.width(), getNumY(1), mPaint);
        canvas.drawText(getNumString(2, 0), mBounds.width() * 2, getNumY(2), mPaint);
        canvas.drawText(getNumString(3, 0), mBounds.width() * 3, getNumY(3), mPaint);

    }


    private String getNumString(int i, int space) {
        if (mMillisUntilFinished == -1)
            return "0";
        return (mNumber / (int) Math.pow(10, 3 - i) % 10 + space + (0 >= mMillisUntilFinished - 1000 * i ? 0 : mMillisUntilFinished)) % 10 + "";
    }

    private float getNumY(int i) {
        //return getMeasuredHeight()/2 + mBounds.height()/2 - (0>=mMillisUntilFinished-1000*i?0:mMillisUntilFinished%(float)getHeight());
        return getMeasuredHeight() / 2 + mBounds.height() / 2;
    }

}
