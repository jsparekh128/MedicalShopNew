package com.example.medicalshop;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_viewproduct extends AppCompatActivity {

    ListView listview1;
    DatabaseReference dbref;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    Products p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viewproduct);

        listview1=findViewById(R.id.listView1);

        dbref=FirebaseDatabase.getInstance().getReference("Products");

        list=new ArrayList<>();

        adapter=new ArrayAdapter<String>(this,R.layout.product_info,R.id.proinfo,list);

        listview1.setAdapter(adapter);

        p=new Products();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dss:dataSnapshot.getChildren()){
                    p=dss.getValue(Products.class);
                    assert p != null;
                    list.add(p.getProductid().toString()+")  Category Name :  "+p.getCategoryname().toString());
                    list.add("  Product Name : "+p.getProductname().toString());
                    list.add("  Product Content : "+p.getProductcontnt().toString());
                    list.add("  Product Price : "+p.getProductprice().toString());
                    list.add("");

                }
                listview1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
