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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

class Login extends AppCompatActivity {
    Button btnsignup,btnLogin;
    EditText edEmail,edPassword;
    FirebaseAuth mFirebaseAuth;
    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        btnsignup=(Button)findViewById(R.id.signupbtn);
        mFirebaseAuth=FirebaseAuth.getInstance();
        btnLogin=(Button) findViewById(R.id.loginbtn);
        edEmail=(EditText) findViewById(R.id.mobedittxt);
        edPassword=(EditText) findViewById(R.id.pwdedittxt);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            if(flag)
            {
                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"Authentication failed",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", email);
                            // this should be salted
                            editor.putString("password", password);
                            editor.commit();
                            startActivity(new Intent(getApplicationContext(),HomePage.class));

                        }
                    }
                });
            }
            else
            {
                Toast.makeText(Login.this,"Please Enter Email/Password",Toast.LENGTH_LONG).show();
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
        Intent i = new Intent(this,Signup_Form.class);
        startActivity(i);
    }
}
