package com.example.azaat.sushiapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;


public class Restaurant extends Fragment {
    RecyclerView recyclerView;
    Toolbar toolbar ;
    DBhelper dBhelper;
    SQLiteDatabase db ;
    TextView menu_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_list1, container, false);


        dBhelper = new DBhelper(getActivity());
        db = dBhelper.getWritableDatabase();



        Cursor cursor = db.query("restaurant",null,null,null,null,null,null);
        Toast.makeText(getActivity(), ""+cursor.toString(), Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar) view.findViewById(R.id.menu_toolbar1);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        TextView to_title = (TextView) view.findViewById(R.id.toolbar_title);
        to_title.setText("Рестораны");


        recyclerView = (RecyclerView) view.findViewById(R.id.menu_list1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<ListClass1> list = new ArrayList<>();
        menu_text = (TextView) view.findViewById(R.id.toolbar_text);

        if(cursor.moveToFirst()){
            do {
                list.add(new ListClass1(cursor.getString(cursor.getColumnIndex("title")),
                        R.drawable.img22,
                        cursor.getInt(cursor.getColumnIndex("id"))));
            }while (cursor.moveToNext());
        }

//        list.add(new ListClass1("ROW",R.drawable.img2,1));
//        list.add(new ListClass1("Суп",R.drawable.img2,1));
//        list.add(new ListClass1("Суши",R.drawable.img2,1));
//        list.add(new ListClass1("Рамен",R.drawable.img2,1));
//        list.add(new ListClass1("Соусы",R.drawable.img2 ,1));


        AdapterForRes adapter = new AdapterForRes(getActivity(),list);

        recyclerView.setAdapter(adapter);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.basket_toolbar);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Basket.class);
                startActivity(intent );
            }
        });
        return view;
    }
    public void onResume() {
        super.onResume();
        int c = 0;
        Cursor cursor1 = db.query("PRODUCTS",null,"count>0",null,null,null,null);
        if(cursor1.moveToFirst()){
            do {
                c += (cursor1.getInt(cursor1.getColumnIndex("price"))*cursor1.getInt(cursor1.getColumnIndex("count")));
            }while (cursor1.moveToNext());
        }
        menu_text.setText(c+"");
    }
}
