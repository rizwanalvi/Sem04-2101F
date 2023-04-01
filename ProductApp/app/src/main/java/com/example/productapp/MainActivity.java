package com.example.productapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        ListView _lstProduct =null;
        ProductDatabaseHelper pdb=null;
        ArrayList<String> _al=null;
        FloatingActionButton _btnShow=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _lstProduct = findViewById(R.id.lstProduct);
        _btnShow = findViewById(R.id.btnShow);

        _btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _pro = new Intent(MainActivity.this,ProductActivity.class);
                startActivity(_pro);
            }
        });
        _al = new ArrayList<>();
        pdb = new ProductDatabaseHelper(this);
        Cursor _cur= pdb.getAllData();
        while (_cur.moveToNext()){
            _al.add(_cur.getString(1));
        }
        ArrayAdapter<String> _dAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,_al);
        _lstProduct.setAdapter(_dAdapter);
        _lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent _upPro = new Intent(MainActivity.this,UpdateProductActivity.class);
                _upPro.putExtra("id",i);
                _upPro.putExtra("name",String.valueOf(adapterView.getItemAtPosition(i)));
                startActivity(_upPro);
               // Snackbar.make(view,String.valueOf(adapterView.getItemAtPosition(i)),Snackbar.LENGTH_LONG).show();
            }
        });
    }
}