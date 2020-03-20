package com.example.medicalshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=4000;
   Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences sharedPreferences = context.getSharedPreferences("preferences",Context.MODE_PRIVATE);
                String email = sharedPreferences.getString("email", null);
                String password = sharedPreferences.getString("password", null);
                if (email != null && password != null ) {
                    startActivity(new Intent(getApplicationContext(),HomePage.class));

                }
                else
                {
                    startActivity(new Intent(getApplicationContext(),Signup_Form.class));
                }


            }
        },SPLASH_TIME_OUT);
        }

    }

