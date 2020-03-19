package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class Admin_Home extends AppCompatActivity {
    CardView corder,cprofile,caboutus,cstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

        caboutus=(CardView)findViewById(R.id.crdaboutus);
        corder=(CardView)findViewById(R.id.crdorder);
        cstore=(CardView)findViewById(R.id.crdstore);
        cprofile=(CardView)findViewById(R.id.crdprofile);

        caboutus.setOnClickListener((View.OnClickListener) this);
        corder.setOnClickListener((View.OnClickListener) this);
        cprofile.setOnClickListener((View.OnClickListener) this);
        cstore.setOnClickListener((View.OnClickListener) this);

    }
    public void onClick(View v){
        Intent i;

        switch(v.getId()){
            case R.id.crdaboutus :
                i=new Intent(this,Admin_aboutus.class);
                break;

            case R.id.crdorder :
                i=new Intent(this,Admin_order.class);
                break;

            case R.id.crdstore :
                i=new Intent(this,Admin_store.class);
                break;

            case R.id.crdprofile :
                i=new Intent(this,Admin_profile.class);
                break;
        }
    }

}
