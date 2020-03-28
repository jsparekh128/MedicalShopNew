package com.example.medicalshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewBinder> {
    Context context;
    ArrayList<ProductJay> productjay;
    public myAdapter(Context c,ArrayList<ProductJay> p){
        context=c;
        productjay=p;
    }
    @NonNull
    @Override
    public myViewBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewBinder(LayoutInflater.from(context).inflate(R.layout.cardview_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewBinder holder, int position) {
        holder.pname.setText(productjay.get(position).getProductname());
        holder.pqty.setText(String.valueOf(productjay.get(position).getQty()));
        holder.prate.setText(String.valueOf(productjay.get(position).getRate()));
        holder.pdescription.setText(productjay.get(position).getDescription());
        holder.pcategory.setText(productjay.get(position).getCategory());

        holder.onClick(productjay.get(position).getProductid());

    }

    @Override
    public int getItemCount() {
        return productjay.size();
    }

    class myViewBinder extends RecyclerView.ViewHolder{

        TextView pname,pqty,prate,pdescription,pcategory;
        Button btnbuy;
        public myViewBinder(@NonNull View itemView) {
            super(itemView);
            pname=(TextView)itemView.findViewById(R.id.txtpname);
          //  pqty=(TextView)itemView.findViewById(R.id.txtpqty);
            prate=(TextView)itemView.findViewById(R.id.txtprate);
            pcategory=(TextView)itemView.findViewById(R.id.txtpcategory);
           // pdescription=(TextView)itemView.findViewById(R.id.txtpdescription);
            btnbuy=(Button)itemView.findViewById(R.id.btnBuy);

        }
        public void onClick(final int id)
        {
            btnbuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,id+" is clicked ",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
