package com.example.medicalshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_Order extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView ordrecyclerview;
    ArrayList<Products> list;
    myAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__order);
        ordrecyclerview=(RecyclerView)findViewById(R.id.orderRecyclerView);
        layoutManager= new LinearLayoutManager(this);
        reference= FirebaseDatabase.getInstance().getReference().child("Products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<Products>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Products p=dataSnapshot1.getValue(Products.class);
                    list.add(p);
                }
                ordrecyclerview.setLayoutManager(layoutManager);
                adapter=new myAdapter(User_Order.this,list);
                ordrecyclerview.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(User_Order.this,"Some error!",Toast.LENGTH_LONG).show();
            }
        });


    }
}
