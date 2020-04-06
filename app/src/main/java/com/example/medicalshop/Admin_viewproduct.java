package com.example.medicalshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Admin_viewproduct extends AppCompatActivity {

    private  RecyclerView prorecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viewproduct);

        prorecycler=findViewById(R.id.prorecycler);

        new FirebaseDatabaseHelper().readProducts(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Products> products, List<String> key) {
                new RecyclerView_Config().setConfig(prorecycler,Admin_viewproduct.this,products,key);
            }

            @Override
            public void DataInserted() {

            }

            @Override
            public void DataDeleted() {

            }

            @Override
            public void DataUpdated() {

            }
        });
    }
}
