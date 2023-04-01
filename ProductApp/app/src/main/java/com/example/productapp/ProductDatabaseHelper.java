package com.example.productapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
private static  final String DATABASE_NAME ="productdb.db";
    private static  final String TABLE_NAME ="products";
    private static  final String COLUMN_ID ="id";
    private static  final String COLUMN_NAME ="name";
    private static  final String COLUMN_QUANTITY ="quantity";
    public ProductDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String _query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME+" TEXT, "+COLUMN_QUANTITY+" INTEGER )";
        sqLiteDatabase.execSQL(_query);
    }
    public void AddProduct(String name,int qun){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_QUANTITY,qun);
        db.insert(TABLE_NAME,null,cv);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String _query = "SELECT * FROM "+ TABLE_NAME;
         Cursor _cur = db.rawQuery(_query,null);
        return _cur;
    }
    public void Update(String rowid,String name,int qun){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_QUANTITY,qun);
        db.update(TABLE_NAME,cv,"id=?",new String[]{rowid});

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
