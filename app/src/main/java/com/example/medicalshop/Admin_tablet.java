package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_tablet extends AppCompatActivity implements View.OnClickListener {

    ImageButton tabletimg;
    TextView tablettxt;
    String catname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tablet);

        tablettxt=findViewById(R.id.tablettxt);
        tabletimg=findViewById(R.id.tabletimg);
        tabletimg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i=new Intent(this,Admin_addproduct.class);

        catname=tablettxt.getText().toString();
        i.putExtra("catename_msg",catname);

        startActivity(i);
    }
}
