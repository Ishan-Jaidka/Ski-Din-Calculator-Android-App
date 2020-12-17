package com.example.dincalcemptyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserAgreement extends AppCompatActivity {
    protected Button agreeButton;
    public TextView agreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);

        agreeButton = (Button) findViewById(R.id.agreeButton);
        agreement = (TextView) findViewById(R.id.agreement);

        //hides Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ReadTxtToTextView.readFiletoTextView(this, agreement, "userAgreement.txt");

        //Agree Button OnClickListener - calls helper method to open main activity
        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }

    //helper method opens main activity when called
    private void openNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}