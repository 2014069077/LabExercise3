package com.uy.labexercise3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    Button loadSharedPref, loadInternalStorage, loadInternalCache, back;
    TextView tvDisplay, tvDisplay2;
    SharedPreferences preferences;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        loadSharedPref = (Button) findViewById(R.id.btnLoadSharedPref);
        back = (Button) findViewById(R.id.btn_back);
        tvDisplay2 = (TextView) findViewById(R.id.tv_display2);
        preferences = getPreferences(Context.MODE_PRIVATE);
        loadInternalCache = (Button) findViewById(R.id.btnLoadInternalCache);
        loadInternalStorage = (Button) findViewById(R.id.btnLoadInternalStorage);
    }

    public void btn_back(View view) {
        finish();
    }


    public void btnclickLoadSharedPref (View view) {
        SharedPreferences preferences = getSharedPreferences("SharedPreferencesLabExer3", 0);
        String data2 = preferences.getString("data", "");
        String file2 = preferences.getString("filename", "");
        tvDisplay2.setText("from Shared Preferences");
        tvDisplay.setText("The input data is " + data2 + " and it's recorded in filename " + file2);
        tvDisplay.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void btnclickLoadInternalStorage(View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("InternalStorageLabExer3.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay2.setText("from Internal Storage");
        tvDisplay.setText("The credentials from Internal Storage are " + buffer.toString());
        tvDisplay.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void btnclickLoadInternalCache(View view) {
        tvDisplay2.setText("from Internal Cache");

    }

    public void btnclickLoadExternalCache(View view) {
        tvDisplay2.setText("from External Cache");

    }

    public void btnclickLoadExternalStorage(View view) {
        tvDisplay2.setText("from External Storage");

    }

    public void btnclickLoadExternalPublicStorage(View view) {
        tvDisplay2.setText("from External Public Storage");

    }

}