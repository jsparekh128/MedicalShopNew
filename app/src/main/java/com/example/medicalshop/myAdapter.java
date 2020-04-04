package com.example.medicalshop;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewBinder> {
    Context context;
    ArrayList<Products> product;
    public myAdapter(Context c,ArrayList<Products> p){
        context=c;
        product=p;
    }
    @NonNull
    @Override
    public myViewBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewBinder(LayoutInflater.from(context).inflate(R.layout.cardview_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewBinder holder, int position) {
        holder.pname.setText(product.get(position).getProductname());
        holder.prate.setText(String.valueOf(product.get(position).getProductprice()));
        holder.pdescription.setText(String.valueOf(product.get(position).getProductcontnt()));
        holder.onClick(Integer.parseInt(product.get(position).getProductid()));
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    class myViewBinder extends RecyclerView.ViewHolder{

        TextView pname,prate,pdescription;
        Button btnadd;
        ImageView imgord;
        public myViewBinder(@NonNull View itemView) {
            super(itemView);
            pname = (TextView) itemView.findViewById(R.id.txtpname);
            prate = (TextView) itemView.findViewById(R.id.txtprate);
            pdescription = (TextView) itemView.findViewById(R.id.txtpdescription);
            btnadd = (Button) itemView.findViewById(R.id.btnadd);
            imgord = (ImageView) itemView.findViewById(R.id.imgord);
        }

            public void onClick(final int id)
            {
                    btnadd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,id + "is clicked",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context,UserProductActivity.class);
                            intent.putExtra("pid",product.get(id).getProductid());
                            context.startActivity(intent);

                        }
                    });

            }




        }
    }

