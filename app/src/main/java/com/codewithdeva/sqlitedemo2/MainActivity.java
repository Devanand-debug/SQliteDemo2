package com.codewithdeva.sqlitedemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyHelper helper=new MyHelper(this);
        SQLiteDatabase database=helper.getWritableDatabase();

        ContentValues cv=new ContentValues();   //To Update price.
        cv.put("PRICE",240.00);

        database.update("product",cv,"_id=?",new String[]{"1"});

      // database.delete("product","_id=?",new String[]{"2"});     //delete the second row

       // database.update("product",cv,"NAME=? AND DESCRIPTION=?",new String[]{"Cake","Cream Cake"}); //to update multiple parameter basis

        Cursor cursor=database.rawQuery("select name,description,price from product",new String[]{});
        //Cursor cursor=database.rawQuery("select name,description,price from product where NAME =?",new String[]{"Corn Flex"}); //display only one row

        if(cursor!=null){
            cursor.moveToFirst();
        }

        StringBuilder builder= new StringBuilder();

        do {
            String name=cursor.getString(0);
            String description=cursor.getString(1);
            double price=cursor.getDouble(2);

            builder.append(" NAME: "+ name +", DESCRIPTION: "+ description +", PRICE: "+ price +"\n"  );

        }while (cursor.moveToNext());

        textView=findViewById(R.id.txtData);
         textView.setText(builder.toString());
    }
}