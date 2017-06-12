package com.cegrano.android.dynamicnumberapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cegrano.android.dynamicnumber.BanditNumberView;
import com.cegrano.android.dynamicnumber.DialNumberView;
import com.cegrano.android.dynamicnumber.ShakeNumberView;
import com.cegrano.android.dynamicnumber.WaterRippleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView numberTextView = (TextView) findViewById(R.id.number_tv);
        BanditNumberView numberView = (BanditNumberView) findViewById(R.id.number);
        ShakeNumberView shakeNumberView = (ShakeNumberView) findViewById(R.id.shake_number);
        numberView.setNumber(1234);
        shakeNumberView.setNumber(1234);
        final WaterRippleView rippleView = (WaterRippleView) findViewById(R.id.water);
        rippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rippleView.toggle();
            }
        });
        final DialNumberView dialNumberView = (DialNumberView) findViewById(R.id.dial);
        new CountDownTimer(Long.MAX_VALUE, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                numberTextView.setText(millisUntilFinished % 10000 + "");
                dialNumberView.setNumber((int) (millisUntilFinished % 4500 + 500));
            }

            @Override
            public void onFinish() {
                dialNumberView.setNumber(1100);
            }
        }.start();
    }
}
