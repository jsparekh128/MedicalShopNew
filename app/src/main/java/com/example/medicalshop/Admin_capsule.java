package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Admin_capsule extends AppCompatActivity implements View.OnClickListener {

    ImageButton capsulimg;
    TextView capsultxt;
    public String catname;
    ListView listview1;
    DatabaseReference dbref;
    Query queryref;
    long maxid=0;
    ArrayList<String> arrlist=new ArrayList<>();
    ArrayAdapter<String> ad;
    Products p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_capsule);

        catname=capsultxt.getText().toString();

        dbref= FirebaseDatabase.getInstance().getReference("Products");
        queryref=dbref.orderByChild("categoryname").equalTo(catname);

        ad=new ArrayAdapter<String>(this,R.layout.product_info,R.id.proinfo,arrlist);

        capsultxt=findViewById(R.id.capsultxt);
        listview1=findViewById(R.id.listView1);
        capsulimg=findViewById(R.id.capsulimg);

        capsulimg.setOnClickListener(this);

        p=new Products();

    }

    @Override
    public void onClick(View v) {

        Intent i=new Intent(this,Admin_addproduct.class);

        i.putExtra("catename_msg",catname);
        startActivity(i);

    }
}
