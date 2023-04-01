package com.example.productapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateProductActivity extends AppCompatActivity {

    EditText _txtUpName =null;
    Button _btnUpdate =null;
    ProductDatabaseHelper pd =null;
    int rowid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        _txtUpName = findViewById(R.id.txtUpName);
        _btnUpdate = findViewById(R.id.btnUpdate);
            Intent _data =     getIntent();
             rowid = _data.getIntExtra("id",0);
             rowid++;
             String proname = _data.getStringExtra("name");
            _txtUpName.setText(proname);
        pd = new ProductDatabaseHelper(this);
        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               pd.Update(String.valueOf(rowid),_txtUpName.getText().toString(),12);

                Intent _pro=new Intent(UpdateProductActivity.this,MainActivity.class);
                startActivity(_pro);
            }
        });
    }
}