package com.example.azaat.sushiapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class MenuList3 extends Fragment {

    DBhelper dBhelper;
    SQLiteDatabase db ;

    ImageView img;
    TextView title,des,price,basketPraice,count;
    LinearLayout basket,plus,minus,layoutAdd;

    ListClass2 class2 ;

    int Allprice;

    @SuppressLint("ValidFragment")
    public MenuList3(ListClass2 class2){
        this.class2 = class2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_list3, container, false);

        dBhelper = new DBhelper(getActivity());
        db = dBhelper.getWritableDatabase();

        layoutAdd = (LinearLayout) view.findViewById(R.id.plus_item_menulist3);
        basketPraice = (TextView) view.findViewById(R.id.toolbar_text);
        img = (ImageView) view.findViewById(R.id.img_menulis3);
        title = (TextView) view.findViewById(R.id.title_menulis3);
        des = (TextView) view.findViewById(R.id.des_menulis3);
        price = (TextView) view.findViewById(R.id.price_menulis3);
        count = (TextView) view.findViewById(R.id.count_menulist3);
        basket = (LinearLayout) view.findViewById(R.id.add_to_basket_menulis3);
        plus = (LinearLayout) view.findViewById(R.id.plus_menulist3);
        minus = (LinearLayout) view.findViewById(R.id.minus_menulist3);

        img.setImageResource(class2.getImg());
        title.setText(class2.getTitle());
        price.setText(class2.getPrice()+"");
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basket.setVisibility(View.GONE);
                layoutAdd.setVisibility(View.VISIBLE);
                count.setText("1");
                Allprice += Integer.parseInt(price.getText().toString());
                basketPraice.setText(Allprice + "");
                ContentValues cv = new ContentValues();
                cv.put("count",count.getText().toString());
                db.update("products",cv,"id=?",new String[]{""+class2.getId()});
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Allprice += Integer.parseInt(price.getText().toString());
                basketPraice.setText(Allprice + "");
                count.setText(Integer.parseInt(count.getText().toString())+ 1 + "");
                ContentValues cv = new ContentValues();
                cv.put("count",count.getText().toString());
                db.update("products",cv,"id=?",new String[]{""+class2.getId()});
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Allprice -= Integer.parseInt(price.getText().toString());
                basketPraice.setText(Allprice + "");
                count.setText(Integer.parseInt(count.getText().toString())- 1 + "");
                ContentValues cv = new ContentValues();
                cv.put("count",count.getText().toString());
                db.update("products",cv,"id=?",new String[]{""+class2.getId()});
                if(Integer.parseInt(count.getText().toString()) == 0){
                    basket.setVisibility(View.VISIBLE);
                    layoutAdd.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        Cursor cursor = db.query("PRODUCTS",null,"id="+class2.getId(),null,null,null,null);
        cursor.moveToFirst();
        count.setText(cursor.getInt(cursor.getColumnIndex("count"))+"");
        if(Integer.parseInt(count.getText().toString())>0){
            basket.setVisibility(View.GONE);
            layoutAdd.setVisibility(View.VISIBLE);
        }

        int c = 0;
        Cursor cursor1 = db.query("PRODUCTS",null,"count>0",null,null,null,null);
        if(cursor1.moveToFirst()){
            do {
                c += (cursor1.getInt(cursor1.getColumnIndex("price"))*cursor1.getInt(cursor1.getColumnIndex("count")));
            }while (cursor1.moveToNext());
        }
        basketPraice.setText(c+"");
        Allprice = c;
    }
}
