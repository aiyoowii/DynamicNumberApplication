package com.cegrano.android.dynamicnumberapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cegrano.android.dynamicnumber.DynamicNumberView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DynamicNumberView numberView = (DynamicNumberView) findViewById(R.id.number);
        numberView.setNumber(1234);
    }
}
