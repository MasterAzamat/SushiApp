package com.example.azaat.sushiapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Basket extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar ;
    DBhelper dBhelper;
    SQLiteDatabase db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.basket_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<BasketClass> list = new ArrayList<>();
        int c = 0;
        Cursor cursor = db.query("PRODUCTS",null,"count>0",null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                c += (cursor.getInt(cursor.getColumnIndex("price"))*cursor.getInt(cursor.getColumnIndex("count")));
                list.add(new BasketClass(cursor.getInt(cursor.getColumnIndex("id")),
                        R.drawable.img22,
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getInt(cursor.getColumnIndex("count")),
                        cursor.getInt(cursor.getColumnIndex("price"))));
            }while (cursor.moveToNext());
        }
        TextView menu_text = (TextView) findViewById(R.id.toolbar_text);
        menu_text.setText(c+"");


        AdapterForBasket adapter = new AdapterForBasket(this,list,c,menu_text);

        recyclerView.setAdapter(adapter);
    }
}
