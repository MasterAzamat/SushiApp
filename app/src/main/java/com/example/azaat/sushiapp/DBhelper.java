package com.example.azaat.sushiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Azaat on 10.04.2018.
 */

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "mydb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE CHOOSES (" +
                "id integer primary key autoincrement," +
                "title text," +
                "image integer);";
        sqLiteDatabase.execSQL(query);
        query = "CREATE TABLE PRODUCTS (" +
                "id integer primary key autoincrement," +
                "title text," +
                "image integer," +
                "price integer," +
                "count integer," +
                "choose_id integer);";
        sqLiteDatabase.execSQL(query);
        ContentValues contentValues = new ContentValues();
        contentValues.put("title","Pizza");
        contentValues.put("image",R.drawable.img1);
        sqLiteDatabase.insert("chooses",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","WOK");
        contentValues.put("image",R.drawable.img6);
        sqLiteDatabase.insert("chooses",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","Sushi");
        contentValues.put("image",R.drawable.img11);
        sqLiteDatabase.insert("chooses",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","Roll");
        contentValues.put("image",R.drawable.img16);
        sqLiteDatabase.insert("chooses",null,contentValues);

        Cursor cursor = sqLiteDatabase.query("chooses",new String[]{"id"},null,null,null,null,null);
        cursor.moveToFirst();
        int a = cursor.getInt(cursor.getColumnIndex("id"));
        //1
        contentValues = new ContentValues();
        contentValues.put("title","Pizza1");
        contentValues.put("image",R.drawable.img1);
        contentValues.put("price",1000);
        contentValues.put("count", 0);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("products",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","Pizza2");
        contentValues.put("image",R.drawable.img1);
        contentValues.put("price",1100);
        contentValues.put("count", 0);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","Pizza3");
        contentValues.put("image",R.drawable.img1);
        contentValues.put("price",1200);
        contentValues.put("count", 0);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","Pizza4");
        contentValues.put("image",R.drawable.img1);
        contentValues.put("price",1200);
        contentValues.put("count", 1);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","Pizza5");
        contentValues.put("image",R.drawable.img1);
        contentValues.put("price",1700);
        contentValues.put("count", 0);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);

        cursor.moveToNext();
        a = cursor.getInt(cursor .getColumnIndex("id"));
        //2
        contentValues = new ContentValues();
        contentValues.put("title","WOK1");
        contentValues.put("image",R.drawable.img6);
        contentValues.put("price",1000);
        contentValues.put("count", 0);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","WOK2");
        contentValues.put("image",R.drawable.img22);
        contentValues.put("price",1100);
        contentValues.put("count", 0);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","WOK3");
        contentValues.put("image",R.drawable.img22);
        contentValues.put("price",1200);
        contentValues.put("count", 0);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","WOK4");
        contentValues.put("image",R.drawable.img22);
        contentValues.put("price",1200);
        contentValues.put("count", 1);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","WOK5");
        contentValues.put("image",R.drawable.img22);
        contentValues.put("price",1700);
        contentValues.put("count", 0);
        contentValues.put("choose_id",a);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);

        query = "CREATE TABLE restaurant (" +
                "id integer primary key autoincrement," +
                "title text," +
                "image integer," +
                "schedule text," +
                "address text," +
                "telnumber text" +");";
        sqLiteDatabase.execSQL(query);

        contentValues = new ContentValues();
        contentValues.put("title","Aport");
        contentValues.put("image",R.drawable.img1);
        contentValues.put("schedule","с 10:00 до 24:00");
        contentValues.put("address","пр.Абылай Хана,94");
        contentValues.put("telnumber","7(747)866 68 65");
        sqLiteDatabase.insert("restaurant",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","Mega");
        contentValues.put("image",R.drawable.img6);
        contentValues.put("schedule","с 09:00 до 24:00");
        contentValues.put("address","пр.Абылай Хана,142");
        contentValues.put("telnumber","7(747)464 68 65");
        sqLiteDatabase.insert("restaurant",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("title","Esentay");
        contentValues.put("image",R.drawable.img11);
        contentValues.put("schedule","с 10:00 до 24:00");
        contentValues.put("address","пр.Абая,94");
        contentValues.put("telnumber","7(747)698 65 55");
        sqLiteDatabase.insert("restaurant",null,contentValues);

        query = "CREATE TABLE user (" +
                "id integer primary key autoincrement," +
                "name text," +
                "surname text," +
                "number text," +
                "address text" +");";
        sqLiteDatabase.execSQL(query);
//        //3
//        contentValues = new ContentValues();
//        contentValues.put("title","Sushi1");
//        contentValues.put("image",R.drawable.img11);
//        contentValues.put("price",1000);
//        contentValues.put("count", 0);
//        contentValues.put("choose_id",3);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//        contentValues = new ContentValues();
//        contentValues.put("title","Sushi2");
//        contentValues.put("image",R.drawable.img12);
//        contentValues.put("price",1100);
//        contentValues.put("count", 0);
//        contentValues.put("choose_id",3);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//        contentValues = new ContentValues();
//        contentValues.put("title","Sushi3");
//        contentValues.put("image",R.drawable.img13);
//        contentValues.put("price",1200);
//        contentValues.put("count", 0);
//        contentValues.put("choose_id",3);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//        contentValues = new ContentValues();
//        contentValues.put("title","Sushi4");
//        contentValues.put("image",R.drawable.img14);
//        contentValues.put("price",1200);
//        contentValues.put("count", 1);
//        contentValues.put("choose_id",3);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//        contentValues = new ContentValues();
//        contentValues.put("title","Sushi5");
//        contentValues.put("image",R.drawable.img15);
//        contentValues.put("price",1700);
//        contentValues.put("count", 0);
//        contentValues.put("choose_id",3);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//
//        //4
//        contentValues = new ContentValues();
//        contentValues.put("title","Roll1");
//        contentValues.put("image",R.drawable.img16);
//        contentValues.put("price",1000);
//        contentValues.put("count", 0);
//        contentValues.put("choose_id",4);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//        contentValues = new ContentValues();
//        contentValues.put("title","Roll2");
//        contentValues.put("image",R.drawable.img17);
//        contentValues.put("price",1100);
//        contentValues.put("count", 0);
//        contentValues.put("choose_id",4);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//        contentValues = new ContentValues();
//        contentValues.put("title","Roll3");
//        contentValues.put("image",R.drawable.img18);
//        contentValues.put("price",1200);
//        contentValues.put("count", 0);
//        contentValues.put("choose_id",4);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//        contentValues = new ContentValues();
//        contentValues.put("title","Roll4");
//        contentValues.put("image",R.drawable.img19);
//        contentValues.put("price",1200);
//        contentValues.put("count", 1);
//        contentValues.put("choose_id",4);
//        sqLiteDatabase.insert("chooses",null,contentValues);
//        contentValues = new ContentValues();
//        contentValues.put("title","Roll5");
//        contentValues.put("image",R.drawable.img20);
//        contentValues.put("price",1700);
//        contentValues.put("count", 0);
//        contentValues.put("choose_id",4);
//        sqLiteDatabase.insert("chooses",null,contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
