package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Admin_Home extends AppCompatActivity implements View.OnClickListener {

    CardView syrupcrd,tabletcrd,ointcrd,capsulecrd,viewcrd;
    TextView capsuletxt,ointtxt,tablettxt,syruptxt;
    String catname;
    ImageButton logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

        syrupcrd=findViewById(R.id.syrupcrd);
        capsulecrd=findViewById(R.id.capsulecrd);
        tabletcrd=findViewById(R.id.tabletcrd);
        ointcrd=findViewById(R.id.ointcrd);
        viewcrd=findViewById(R.id.viewcrd);

        logoutbtn=findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(this);

        capsuletxt=findViewById(R.id.capsuletxt);
        ointtxt=findViewById(R.id.ointtxt);
        syruptxt=findViewById(R.id.syruptxt);
        tablettxt=findViewById(R.id.tablettxt);

        syrupcrd.setOnClickListener(this);
        capsulecrd.setOnClickListener(this);
        ointcrd.setOnClickListener(this);
        tabletcrd.setOnClickListener(this);
        viewcrd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()){

            case R.id.capsulecrd:
                i=new Intent(this,Admin_addproduct.class);

                catname=capsuletxt.getText().toString();
                i.putExtra("catename_msg",catname);

                startActivity(i);
                break;

            case R.id.ointcrd:
                i=new Intent(this,Admin_addproduct.class);

                catname=ointtxt.getText().toString();
                i.putExtra("catename_msg",catname);

                startActivity(i);
                break;

            case R.id.tabletcrd:
                i=new Intent(this,Admin_addproduct.class);

                catname=tablettxt.getText().toString();
                i.putExtra("catename_msg",catname);

                startActivity(i);
                break;

            case R.id.syrupcrd:
                i=new Intent(this,Admin_addproduct.class);

                catname=syruptxt.getText().toString();
                i.putExtra("catename_msg",catname);

                startActivity(i);
                break;

            case R.id.viewcrd:
                i=new Intent(this,Admin_viewproduct.class);
                startActivity(i);
                break;

            case R.id.logoutbtn:
                i=new Intent(this,Login.class);
                startActivity(i);
        }
    }
}
