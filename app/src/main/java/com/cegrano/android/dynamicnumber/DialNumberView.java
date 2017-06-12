package com.cegrano.android.dynamicnumber;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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
    private float degree = -90;
    private Rect mRect;
    private RectF mRectF;

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
//        mPaint.setTextSize(44 * mDensity);
        mPaint.setAntiAlias(true);

//        String testString = "1000";
//        mPaint.setStrokeWidth(3);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        mPaint.getTextBounds(testString, 0, testString.length(), mBounds);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = getMeasuredWidth();
        options.outHeight = getMeasuredHeight();
        options.inScaled = true;
        mDial = BitmapFactory.decodeResource(getResources(), R.mipmap.dial, options);
        mArrow = BitmapFactory.decodeResource(getResources(), R.mipmap.dial_arrow, new BitmapFactory.Options());
        mRect = new Rect(0, 0, mDial.getWidth(), mDial.getHeight());
        mRectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void setNumber(int number) {
        degree = (number - 500) / 4500f * 180 - 180;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDial == null) {
            init();
        }
        canvas.drawBitmap(mDial, mRect, mRectF, mPaint);
//        canvas.drawBitmap(mDial, 0, 0, mPaint);
//        canvas.drawText("1000", mDial.getWidth() / 2, 83 * mDensity + mBounds.height(), mPaint);

//        canvas.drawBitmap(mArrow, (float) (mDial.getWidth() / 2 + mDial.getWidth() / 2 * Math.cos(Math.PI * degree)), (float) (mDial.getWidth() / 2 - mDial.getWidth() / 2 * Math.sin(Math.PI * degree)), mPaint);
        canvas.translate((float) (getMeasuredWidth() / 2 + getMeasuredWidth() * 0.73 / 2 * Math.cos(Math.PI * degree / 180) - mArrow.getWidth() / 2),
                (float) (getMeasuredWidth() / 2 + getMeasuredWidth() * 0.73 / 2 * Math.sin(Math.PI * degree / 180)) - mArrow.getHeight() / 2);
        canvas.rotate(degree + 90);
        canvas.drawBitmap(mArrow, 0, 0, mPaint);

    }
}
