package com.example.dincalcemptyactivity;

import android.content.Intent;
import android.widget.TextView;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DinCalcHelpers {

    //sets the text of the given TextView to the contents of the given file
    public static void readFiletoTextView(android.content.Context c, TextView t, String filename) {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(c.getAssets().open(filename)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (Exception e) {}
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    //log the exception
                }
            }

            t.setText((CharSequence) text);
        }
    }
}
