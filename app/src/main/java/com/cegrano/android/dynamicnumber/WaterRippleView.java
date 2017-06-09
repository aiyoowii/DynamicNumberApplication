package com.cegrano.android.dynamicnumber;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import com.cegrano.android.dynamicnumberapplication.R;

/**
 * Created by cegrano on 2017/6/9.
 * 水波纹背景
 */
public class WaterRippleView extends View {

    private Paint mPaint = new Paint();
    private Rect mBounds = new Rect();
    private Bitmap mWater1, mWater2;
    private float mDensity;
    private long mOffset;
    private boolean stop;
    private CountDownTimer mT;

    public WaterRippleView(Context context) {
        super(context);
    }

    public WaterRippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WaterRippleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void start() {
        mT = new CountDownTimer(getMeasuredWidth(), 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                mOffset = millisUntilFinished - getMeasuredWidth();
                postInvalidate();
            }

            @Override
            public void onFinish() {
                postInvalidate();
                start();
            }
        }.start();
    }

    public void toggle() {
        stop = !stop;
        if (mT != null)
            mT.cancel();
        if (!stop)
            start();
    }

    private void init() {

        mDensity = getResources().getDisplayMetrics().density;
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(44 * mDensity);
        mPaint.setAntiAlias(true);

        String testString = "1000";
//        mPaint.setStrokeWidth(3);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getTextBounds(testString, 0, testString.length(), mBounds);
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.outWidth = getMeasuredWidth();
//        options.outHeight = (int) (getMeasuredWidth()*0.57);
        mWater1 = BitmapFactory.decodeResource(getResources(), R.mipmap.bg1, options);
        mWater2 = BitmapFactory.decodeResource(getResources(), R.mipmap.bg2, options);
        start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mWater1 == null)
            init();
        long offset = mOffset;
        while (offset < getMeasuredWidth()) {
            canvas.drawBitmap(mWater1, offset, getMeasuredHeight() - mWater1.getHeight(), mPaint);
            canvas.drawBitmap(mWater2, offset, getMeasuredHeight() - mWater2.getHeight(), mPaint);
            offset += mWater1.getWidth();
        }
    }
}
