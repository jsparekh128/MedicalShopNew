package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Admin_Home extends AppCompatActivity implements View.OnClickListener {
    Button probtn,catbtn,viewbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

        probtn=(Button)findViewById(R.id.productbtn);
        catbtn=(Button)findViewById(R.id.categorybtn);
        viewbtn=(Button)findViewById(R.id.ordviewbtn);

        probtn.setOnClickListener(this);
        catbtn.setOnClickListener(this);
        viewbtn.setOnClickListener(this);

    }
    public void onClick(View v){
        Intent i;

        switch(v.getId()){
            case R.id.productbtn:
                i=new Intent(this, Admin_product.class);
                startActivity(i);
                break;

            case R.id.categorybtn :
                i=new Intent(this,Admin_category.class);
                startActivity(i);
                break;

            case R.id.ordviewbtn :
                i=new Intent(this,Admin_order.class);
                startActivity(i);
                break;

        }
    }

}
