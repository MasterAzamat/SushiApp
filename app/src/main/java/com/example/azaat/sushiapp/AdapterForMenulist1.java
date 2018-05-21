package com.example.azaat.sushiapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Azaat on 10.04.2018.
 */

public class AdapterForMenulist1 extends RecyclerView.Adapter<AdapterForMenulist1.AdapterForMenulist1ViewHolder>{


    List<ListClass1> list;
    FragmentActivity context;
    View view;

    public AdapterForMenulist1(FragmentActivity context, List<ListClass1> list) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public AdapterForMenulist1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_for_menulist1,null);
        AdapterForMenulist1ViewHolder viewHolder = new AdapterForMenulist1ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForMenulist1ViewHolder holder, final int position) {
        ListClass1 class1 = list.get(position);

        holder.img.setImageResource(class1.getImg());
        holder.title.setText(class1.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("mess",list.get(position).getId());
                MenuList2 menuList2 = new MenuList2();
                menuList2.setArguments(bundle);
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_con, menuList2);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AdapterForMenulist1ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView img;

        public AdapterForMenulist1ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title_item_menulist1);
            img = (ImageView) itemView.findViewById(R.id.img_item_menulist1);

        }
    }


}
