package com.example.dincalcemptyactivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BslInfo extends AppCompatActivity {
    protected TextView xclosebsl, infobsl;
    protected ImageView bslImg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bslinfo);

        xclosebsl = (TextView) findViewById(R.id.xbuttonbsl);

        xclosebsl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return false;
            }
        });

    }
}
