package com.example.azaat.sushiapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class Chage_Data extends AppCompatActivity {

    DBhelper dBhelper;
    SQLiteDatabase db ;

    TextView number;
    EditText name,surname,email;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chage__data);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();

        number = (TextView) findViewById(R.id.profile_number);
        name = (EditText) findViewById(R.id.profile_name);
        surname = (EditText) findViewById(R.id.profile_surname);
        email = (EditText) findViewById(R.id.profile_email);
        save = (Button) findViewById(R.id.profile_save);

        Cursor cursor = db.query("user",null,null,null,null,null,null);
        cursor.moveToFirst();

        number.setText(cursor.getString(cursor.getColumnIndex("number")));
        name.setText(cursor.getString(cursor.getColumnIndex("name")));
        surname.setText(cursor.getString(cursor.getColumnIndex("surname")));
//        email.setText(cursor.getString(cursor.getColumnIndex("email")));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues contentValues = new ContentValues();
                contentValues.put("name",name.getText().toString());
                contentValues.put("surname",surname.getText().toString());
//                contentValues.put("email",email.getText().toString());
                db.update("user",contentValues,null,null);
                finish();
            }
        });
    }
}
