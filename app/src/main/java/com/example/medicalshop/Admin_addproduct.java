package com.example.medicalshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_addproduct extends AppCompatActivity implements View.OnClickListener {

    EditText proid,proname,procon,proprice,cateoname;
    Button addbtn;
    DatabaseReference dbref;
    String catname;
    Products p;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_addproduct);

        cateoname=findViewById(R.id.cateoname);
        proid=findViewById(R.id.proid);
        proname=findViewById(R.id.proname);
        procon=findViewById(R.id.procon);
        proprice=findViewById(R.id.proprice);
        addbtn=findViewById(R.id.addbtn);
        dbref= FirebaseDatabase.getInstance().getReference().child("Products");
        p=new Products();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        addbtn.setOnClickListener(this);

        Intent i=getIntent();
        String data=i.getStringExtra("catename_msg");

        catname=data;

        cateoname.setText(data);
        proid.setText(String.valueOf(maxid+1));
    }

    @Override
    public void onClick(View v) {

            Float price=Float.parseFloat(proprice.getText().toString().trim());

            p.setProductname(proname.getText().toString().trim());
            p.setProductcontnt(procon.getText().toString().trim());
            p.setProductprice(price);
            p.setCategoryname(cateoname.getText().toString().trim());
            p.setProductid(String.valueOf(maxid+1));
            dbref.child(String.valueOf(maxid+1)).setValue(p);

            Toast.makeText(this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();

            if(catname.equals("Ointment")) {
                Intent intent = new Intent(this, Admin_oint.class);
                startActivity(intent);
            }

            if(catname.equals("Syrup")) {
                 Intent intent = new Intent(this, Admin_syrup.class);
                 startActivity(intent);
             }

            if(catname.equals("Capsule")) {
                 Intent intent = new Intent(this, Admin_capsule.class);
                 startActivity(intent);
            }

            if(catname.equals("Tablet")) {
                 Intent intent = new Intent(this, Admin_tablet.class);
                 startActivity(intent);
          }
        }

}
