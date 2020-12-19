package com.example.dincalcemptyactivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TypeInfo extends AppCompatActivity {
    protected TextView xclose, info;
    protected ImageView skierType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeinfo);

        

        xclose = (TextView) findViewById(R.id.xbutton);
        skierType = (ImageView) findViewById(R.id.skierTypeInfo);

        xclose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return false;
            }
        });

    }
}
