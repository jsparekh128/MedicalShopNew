package com.example.medicalshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class RecyclerView_Config {
    private Context pcontext;

    public void setConfig(RecyclerView recyclerView, Context context, List<Products> products, List<String> key){
        pcontext=context;
        ProductsAdaptor productsAdaptor = new ProductsAdaptor(products, key);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(productsAdaptor);
    }

    class ProductItemView extends ViewHolder {
        private TextView proname;
        private TextView procontent;
        private TextView catname;
        private TextView price;
        private ImageButton deletebtn;

        private String key;

        public ProductItemView(ViewGroup parent){
            super(LayoutInflater.from(pcontext).inflate(R.layout.product_list_item,parent,false));

            deletebtn=itemView.findViewById(R.id.deletebtn);
            proname= itemView.findViewById(R.id.proname);
            procontent=itemView.findViewById(R.id.procontent);
            catname=itemView.findViewById(R.id.catname);
            price=itemView.findViewById(R.id.price);

            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new FirebaseDatabaseHelper().deleteProduct(key, new FirebaseDatabaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Products> products, List<String> key) {

                        }

                        @Override
                        public void DataInserted() {

                        }

                        @Override
                        public void DataDeleted() {
                            Log.d("Data Deleted", String.valueOf(deletebtn));
                        }

                        @Override
                        public void DataUpdated() {

                        }
                    });
                }
            });
        }

        public void bind(Products products,String key){
            proname.setText(products.getProductname());
            procontent.setText(products.getProductcontnt());
            catname.setText(products.getCategoryname());
            price.setText( products.getProductprice().toString());
            this.key=key;
        }
    }

    class ProductsAdaptor extends RecyclerView.Adapter<ProductItemView>{
        private List<Products> productsList;
        private List<String> keylist;

        public ProductsAdaptor(List<Products> productsList, List<String> keylist) {
            this.productsList = productsList;
            this.keylist = keylist;
        }

        @NonNull
        @Override
        public ProductItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductItemView holder, int position) {
            holder.bind(productsList.get(position),keylist.get(position));
        }

        @Override
        public int getItemCount() {
            return productsList.size();
        }
    }
}
