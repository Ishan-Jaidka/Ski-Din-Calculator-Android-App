package com.example.dincalcemptyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    protected Button calculateButton;
    protected ToggleButton unitsButton;
    protected EditText editHeight, editWeight, editAge, editBSL;
    protected TextView dinString, errorString, skierTypeBox, clearForm, typeInfo, bslInfo;
    protected RadioGroup skierTypeGroup;
    protected RadioButton type0, type1, type2, type3, type4;
    protected TextInputLayout tileditHeight, tileditWeight, tileditAge, tileditBSL;
    protected boolean metric;
    Skier bruh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hides Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        calculateButton = (Button) findViewById(R.id.calculateButton);
        unitsButton = (ToggleButton) findViewById(R.id.unitsButton);
        dinString = (TextView) findViewById(R.id.dinString);
        errorString = (TextView) findViewById(R.id.errorString);
        skierTypeBox = (TextView) findViewById(R.id.SkierTypeBox);
        clearForm = (TextView) findViewById(R.id.clearForm);
        typeInfo = (TextView) findViewById(R.id.typeInfo);
        bslInfo = (TextView) findViewById(R.id.bslInfo);
        tileditAge = (TextInputLayout) findViewById(R.id.tileditAge);
        tileditBSL = (TextInputLayout) findViewById(R.id.tileditBSL);
        tileditWeight = (TextInputLayout) findViewById(R.id.tileditWeight);
        tileditHeight = (TextInputLayout) findViewById(R.id.tileditHeight);
        editHeight = (EditText) findViewById(R.id.editHeight);
        editWeight = (EditText) findViewById(R.id.editWeight);
        editAge = (EditText) findViewById(R.id.editAge);
        editBSL = (EditText) findViewById(R.id.editBSL);
        skierTypeGroup = (RadioGroup) findViewById(R.id.skierTypeGroup);
        type0 = (RadioButton) findViewById(R.id.skierType0);
        type1 = (RadioButton) findViewById(R.id.skierType1);
        type2 = (RadioButton) findViewById(R.id.skierType2);
        type3 = (RadioButton) findViewById(R.id.skierType3);
        type4 = (RadioButton) findViewById(R.id.skierType4);

        metric = false;
        bruh = new Skier();

        //Calls calculate() when Calculate button is touched
        calculateButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                calculate(v);
                return false;
            }
        });

        //clears all fields and textViews when "Clear Form" is touched
        clearForm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bruh.setDefault();
                clearAllFields();
                return false;
            }
        });

        //Units Toggle Listener:
        unitsButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {    //metric units
                    tileditHeight.setHint("Enter Height (cm)");
                    tileditWeight.setHint("Enter Weight (kg)");
                    /*if(!editHeight.getText().toString().isEmpty())
                        editHeight.setText((int)(Double.parseDouble(editHeight.getText().toString())*2.54));
                    if(!editWeight.getText().toString().isEmpty())
                        editWeight.setText((int)(Double.parseDouble(editHeight.getText().toString())/2.2));*/

                } else {    //standard units
                    tileditHeight.setHint("Enter Height (inches)");
                    tileditWeight.setHint("Enter Weight (lbs)");
                    /*if(!editHeight.getText().toString().isEmpty())
                        editHeight.setText((int)(Double.parseDouble(editHeight.getText().toString())/2.54));
                    if(!editWeight.getText().toString().isEmpty())
                        editWeight.setText((int)(Double.parseDouble(editHeight.getText().toString())*2.2));*/
                }
                metric = isChecked;
            }
        });

        //Height Focus Listener:
        editHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {
                        if (metric) {
                            bruh.setHeight((int) (Double.parseDouble(editHeight.getText().toString()) / 2.54));
                        } else {
                            bruh.setHeight(Integer.parseInt(editHeight.getText().toString()));
                        }
                    } catch (Exception e) {
                        dinString.setText("");
                        tileditHeight.setError("Please enter a valid height");
                    }
                } else tileditHeight.setError(null);
            }
        });

        //Weight Focus Listener:
        editWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {
                        if (metric) {
                            bruh.setWeight((int) (Double.parseDouble(editWeight.getText().toString()) * 2.2));
                        } else {
                            bruh.setWeight(Integer.parseInt(editWeight.getText().toString()));
                        }
                    } catch (Exception e) {
                        dinString.setText("");
                        tileditWeight.setError("Please enter a valid weight");
                    }
                } else tileditWeight.setError(null);
            }
        });

        //BSL Focus Listener:
        editBSL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {
                        bruh.setBsl(Integer.parseInt(editBSL.getText().toString()));
                    } catch (Exception e) {
                        dinString.setText("");
                        tileditBSL.setError("Please enter a valid BSL");
                    }
                } else tileditBSL.setError(null);
            }
        });

        //BSL Enter = perform calculate()
        editBSL.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
                    calculate(v);
                }
                return false;
            }
        });

        //Age Focus Listener:
        editAge.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {
                        bruh.setAge(Integer.parseInt(editAge.getText().toString()));
                    } catch (Exception e) {
                        dinString.setText("");
                        tileditAge.setError("Please enter a valid age");
                    }
                } else tileditAge.setError(null);
            }
        });

        skierTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                skierTypeBox.setTextColor(getResources().getColor(R.color.white));
                type4.setError(null);
            }
        });

        //opens BSL information on touch
        bslInfo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openBslInfo();
                return false;
            }
        });

        //opens Skier Type information on touch
        typeInfo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openTypeInfo();
                return false;
            }
        });
    }

    protected int getSkierType(){
        int checkID = skierTypeGroup.getCheckedRadioButtonId();
        if(checkID==type0.getId())
            return 0;
        else if(checkID==type1.getId())
            return 1;
        else if(checkID==type2.getId())
            return 2;
        else if(checkID==type3.getId())
            return 3;
        else if(checkID==type4.getId())
            return 4;
        else return -1;
    }

    //resets skier details, checks each field, then calculates DIN
    public void calculate(View view) {
        bruh.setDefault();
        editHeight.requestFocus();
        editWeight.requestFocus();
        editAge.requestFocus();
        editBSL.requestFocus();
        calculateButton.requestFocus();

        try {
            if (skierTypeGroup.getCheckedRadioButtonId() == -1) {
                dinString.setText("");
                skierTypeBox.setTextColor(getResources().getColor(R.color.error));
                type4.setError("");
                throw new IllegalArgumentException("Select skier type");
            } else {
                skierTypeBox.setTextColor(getResources().getColor(R.color.white));
                type4.setError(null);
                bruh.setSkierType(getSkierType());
                double din = bruh.calculateDin();
                errorString.setText("");
                dinString.setText("DIN: " + din);
            }
        } catch (Exception e) {
            dinString.setText("");
            errorString.setText("Please enter valid data and try again.");
        }
    }

    //clears all fields and resets skier data
    public void clearAllFields(){
        //startActivity(new Intent(this, MainActivity.class));
        skierTypeGroup.clearCheck();
        editHeight.getText().clear();
        editWeight.getText().clear();
        editAge.getText().clear();
        editBSL.getText().clear();

        dinString.setText("");
        errorString.setText("");

        clearForm.requestFocus();

        editHeight.setError(null);
        editWeight.setError(null);
        editAge.setError(null);
        editBSL.setError(null);

        bruh.setDefault();
    }

    public void openBslInfo(){
        startActivity(new Intent(this, BslInfo.class));
    }

    public void openTypeInfo(){
        startActivity(new Intent(this, TypeInfo.class));
    }
}