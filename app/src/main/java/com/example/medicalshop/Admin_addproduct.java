package com.example.medicalshop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_addproduct extends AppCompatActivity implements View.OnClickListener {
 Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_addproduct);

        addbtn=findViewById(R.id.addbtn);
        addbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
