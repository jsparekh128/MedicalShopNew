package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Admin_Home extends AppCompatActivity implements View.OnClickListener {

    CardView syrupcrd,tabletcrd,ointcrd,capsulecrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

        syrupcrd=findViewById(R.id.syrupcrd);
        capsulecrd=findViewById(R.id.capsulecrd);
        tabletcrd=findViewById(R.id.tabletcrd);
        ointcrd=findViewById(R.id.ointcrd);

        syrupcrd.setOnClickListener(this);
        capsulecrd.setOnClickListener(this);
        ointcrd.setOnClickListener(this);
        tabletcrd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()){

            case R.id.capsulecrd:
                i=new Intent(this,Admin_capsule.class);
                startActivity(i);
                break;

            case R.id.ointcrd:
                i=new Intent(this,Admin_oint.class);
                startActivity(i);
                break;

            case R.id.tabletcrd:
                i=new Intent(this,Admin_tablet.class);
                startActivity(i);
                break;

            case R.id.syrupcrd:
                i=new Intent(this,Admin_syrup.class);
                startActivity(i);
                break;
        }
    }
}
