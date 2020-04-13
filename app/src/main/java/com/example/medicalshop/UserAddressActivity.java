package com.example.medicalshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UserAddressActivity extends AppCompatActivity {
    private EditText editname,editphone,editaddress,editcity;
    Button btnconfirmOrder;
    private String totalamount="";
    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);
        editname=findViewById(R.id.editTextName);
        editphone=findViewById(R.id.editTextMobile);
        editcity=findViewById(R.id.editTextCity);
        editaddress=findViewById(R.id.editTextAddress);
        btnconfirmOrder=findViewById(R.id.btnconfirmorder);

        totalamount=getIntent().getStringExtra("TotalPrice");

        btnconfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });




    }

    private void Check()
    {
        String pname=editname.getText().toString();
        String pphone=editphone.getText().toString();
        String paddress=editaddress.getText().toString();
        String pcity=editcity.getText().toString();

        if(TextUtils.isEmpty(pname))
        {
            editname.setError("Please Enter Name");
            flag=false;

        }

        else if(TextUtils.isEmpty(pphone))
        {
            editphone.setError("Please Enter Phone");
            flag=false;

        }

        else if(TextUtils.isEmpty(pcity))
        {
            editcity.setError("Please Enter City");
            flag=false;

        }

        else if(TextUtils.isEmpty(paddress))
        {
            editaddress.setError("Please Enter Address");
            flag=false;

        }
        else
        {
            if(flag)
            {
                confirmOrder();
            }
        }
    }

    private void confirmOrder()
    {
        String pname=editname.getText().toString();
        String pphone=editphone.getText().toString();
        String paddress=editaddress.getText().toString();
        String pcity=editcity.getText().toString();

        String savecurrentdate,savecurrentime;
        Calendar calendar=Calendar.getInstance();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate= new SimpleDateFormat("MMM dd, yyyy");
        savecurrentdate=currentDate.format(calendar.getTime());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        savecurrentime=currentTime.format(calendar.getTime());

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        final String shareduserid = sharedPreferences.getString("userid", null);

        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Orders")
                .child(shareduserid);



        HashMap<String,Object> ordersMap = new HashMap<>();


        ordersMap.put("name",pname);
        ordersMap.put("phone",pphone);
        ordersMap.put("city",pcity);
        ordersMap.put("address",paddress);
        ordersMap.put("totalAmount",totalamount);
        ordersMap.put("date",savecurrentdate);
        ordersMap.put("time",savecurrentime);
        ordersMap.put("pstatus","Shipped");

        reference.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("CartList")
                            .child("UserView").child(shareduserid).removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(UserAddressActivity.this,"Your order has been placed",Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(UserAddressActivity.this,HomePage.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }
}
