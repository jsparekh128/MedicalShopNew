package com.example.medicalshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=4000;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                db=openOrCreateDatabase("PersonDetails", Context.MODE_PRIVATE, null);
                Cursor c=db.rawQuery("SELECT * FROM person",null);
                if(c.getCount()==0)
                {
                    Intent logini=new Intent(MainActivity.this,Signup_Form.class);
                    startActivity(logini);
                    finish();
                }
                else
                {
                    Intent logini=new Intent(MainActivity.this,HomePage.class);
                    startActivity(logini);
                    finish();
                }
                c.close();

            }
        },SPLASH_TIME_OUT);
        }
    }

