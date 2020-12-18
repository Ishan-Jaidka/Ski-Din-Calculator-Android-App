package com.example.dincalcemptyactivity;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserAgreement extends AppCompatActivity {
    protected Button agreeButton;
    protected TextView agreement, agreeTitle, agreementInfo, uaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);

        agreeButton = (Button) findViewById(R.id.agreeButton);
        agreement = (TextView) findViewById(R.id.agreement);
        //agreeTitle = (TextView) findViewById(R.id.agreeTitle);
        uaButton =  (TextView) findViewById(R.id.uaButton);
       // agreementInfo = (TextView) findViewById(R.id.agreementInfo);

        //hides Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //DinCalcHelpers.readFiletoTextView(this, agreementInfo, "agreementInfo.txt");

        DinCalcHelpers.readFiletoTextView(this, agreement, "userAgreement.txt");

        //Agree Button OnClickListener - calls helper method to open main activity
        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(MainActivity.class);
            }
        });

        uaButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openNewActivity(FullTextUA.class);
                return false;
            }
        });
    }

    //helper method opens main activity when called
    private void openNewActivity(Class<?> cls){
        startActivity(new Intent(this, cls));
    }
}