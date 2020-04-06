package com.example.medicalshop;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewBinder> implements Filterable {
    Context context;
    ArrayList<Products> product;
    ArrayList<Products> productAll;
    public myAdapter(Context c,ArrayList<Products> p){
        context=c;
        product=p;
        productAll=new ArrayList<>();
        productAll.addAll(p);
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
        holder.productid.setText(product.get(position).getProductid());
        holder.onClick(product.get(position).getProductid());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Products> filteredlist= new ArrayList<>();
            if(constraint == null || constraint.length()==0)
            {
                filteredlist.addAll(productAll);
            }
            else
            {
                String filter=constraint.toString().toLowerCase().trim();
                for (Products p: productAll)
                {
                    if(p.getProductname().toLowerCase().contains(filter))
                    {
                        filteredlist.add(p);

                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredlist;


            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            product.clear();
            product.addAll((Collection<? extends Products>) results.values);
            notifyDataSetChanged();
        }
    };

    class myViewBinder extends RecyclerView.ViewHolder{

        TextView pname,prate,pdescription,productid;
        Button btnadd;
        ImageView imgord;
        public myViewBinder(@NonNull View itemView) {
            super(itemView);
            pname = (TextView) itemView.findViewById(R.id.txtpname);
            prate = (TextView) itemView.findViewById(R.id.txtprate);
            pdescription = (TextView) itemView.findViewById(R.id.txtpdescription);
            btnadd = (Button) itemView.findViewById(R.id.btnadd);
            productid=(TextView)itemView.findViewById(R.id.txtproductid);
            imgord = (ImageView) itemView.findViewById(R.id.imgord);
        }

            public void onClick(final String id)
            {
                    btnadd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context,UserProductActivity.class);
                            intent.putExtra("pid",id);
                            context.startActivity(intent);

                        }
                    });

            }




        }
    }

