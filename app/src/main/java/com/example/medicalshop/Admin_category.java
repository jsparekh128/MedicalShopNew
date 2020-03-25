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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_category extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner selcat,catspin;
    TextView selcattxt;
    Button btn;
    ArrayAdapter ad;
    EditText catname;
    TextInputLayout cattxt;

    DatabaseReference  dbcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        dbcategory= FirebaseDatabase.getInstance().getReference("category");

        catspin= findViewById(R.id.catydropdown);
        selcat= findViewById(R.id.catupdropdown);
        ad= ArrayAdapter.createFromResource(this, R.array.category_item, android.R.layout.simple_spinner_item);
        selcattxt= findViewById(R.id.cattxt);
        btn= findViewById(R.id.btn);
        catname= findViewById(R.id.catname);
        cattxt=findViewById(R.id.cattxtnm);

        catspin.setAdapter(ad);
        catspin.setOnItemSelectedListener(this);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addcategory();
            }
        });

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

    private void addcategory(){
        String cname=catname.getText().toString();

        if(!TextUtils.isEmpty(cname)){
            String id=dbcategory.push().getKey();

            Category c=new Category(id,cname);
            dbcategory.child(id).setValue(c);

            Toast.makeText(this,"Category Added.",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Please enter the Category.",Toast.LENGTH_LONG).show();
        }
    }
}
