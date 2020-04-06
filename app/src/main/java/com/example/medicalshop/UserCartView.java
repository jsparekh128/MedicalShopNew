package com.example.medicalshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserCartView extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView cartrecyclerview;
    ArrayList<CartList> list;
    ArrayList<CartList> list1;
    myCartListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button btnOrder;
    TextView txtprice;
    Float amount=0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cart_view);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String shareduserid = sharedPreferences.getString("userid", null);
        btnOrder=(Button)findViewById(R.id.btnorder);
        txtprice=(TextView)findViewById(R.id.txttotalprice);
        cartrecyclerview=(RecyclerView)findViewById(R.id.cartRecyclerView);
        layoutManager= new LinearLayoutManager(this);
        reference= FirebaseDatabase.getInstance().getReference().child("CartList").child("UserView")
        .child(shareduserid).child("Products");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list=new ArrayList<CartList>();
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            CartList p=dataSnapshot1.getValue(CartList.class);
                            list.add(p);
                        }
                        cartrecyclerview.setLayoutManager(layoutManager);
                        adapter=new myCartListAdapter(UserCartView.this,list);
                        cartrecyclerview.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(UserCartView.this,"Some error!",Toast.LENGTH_LONG).show();
                    }
                });
                btnOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        for(int i=0;i<adapter.getItemCount();i++)
                        {
                            amount = amount + list.get(i).getTotalAmt();

                        }
                        Toast.makeText(UserCartView.this, amount+ " total ",Toast.LENGTH_LONG).show();

                    }
                });

    }
}
