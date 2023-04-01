package com.example.productapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProductActivity extends AppCompatActivity {

    EditText _txtName,_txtQunatity;
    Button _btnSave;
    ProductDatabaseHelper phd=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        _txtQunatity = findViewById(R.id.txtQunatity);
        _txtName = findViewById(R.id.txtName);
        _btnSave = findViewById(R.id.btnSave);
        phd= new ProductDatabaseHelper(this);
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phd.AddProduct(_txtName.getText().toString(),Integer.valueOf(_txtQunatity.getText().toString()));
            Intent _main = new Intent(ProductActivity.this,MainActivity.class);
            startActivity(_main);
            }
        });

    }
}