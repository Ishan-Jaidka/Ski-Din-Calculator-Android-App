package com.example.dincalcemptyactivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TypeInfo extends AppCompatActivity {
    protected TextView xClose, info;
    protected ImageView infoPic;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeinfo);

        //final float scale = getBaseContext().getResources().getDisplayMetrics().density;
        infoPic = (ImageView) findViewById(R.id.infoPic);
        xClose = (TextView) findViewById(R.id.xbutton);

        //ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.view.getLayoutParams();
        //infoPic.setImageDrawable(pic);

        //params.topMargin = (int)(pos * scale + 0.5f);
        //v.setLayoutParams(params);

        //hides Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        xClose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return false;
            }
        });

        setFinishOnTouchOutside(true);

        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

    }
}
