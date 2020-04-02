package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_capsule extends AppCompatActivity implements View.OnClickListener {

    ImageButton capsulimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_capsule);

        capsulimg=findViewById(R.id.capsulimg);
        capsulimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(this,Admin_addproduct.class);
        startActivity(i);
    }
}
