package com.cegrano.android.dynamicnumber;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.cegrano.android.dynamicnumberapplication.R;

/**
 * Created by cegrano on 2017/6/9.
 * 转盘数字
 */
public class DialNumberView extends View {
    private Paint mPaint = new Paint();
    private Rect mBounds = new Rect();
    private Bitmap mDial;
    private Bitmap mArrow;
    private float mDensity;

    public DialNumberView(Context context) {
        super(context);
    }

    public DialNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DialNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        options.outWidth = getMeasuredWidth();
        options.outHeight = (int) (getMeasuredWidth() * 0.57);
        mDial = BitmapFactory.decodeResource(getResources(), R.mipmap.dial, options);
        mArrow = BitmapFactory.decodeResource(getResources(), R.mipmap.dial_arrow, new BitmapFactory.Options());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDial == null) {
            init();
        }
        canvas.drawBitmap(mDial, 0, 0, mPaint);
        canvas.drawText("1000", mDial.getWidth() / 2, 83 * mDensity + mBounds.height(), mPaint);

        canvas.drawBitmap(mArrow, (float) (mDial.getWidth() / 2 + mDial.getWidth() / 2 * Math.cos(Math.PI * 135 / 180)), (float) (mDial.getWidth() / 2 - mDial.getWidth() / 2 * Math.sin(Math.PI * 135 / 180)), mPaint);
//        canvas.translate(mDial.getWidth()/2,mDial.getWidth()/2);
//        canvas.rotate(30);
//        canvas.drawBitmap(mArrow, 0,0,mPaint);

    }
}
