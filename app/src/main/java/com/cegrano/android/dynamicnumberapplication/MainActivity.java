package com.cegrano.android.dynamicnumberapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cegrano.android.dynamicnumber.BanditNumberView;
import com.cegrano.android.dynamicnumber.WaterRippleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BanditNumberView numberView = (BanditNumberView) findViewById(R.id.number);
        numberView.setNumber(1234);
        final WaterRippleView rippleView = (WaterRippleView) findViewById(R.id.water);
        rippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rippleView.toggle();
            }
        });
    }
}
