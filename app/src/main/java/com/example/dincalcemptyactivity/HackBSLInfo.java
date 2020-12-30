package com.example.dincalcemptyactivity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HackBSLInfo extends AppCompatActivity {
    protected TextView xButton2, bslDesc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hackbslinfopopup);
        bslDesc = (TextView) findViewById(R.id.bslDesc);
        xButton2 = (TextView) findViewById(R.id.xbutton2);

        DinCalcHelpers.readFiletoTextView(this, bslDesc, "bslInfo.txt");

        //hides Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        xButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
