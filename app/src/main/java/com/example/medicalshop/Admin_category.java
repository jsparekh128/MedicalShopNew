package com.example.medicalshop;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_category extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner selcat,catspin;
    TextView selcattxt;
    Button btn;
    ArrayAdapter choicead;
    EditText catname;
    TextInputLayout cattxt;

    String cname="";

    DatabaseReference  dbcategory;
    ValueEventListener listener;
    ArrayAdapter<String> catead;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        catspin= findViewById(R.id.catydropdown);
        selcat= findViewById(R.id.catupdropdown);
        choicead= ArrayAdapter.createFromResource(this, R.array.category_item, android.R.layout.simple_spinner_item);
        selcattxt= findViewById(R.id.cattxt);
        btn= findViewById(R.id.btn);
        catname= findViewById(R.id.catname);
        cattxt=findViewById(R.id.cattxtnm);

        catspin.setAdapter(choicead);
        catspin.setOnItemSelectedListener(this);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addcategory();
            }
        });

        dbcategory= FirebaseDatabase.getInstance().getReference("category");
        list=new ArrayList<>();
        catead=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);

        selcat.setAdapter(catead);
        retrivedata();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        btn.setText(catspin.getSelectedItem().toString());

        if(catspin.getSelectedItem().toString().equals("Add Category")){
            selcattxt.setVisibility(View.INVISIBLE);
            selcat.setVisibility(View.INVISIBLE);

            catname.setVisibility(View.VISIBLE);
            cattxt.setVisibility(View.VISIBLE);
        }

        if(catspin.getSelectedItem().toString().equals("Delete Category")){
            selcattxt.setVisibility(View.VISIBLE);
            selcat.setVisibility(View.VISIBLE);

            catname.setVisibility(View.INVISIBLE);
            cattxt.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addcategory(){

        if(!TextUtils.isEmpty(cname)){
            cname=catname.getText().toString().trim();
            dbcategory.push().setValue(cname).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    catname.setText(cname);
                    list.clear();
                    retrivedata();
                    catead.notifyDataSetChanged();
                }
            });
            Toast.makeText(this,"Category Inserted.",Toast.LENGTH_LONG).show();

        }
        else{

            Toast.makeText(this,"Please enter the Category.",Toast.LENGTH_LONG).show();
        }
    }

    public void retrivedata(){
        listener=dbcategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot catitem:dataSnapshot.getChildren()){
                    list.add(catitem.getValue().toString());
                }
                catead.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
