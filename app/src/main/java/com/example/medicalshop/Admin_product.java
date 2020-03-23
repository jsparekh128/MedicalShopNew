package com.example.medicalshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Admin_product extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner prospin,proupdwn;
    ArrayAdapter ad;
    Button btn;
    TextView pname;
    EditText prname,prprice,prcontent;
    TextInputLayout procon,propri,pronm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);

        prospin= findViewById(R.id.prodropdown);
        proupdwn= findViewById(R.id.proupdropdown);
        ad= ArrayAdapter.createFromResource(this, R.array.product_item, android.R.layout.simple_spinner_item);
        btn= findViewById(R.id.btn);
        pname= findViewById(R.id.protxt);
        prname= findViewById(R.id.proname);
        prprice= findViewById(R.id.proprice);
        prcontent= findViewById(R.id.procontent);
        procon= findViewById(R.id.procon);
        propri= findViewById(R.id.propri);
        pronm= findViewById(R.id.pronm);

        prospin.setAdapter(ad);
        prospin.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        btn.setText(prospin.getSelectedItem().toString());

        if(prospin.getSelectedItem().toString().equals("Add Product")){
            pname.setVisibility(View.INVISIBLE);
            proupdwn.setVisibility(View.INVISIBLE);

            prname.setVisibility(View.VISIBLE);
            pronm.setVisibility(View.VISIBLE);

            prprice.setVisibility(View.VISIBLE);
            propri.setVisibility(View.VISIBLE);

            prcontent.setVisibility(View.VISIBLE);
            procon.setVisibility(View.VISIBLE);
        }

        if(prospin.getSelectedItem().toString().equals("Update Product")){
            pname.setVisibility(View.VISIBLE);
            proupdwn.setVisibility(View.VISIBLE);

            prname.setVisibility(View.VISIBLE);
            pronm.setVisibility(View.VISIBLE);

            prprice.setVisibility(View.VISIBLE);
            propri.setVisibility(View.VISIBLE);

            prcontent.setVisibility(View.VISIBLE);
            procon.setVisibility(View.VISIBLE);
        }

        if(prospin.getSelectedItem().toString().equals("Delete Product")){
            pname.setVisibility(View.VISIBLE);
            proupdwn.setVisibility(View.VISIBLE);

            prname.setVisibility(View.INVISIBLE);
            pronm.setVisibility(View.INVISIBLE);

            prprice.setVisibility(View.INVISIBLE);
            propri.setVisibility(View.INVISIBLE);

            prcontent.setVisibility(View.INVISIBLE);
            procon.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
