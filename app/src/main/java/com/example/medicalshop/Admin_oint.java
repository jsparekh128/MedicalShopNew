package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_oint extends AppCompatActivity implements View.OnClickListener {

    ImageButton ointmntimg;
    TextView ointmnttxt;
    String catname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_oint);

      ointmntimg=findViewById(R.id.ointmntimg);
      ointmnttxt=findViewById(R.id.ointnmttxt);

      ointmntimg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        i=new Intent(Admin_oint.this,Admin_addproduct.class);

        catname=ointmnttxt.getText().toString();

        i.putExtra("catename_msg",catname);
        startActivity(i);
    }
}
