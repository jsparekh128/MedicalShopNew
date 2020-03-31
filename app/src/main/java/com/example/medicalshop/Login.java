package com.example.medicalshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    Button btnsignup,btnLogin;
    EditText edEmail,edPassword;
    FirebaseAuth mFirebaseAuth;
    TextView mtxtviewAdmin,mtxtviewnoAdmin;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    boolean flag=true,fadmin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        btnsignup=(Button)findViewById(R.id.btnRegister);
        mFirebaseAuth=FirebaseAuth.getInstance();
        btnLogin=(Button) findViewById(R.id.btnLogin);
        edEmail= (EditText)findViewById(R.id.editTextEmail);
        edPassword=(EditText)findViewById(R.id.editTextPassword);
        mtxtviewAdmin=findViewById(R.id.tvAdmin);
        mtxtviewnoAdmin=findViewById(R.id.tvNotAdmin);
        mtxtviewAdmin.setVisibility(View.VISIBLE);
        mtxtviewnoAdmin.setVisibility(View.INVISIBLE);
        database=FirebaseDatabase.getInstance();




        mtxtviewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin.setText(R.string.btnadminloign);
              //  edEmail.setHint(R.string.usernametxt);
               // edPassword.setHint(R.string.passtxt);
                mtxtviewAdmin.setVisibility(View.INVISIBLE);
                mtxtviewnoAdmin.setVisibility(View.VISIBLE);
                fadmin=true;
            }
        });

        mtxtviewnoAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 SharedPreferences sharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE);
                 String sharedemail = sharedPreferences.getString("email", null);
                 String sharedpassword = sharedPreferences.getString("password", null);
                    if (sharedemail != null && sharedpassword != null ) {
                     startActivity(new Intent(getApplicationContext(),HomePage.class));

                 }
                else
                    {
                        btnLogin.setText(R.string.loginbtn);
                      //  edEmail.setHint(R.string.emailtxt);
                      //  edPassword.setHint(R.string.passtxt);
                        mtxtviewAdmin.setVisibility(View.VISIBLE);
                        mtxtviewnoAdmin.setVisibility(View.INVISIBLE);
                        fadmin=false;

                    }


            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;
            final String email=edEmail.getText().toString();
            final String password=edPassword.getText().toString();
            if(TextUtils.isEmpty(email))
            {
                edEmail.setError("Please enter Email");
                flag=false;
            }
            if(TextUtils.isEmpty(password))
            {

                edPassword.setError("Please enter Password");
                flag=false;

            }
            if(!fadmin) {
                if (flag) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_LONG).show();
                            } else {
                                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", email);
                                // this should be salted
                                editor.putString("password", password);
                                editor.commit();
                                startActivity(new Intent(Login.this, HomePage.class));

                            }
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Please Enter Email/Password", Toast.LENGTH_LONG).show();
                }
            }
            else if(fadmin)
            {
                if(flag)
                {
                        databaseReference=database.getReference("Admins").child("Username");
                        databaseReference.orderByChild("password").equalTo(password).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists())
                                {
                                    startActivity(new Intent(Login.this,Admin_Home.class));
                                }
                                else
                                {
                                    Toast.makeText(Login.this, "Please Enter Admin Password Correctly!", Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                }
                else
                {
                    Toast.makeText(Login.this, "Please Enter Username/Password", Toast.LENGTH_LONG).show();
                }
            }



            }
        });


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup();
            }
        });
    }


    public void openSignup(){
        Intent i = new Intent(Login.this,Signup_Form.class);
        startActivity(i);
    }
}
