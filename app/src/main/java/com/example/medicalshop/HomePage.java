package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CardView crdOrder, crdProfile, crdStore, crdAboutUs;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        crdOrder=(CardView) findViewById(R.id.crdorder);
        crdProfile=(CardView) findViewById(R.id.crdprofile);
        crdAboutUs=(CardView) findViewById(R.id.crdaboutus);
        crdStore=(CardView) findViewById(R.id.crdstore);

        crdOrder.setOnClickListener(this);
        crdAboutUs.setOnClickListener(this);
        crdStore.setOnClickListener(this);
        crdProfile.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId())
        {
            case R.id.crdorder:
                i=new Intent(this,User_Order.class);
                startActivity(i);
                break;
        }

    }
}
