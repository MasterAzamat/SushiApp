package com.example.azaat.sushiapp;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentTransaction transaction;
    int bottomItem = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            transaction = getSupportFragmentManager().beginTransaction();
            int count = getSupportFragmentManager().getBackStackEntryCount();
            switch (item.getItemId()) {

                case R.id.nav_menu :
                    if (bottomItem !=0){
                        bottomItem = 0;
                        for(int i = 0; i < count; ++i) {
                            getSupportFragmentManager().popBackStack();
                        }
                        transaction.replace(R.id.frame_con, new MenuList1());
                        transaction.commit();
                    }
                    return true;

                case R.id.nav_restorans:
                    if (bottomItem !=1){
                        bottomItem = 1;
                        for(int i = 0; i < count; ++i) {
                            getSupportFragmentManager().popBackStack();
                        }
                        transaction.replace(R.id.frame_con, new Restaurant());
                        transaction.commit();
                    }
                    return true;

                case R.id.nav_profile:
                    if (bottomItem !=2){
                        bottomItem = 2;
                        for(int i = 0; i < count; ++i) {
                            getSupportFragmentManager().popBackStack();
                        }
                        transaction.replace(R.id.frame_con,new Profile());
                        transaction.commit();
                    }
                    return true;

                case R.id.nav_more:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuList1 menuList1 = new MenuList1();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_con, menuList1);
        transaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

