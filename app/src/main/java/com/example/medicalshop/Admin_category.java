package com.example.medicalshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_category extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner selcat,catspin;
    TextView selcattxt;
    Button btn;
    ArrayAdapter ad;
    EditText catname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        catspin= findViewById(R.id.catydropdown);
        selcat= findViewById(R.id.catupdropdown);
        ad= ArrayAdapter.createFromResource(this, R.array.category_item, android.R.layout.simple_spinner_item);
        selcattxt= findViewById(R.id.cattxt);
        btn= findViewById(R.id.btn);
        catname= findViewById(R.id.catname);

        catspin.setAdapter(ad);
        catspin.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        btn.setText(catspin.getSelectedItem().toString());

        if(catspin.getSelectedItem().toString().equals("Add Category")){
            selcattxt.setVisibility(View.INVISIBLE);
            selcat.setVisibility(View.INVISIBLE);
        }

        if(catspin.getSelectedItem().toString().equals("Delete Category")){
            selcattxt.setVisibility(View.VISIBLE);
            selcat.setVisibility(View.VISIBLE);

            catname.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
