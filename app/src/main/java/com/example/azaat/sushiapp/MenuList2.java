package com.example.azaat.sushiapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MenuList2 extends Fragment {

    DBhelper dBhelper;
    SQLiteDatabase db ;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_list2, container, false);
        TextView to_title = (TextView) view.findViewById(R.id.toolbar_title);
        to_title.setText("Выбор");

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
        Bundle bundle = getArguments();
        int a = bundle.getInt("mess");

        dBhelper = new DBhelper(getActivity());
        db = dBhelper.getWritableDatabase();
//        Toast.makeText(getActivity(), ""+a, Toast.LENGTH_SHORT).show();

        Cursor cursor = db.query("PRODUCTS",null,"choose_id="+a,null,null,null,null);
        recyclerView = (RecyclerView) getView().findViewById(R.id.menu_list2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<ListClass2> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {

                list.add(new ListClass2(cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getInt(cursor.getColumnIndex("price")),
                        R.drawable.img22,
                        cursor.getInt(cursor.getColumnIndex("count")),
                        cursor.getInt(cursor.getColumnIndex("id"))));
            }while (cursor.moveToNext());
        }
        int c = 0;
        Cursor cursor1 = db.query("PRODUCTS",null,"count>0",null,null,null,null);
        if(cursor1.moveToFirst()){
            do {
                c += (cursor1.getInt(cursor1.getColumnIndex("price"))*cursor1.getInt(cursor1.getColumnIndex("count")));
            }while (cursor1.moveToNext());
        }
        TextView menu_text = (TextView) getView().findViewById(R.id.toolbar_text);
        menu_text.setText(c+"");
//        list.add(new ListClass2("tttt",1000,R.drawable.img22,0,1));
//        list.add(new ListClass2("tttt",1000,R.drawable.img22,0,1));
//        list.add(new ListClass2("tttt",1000,R.drawable.img22,4,1));
//        list.add(new ListClass2("tttt",1000,R.drawable.img22,0,1));
//        list.add(new ListClass2("tttt",1000,R.drawable.img22,0,1));
        AdapterForMenulist2 adapter = new AdapterForMenulist2(getActivity(),list,c,menu_text);

        recyclerView.setAdapter(adapter);
    }
}
