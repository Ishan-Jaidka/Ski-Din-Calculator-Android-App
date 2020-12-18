package com.example.dincalcemptyactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FullTextUA extends AppCompatActivity {
    protected Button fullAgreeButton;
    protected TextView fullAgreement, fullAgreeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulltext_agreement);

        fullAgreeButton = (Button) findViewById(R.id.fullAgreeButton);
        fullAgreement = (TextView) findViewById(R.id.fullAgreement);
        fullAgreeTitle = (TextView) findViewById(R.id.fullAgreeTitle);

        //hides Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        DinCalcHelpers.readFiletoTextView(this, fullAgreement, "fullAgreement.txt");

        //Agree Button OnClickListener - calls helper method to open main activity
        fullAgreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(MainActivity.class);
            }
        });

    }

    //helper method opens main activity when called
    private void openNewActivity(Class<?> cls){
        startActivity(new Intent(this, cls));
    }
}
