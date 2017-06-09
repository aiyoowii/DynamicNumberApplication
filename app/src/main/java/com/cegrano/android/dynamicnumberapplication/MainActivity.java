package com.cegrano.android.dynamicnumberapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cegrano.android.dynamicnumber.BanditNumberView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BanditNumberView numberView = (BanditNumberView) findViewById(R.id.number);
        numberView.setNumber(1234);
    }
}
