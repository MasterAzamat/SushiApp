package com.example.azaat.sushiapp;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;


public class Profile extends Fragment {

    DBhelper dBhelper;
    SQLiteDatabase db ;

    int Allprice;
    TextView basketPraice;

    boolean userCheck = false;

    CardView profile,sign_out,my_address,my_orders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        basketPraice = (TextView) view.findViewById(R.id.toolbar_text);

        profile = (CardView) view.findViewById(R.id.profile_change);
        sign_out = (CardView) view.findViewById(R.id.sign_out);
        my_address = (CardView) view.findViewById(R.id.my_address);
        my_orders = (CardView) view.findViewById(R.id.my_orders);

        dBhelper = new DBhelper(getActivity());
        db = dBhelper.getWritableDatabase();


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userCheck){
                    Intent intent = new Intent(getActivity(),Chage_Data.class);
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    View view1 = getLayoutInflater().inflate(R.layout.sign_in_layout,null);
                    Button cancel = (Button) view1.findViewById(R.id.dialog_cancel);
                    Button next = (Button) view1.findViewById(R.id.dialog_next);
                    final EditText edit = (EditText) view1.findViewById(R.id.dialog_edit);

                    builder.setView(view1);
                    final AlertDialog dialog =builder.create();
                    dialog.show();
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PhoneNumberUtil pnu = PhoneNumberUtil.getInstance();
                            Phonenumber.PhoneNumber pn = null;
                            try {
                                pn = pnu.parse(edit.getText().toString(), "KZ");
                            } catch (NumberParseException e) {
                                e.printStackTrace();
                            }
                            String pnE164 = pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.E164);
                            Toast.makeText(getActivity(), ""+pnE164, Toast.LENGTH_SHORT).show();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name","");
                            contentValues.put("surname","");
                            contentValues.put("number",edit.getText().toString());
                            contentValues.put("address","");
                            db.insert("user",null,contentValues);
                            dialog.cancel();
                            Intent intent = new Intent(getActivity(),Chage_Data.class);
                            startActivity(intent);

                        }
                    });
//                    Rect displayRectangle = new Rect();
//                    Window window = getActivity().getWindow();
//                    window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
//                    dialog.getWindow().setLayout((int)(displayRectangle.width() *
//                            0.95f), (int)(displayRectangle.height() * 0.56f));
                }

            }
        });

        my_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userCheck){
                    Intent intent = new Intent(getActivity(),MapActivity.class);
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    View view1 = getLayoutInflater().inflate(R.layout.sign_in_layout,null);
                    Button cancel = (Button) view1.findViewById(R.id.dialog_cancel);
                    Button next = (Button) view1.findViewById(R.id.dialog_next);
                    final EditText edit = (EditText) view1.findViewById(R.id.dialog_edit);

                    builder.setView(view1);
                    final AlertDialog dialog =builder.create();
                    dialog.show();
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PhoneNumberUtil pnu = PhoneNumberUtil.getInstance();
                            Phonenumber.PhoneNumber pn = null;
                            try {
                                pn = pnu.parse(edit.getText().toString(), "KZ");
                            } catch (NumberParseException e) {
                                e.printStackTrace();
                            }
                            String pnE164 = pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.E164);
                            Toast.makeText(getActivity(), ""+pnE164, Toast.LENGTH_SHORT).show();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name","");
                            contentValues.put("surname","");
                            contentValues.put("number",edit.getText().toString());
                            contentValues.put("address","");
                            db.insert("user",null,contentValues);
                            dialog.cancel();
                            Intent intent = new Intent(getActivity(),Chage_Data.class);
                            startActivity(intent);

                        }
                    });
//                    Rect displayRectangle = new Rect();
//                    Window window = getActivity().getWindow();
//                    window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
//                    dialog.getWindow().setLayout((int)(displayRectangle.width() *
//                            0.95f), (int)(displayRectangle.height() * 0.56f));
                }

            }
        });

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Выйти из аккаунта?").setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sign_out.setVisibility(View.GONE);
                        userCheck = false;
                        db.delete("user",null,null);
                    }
                }).setNegativeButton("ОТМЕНА", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dialog =builder.create();
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor cursor = db.query("user",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            sign_out.setVisibility(View.VISIBLE);
            userCheck = true;
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
