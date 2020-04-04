package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_capsule extends AppCompatActivity implements View.OnClickListener {

    ImageButton capsulimg;
    TextView capsultxt;
    String catname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_capsule);

        capsultxt=findViewById(R.id.capsultxt);
        capsulimg=findViewById(R.id.capsulimg);
        capsulimg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i=new Intent(this,Admin_addproduct.class);

        catname=capsultxt.getText().toString();

        i.putExtra("catename_msg",catname);
        startActivity(i);

    }
}
