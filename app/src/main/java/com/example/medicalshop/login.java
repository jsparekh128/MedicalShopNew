package com.example.medicalshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        btnsignup=(Button)findViewById(R.id.signupbtn);

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
