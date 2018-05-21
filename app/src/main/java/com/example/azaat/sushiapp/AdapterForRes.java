package com.example.azaat.sushiapp;

import android.os.Bundle;
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
 * Created by Azaat on 12.04.2018.
 */

public class AdapterForRes extends RecyclerView.Adapter<AdapterForRes.AdapterForResViewHolder>{

    List<ListClass1> list;
    FragmentActivity context;

    public AdapterForRes(FragmentActivity context, List<ListClass1> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdapterForRes.AdapterForResViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_for_res,null);
        AdapterForResViewHolder holder = new AdapterForResViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterForResViewHolder holder, int position) {
        ListClass1 class1 = list.get(position);

        holder.img.setImageResource(class1.getImg());
        holder.title.setText(class1.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("mess",list.get(position).getId());
//                MenuList2 menuList2 = new MenuList2();
//                menuList2.setArguments(bundle);
//                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_con, menuList2);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AdapterForResViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView img;
        public AdapterForResViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_item_reslist);
            img = (ImageView) itemView.findViewById(R.id.img_item_reslist);
        }
    }
}
