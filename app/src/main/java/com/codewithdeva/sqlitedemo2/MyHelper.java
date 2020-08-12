package com.codewithdeva.sqlitedemo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {

    private static final String dbname="mydb";

    public MyHelper(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {     //on create methode call only one time.
        //To create the Table for SQLite.
        String table="create table product(_id INTEGER PRIMARY KEY AUTOINCREMENT,name text,description text,price real)";
        sqLiteDatabase.execSQL(table);

        insertdata("jam","Fruit jam",300.00,sqLiteDatabase);
        insertdata("Bread","Brown Bread",35.00,sqLiteDatabase);
        insertdata("Corn Flex","Flakes",420.75,sqLiteDatabase);

    }

    private void insertdata(String name,String description,double price,SQLiteDatabase Database){
       // SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("NAME",name);
        cv.put("DESCRIPTION",description);
        cv.put("PRICE",price);

        Database.insert("product",null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS product");
        onCreate(sqLiteDatabase);

    }
}
