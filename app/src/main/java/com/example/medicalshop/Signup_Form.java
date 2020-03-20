package com.example.medicalshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Form extends AppCompatActivity {
    EditText edName,edEmail,edMobile,edPassword;
    Button btnReg;
    FirebaseDatabase database;
    DatabaseReference mDatabaseReference;
    FirebaseAuth mFirebaseAuth;
    boolean flag=true;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Sign up");
        edName = (EditText) findViewById(R.id.editTextName);
        edEmail = (EditText) findViewById(R.id.editTextEmail);
        edMobile = (EditText) findViewById(R.id.editTextMobile);
        edPassword = (EditText) findViewById(R.id.editTextPassword);
        btnReg = (Button) findViewById(R.id.btnRegister);
        btnlogin = (Button) findViewById(R.id.btnLogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        database=FirebaseDatabase.getInstance();
        mFirebaseAuth=FirebaseAuth.getInstance();


        mDatabaseReference=database.getReference("PersonDetails");
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pname=edName.getText().toString();
                final String pemail=edEmail.getText().toString();
                final String pmobile=edMobile.getText().toString();
                final String ppassword=edPassword.getText().toString();
                if(TextUtils.isEmpty(pname))
                {
                    edName.setError("Please enter Name");
                    flag=false;
                }
                if(TextUtils.isEmpty(pemail))
                {
                    edEmail.setError("Please enter Email");
                    flag=false;
                }
                if(TextUtils.isEmpty(pmobile))
                {
                    edMobile.setError("Please enter Mobile");
                    flag=false;
                }
                if(TextUtils.isEmpty(ppassword))
                {
                    edPassword.setError("Please enter Password");
                    flag=false;
                }


                if(flag)
                {
                mFirebaseAuth.signInWithEmailAndPassword(pemail, ppassword)
                        .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                            Toast.makeText(Signup_Form.this, "Registration Complete", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(),Login.class));

                                        }
                                    };

                        });

                }

                else
                {
                    Toast.makeText(Signup_Form.this,"Please Enter Valid Data",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void openLogin(){
        Intent i=new Intent(this,Login.class);
        startActivity(i);
    }
}
