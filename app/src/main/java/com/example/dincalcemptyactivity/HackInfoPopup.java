package com.example.dincalcemptyactivity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HackInfoPopup extends AppCompatActivity {
    protected TextView xButton, xButton2, typeDesc, bslDesc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hackinfopopup);
        xButton = (TextView) findViewById(R.id.xbutton);
        typeDesc = (TextView) findViewById(R.id.typeDesc);
        bslDesc = (TextView) findViewById(R.id.bslDesc);
        xButton2 = (TextView) findViewById(R.id.xbutton2);

        DinCalcHelpers.readFiletoTextView(this, typeDesc, "skierTypeInfo.txt");
        DinCalcHelpers.readFiletoTextView(this, bslDesc, "bslInfo.txt");

        //hides Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        xButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        xButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
