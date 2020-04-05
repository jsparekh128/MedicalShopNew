package com.example.medicalshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UserProductActivity extends AppCompatActivity {
    TextView txtpname,txtprate,txtpcategory,txtpcontent;
    Button btnBuy;
    ElegantNumberButton btnQty;
    RelativeLayout relativelayout;
    String productid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product);
        txtpname=findViewById(R.id.txtproductname);
        txtprate=findViewById(R.id.txtproductrate);
        txtpcategory=findViewById(R.id.txtproductcategory);
        txtpcontent=findViewById(R.id.txtproductcontent);
        btnQty=findViewById(R.id.btnqty);
        btnBuy=findViewById(R.id.btnbuy);
        relativelayout=findViewById(R.id.relativeLayout);
        productid=getIntent().getStringExtra("pid");
        getProductDetails(productid);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingToCartList();
            }
        });
    }

    private void addingToCartList() {
    DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("CartList");
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String shareduserid = sharedPreferences.getString("userid", null);
        final HashMap<String,Object> cartMap= new HashMap<>();


        cartMap.put("pid",productid);
        cartMap.put("pname",txtpname.getText().toString());
        cartMap.put("pqty",btnQty.getNumber());
        cartMap.put("prate",txtprate.getText().toString());
        cartMap.put("pcategory",txtpcategory.getText().toString());
        cartMap.put("pcontent",txtpcontent.getText().toString());
        int qty=Integer.parseInt(btnQty.getNumber());
        Float rate=Float.parseFloat(txtprate.getText().toString());
        Float amt= qty * rate;
        cartMap.put("totalAmt",amt);
        cartMap.put("pstatus","Pending");

        cartListRef.child("UserView").child(shareduserid).child("Products").child(productid).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Snackbar snackbar=Snackbar.make(relativelayout,"Go to Cart",Snackbar.LENGTH_INDEFINITE)
                            .setAction("Click Here", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(UserProductActivity.this,UserCartView.class);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });
    }

    private void getProductDetails(String productid) {
        DatabaseReference productref= FirebaseDatabase.getInstance().getReference().child("Products");
        productref.child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Products product= dataSnapshot.getValue(Products.class);
                    txtpname.setText(product.getProductname());
                    txtprate.setText(String.valueOf(product.getProductprice()));
                    txtpcategory.setText(product.getCategoryname());
                    txtpcontent.setText(product.getProductcontnt());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
