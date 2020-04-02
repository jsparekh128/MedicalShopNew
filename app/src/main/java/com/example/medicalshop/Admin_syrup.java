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

public class Admin_syrup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner choicespinner,categoryspinner;
    TextView selcategory;
    EditText categoryname;
    TextInputLayout categorytxt;
    Button btn;
    ArrayAdapter ad;
    String cattxt;
    DatabaseReference dbref;
    ValueEventListener listener;
    ArrayAdapter<String> categoryad;
    ArrayList<String> categorylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tablet);

        choicespinner=findViewById(R.id.catydropdown);
        categoryspinner=findViewById(R.id.catupdropdown);
        selcategory=findViewById(R.id.cattxt);
        categoryname=findViewById(R.id.catname);
        categorytxt=findViewById(R.id.cattxtnm);
        btn=findViewById(R.id.btn);

        ad=ArrayAdapter.createFromResource(this,R.array.category_item,android.R.layout.simple_spinner_item);
        dbref= FirebaseDatabase.getInstance().getReference("Category");

        categorylist=new ArrayList<>();
        categoryad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,categorylist);

        choicespinner.setAdapter(ad);
        choicespinner.setOnItemSelectedListener(this);

        categoryspinner.setAdapter(categoryad);
        categoryspinner.setOnItemSelectedListener(this);
        retrivedata();

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(btn.getText().toString().equals("Add Category")){
                   addcategory(v);
               }
               else if(btn.getText().toString().equals("Delete Category")){
                   deletedata();
               }
           }
       });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (view.getId()){
            case R.id.catydropdown:
                btn.setText(choicespinner.getSelectedItem().toString());

                if (choicespinner.getSelectedItem().toString().equals("Add Category")) {
                    selcategory.setVisibility(View.INVISIBLE);
                    categoryspinner.setVisibility(View.INVISIBLE);

                    categorytxt.setVisibility(View.VISIBLE);
                    categoryname.setVisibility(View.VISIBLE);
                }

                if (choicespinner.getSelectedItem().toString().equals("Delete Category")) {
                    selcategory.setVisibility(View.VISIBLE);
                    categoryspinner.setVisibility(View.VISIBLE);

                    categorytxt.setVisibility(View.INVISIBLE);
                    categoryname.setVisibility(View.INVISIBLE);
                }

                break;

            case R.id.catdropdown:
                String s=categoryspinner.getSelectedItem().toString();
                 dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         for(DataSnapshot dss:dataSnapshot.getChildren()){

                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });

                break;
        }


    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addcategory(View view) {
        cattxt=categoryname.getText().toString().trim();
        if(!TextUtils.isEmpty(cattxt)) {
            dbref.push().setValue(cattxt).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    categoryname.setText("");
                    categorylist.clear();
                    retrivedata();
                    categoryad.notifyDataSetChanged();
                }
            });
            Toast.makeText(this, "Category Inserted", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"Please enter category name.",Toast.LENGTH_LONG).show();
    }

    public void retrivedata(){
        listener=dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot categ:dataSnapshot.getChildren()){

                    categorylist.add(categ.getValue().toString());
                }
                categoryad.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deletedata(){

    }
}
