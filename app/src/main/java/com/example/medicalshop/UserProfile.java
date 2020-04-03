package com.example.medicalshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    TextView pName,pEmail,pMobile;
    Button btnlogout;
    DatabaseReference reference;
    FirebaseAuth mauth;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        pName=(TextView)findViewById(R.id.profilename);
        pEmail=(TextView)findViewById(R.id.profilemail);
        pMobile=(TextView)findViewById(R.id.profilemobileno);
        btnlogout=(Button) findViewById(R.id.btnlogout);
        mauth=FirebaseAuth.getInstance();
        userId=mauth.getCurrentUser().getUid();


        reference= FirebaseDatabase.getInstance().getReference().child("PersonDetails").child(userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String username=dataSnapshot.child("pEmail").getValue().toString();
                    String useremail=dataSnapshot.child("pName").getValue().toString();
                    String usermobile=dataSnapshot.child("pMobile").getValue().toString();
                    pName.setText(username);
                    pEmail.setText(useremail);
                    pMobile.setText(usermobile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(UserProfile.this,Login.class));

            }
        });


    }
}
