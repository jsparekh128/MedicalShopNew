package com.example.medicalshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myCartListAdapter extends RecyclerView.Adapter<myCartListAdapter.myCartViewBinder> {
    Context context;
    ArrayList<CartList> cartLists;
    float amount=0;

    public myCartListAdapter(Context c, ArrayList<CartList> p) {
        context = c;
        cartLists = p;

    }

    @NonNull
    @Override
    public myCartViewBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myCartViewBinder(LayoutInflater.from(context).inflate(R.layout.cartlist_recycler,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull myCartViewBinder holder, int position) {
        holder.pname.setText(cartLists.get(position).getPname());
        holder.pamt.setText(String.valueOf(cartLists.get(position).getTotalAmt()));
        amount= amount + cartLists.get(position).getTotalAmt();

        holder.pqty.setText(String.valueOf(cartLists.get(position).getPqty()));
        holder.productid.setText(cartLists.get(position).getPid());


    }

    @Override
    public int getItemCount() {
        return cartLists.size();
    }

    class myCartViewBinder extends RecyclerView.ViewHolder{
        TextView pname,pqty,pamt,productid;
        ImageView imgord;
        public myCartViewBinder(@NonNull View itemView) {
            super(itemView);
            pname = (TextView) itemView.findViewById(R.id.txtpname);
            pqty = (TextView) itemView.findViewById(R.id.txtpqty);
            pamt = (TextView) itemView.findViewById(R.id.txtpamt);
            productid=(TextView)itemView.findViewById(R.id.txtproductid);
            imgord = (ImageView) itemView.findViewById(R.id.imgord);
        }

    }


    }


