package com.example.medicalshop;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private DatabaseReference dbref;
    private List<Products> products=new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Products> products,List<String> key);
        void DataInserted();
        void DataDeleted();
        void DataUpdated();
    }
    public FirebaseDatabaseHelper() {
        FirebaseDatabase mdb = FirebaseDatabase.getInstance();
        dbref= mdb.getReference("Products");
    }

    public void readProducts(final DataStatus dataStatus){
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Products p=keyNode.getValue(Products.class);
                    products.add(p);
                }
                dataStatus.DataIsLoaded(products,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
