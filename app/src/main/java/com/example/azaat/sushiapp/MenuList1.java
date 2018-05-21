package com.example.azaat.sushiapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MenuList1 extends Fragment {
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



        Cursor cursor = db.query("chooses",null,null,null,null,null,null);
        toolbar = (Toolbar) view.findViewById(R.id.menu_toolbar1);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        TextView to_title = (TextView) view.findViewById(R.id.toolbar_title);
        to_title.setText("Категории");


        recyclerView = (RecyclerView) view.findViewById(R.id.menu_list1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<ListClass1> list = new ArrayList<>();
        cursor.moveToFirst();
        menu_text = (TextView) view.findViewById(R.id.toolbar_text);


        do {
            list.add(new ListClass1(cursor.getString(cursor.getColumnIndex("title")),
                    R.drawable.img22,
                    cursor.getInt(cursor.getColumnIndex("id"))));
        }while (cursor.moveToNext());
//        list.add(new ListClass1("ROW",R.drawable.img2,1));
//        list.add(new ListClass1("Суп",R.drawable.img2,1));
//        list.add(new ListClass1("Суши",R.drawable.img2,1));
//        list.add(new ListClass1("Рамен",R.drawable.img2,1));
//        list.add(new ListClass1("Соусы",R.drawable.img2 ,1));


        AdapterForMenulist1 adapter = new AdapterForMenulist1(getActivity(),list);

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
    @Override
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
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        System.out.println("ondestroy");
//    }
//

//
//    @Override
//    public void onStart() {
//        super.onStart();
//        System.out.println("onstart");
//
//    }
//
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        System.out.println("oncreate");
//
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        System.out.println("onDetach");
//
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        System.out.println("ondesryyview");
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        System.out.println("onstop");
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        System.out.println("onpause");
//
//    } AIzaSyDk0WGkdJU5LufzXhktDWuQgK4ft6k36oE

}
