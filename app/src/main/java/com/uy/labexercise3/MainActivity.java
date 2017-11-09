package com.uy.labexercise3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etData, etFilename;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = (EditText) findViewById(R.id.etData);
        etFilename = (EditText) findViewById(R.id.etFilename);
        preferences =getPreferences(Context.MODE_PRIVATE);

    }

    public void goNext (View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void sharedPref(View view) {
        SharedPreferences preferences = getSharedPreferences("SharedPreferencesLabExer3", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", etData.getText().toString());
        editor.putString("filename", etFilename.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data saved in Shared Preferences", Toast.LENGTH_SHORT).show();
    }

    public void internalStorage (View view) {
        String data = etData.getText().toString();
        String filename = etFilename.getText().toString();
        String blank = ("\r\n");

        try {
            fos = openFileOutput("InternalStorageLabExer3.txt", Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.write(blank.getBytes());
            fos.write(filename.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Data saved in Internal Storage", Toast.LENGTH_SHORT).show();
    }

    public void internalCache (View view) {
        String userFileName = etFilename.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder, userFileName + ".txt");

        FileOutputStream fos = null;
        String message = etData.getText().toString();
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Toast.makeText(this, "Data saved in Internal Cache", Toast.LENGTH_SHORT).show();
    }

    public void externalCache (View view) {
        String userFileName = etFilename.getText().toString();
        File folder = getExternalCacheDir();
        File file = new File(folder, userFileName + ".txt");
        String message = etData.getText().toString();
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Toast.makeText(this, "Data saved in External Cache", Toast.LENGTH_SHORT).show();
    }


    public void externalStorage (View view) {
        String userFileName = etFilename.getText().toString();
        File folder = getExternalFilesDir("Uy");
        File file = new File(folder, userFileName + ".txt");
        String message = etData.getText().toString();
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Data saved to External Storage", Toast.LENGTH_SHORT).show();
        }

    }

    public void extPublicStorage (View view) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "myText4.txt");
        String message = etData.getText().toString();
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Data saved to External Public", Toast.LENGTH_SHORT).show();
        }

    }

}

