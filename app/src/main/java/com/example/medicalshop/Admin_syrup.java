package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_syrup extends AppCompatActivity implements View.OnClickListener {

    ImageButton syrupimg;
    TextView syruptxt;
    String catname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_syrup);

        syruptxt=findViewById(R.id.syruptxt);

        syrupimg=findViewById(R.id.syrupimg);
        syrupimg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i=new Intent(this,Admin_addproduct.class);

        catname=syruptxt.getText().toString();

        i.putExtra("catename_msg",catname);
        startActivity(i);
    }
}
