package com.example.azaat.sushiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Azaat on 10.04.2018.
 */

public class AdapterForMenulist2 extends RecyclerView.Adapter<AdapterForMenulist2.AdapterForMenulist2ViewHolder>{
    FragmentActivity context;
    List<ListClass2> list;
    Animation animation;
    Runnable []runnablesList;

    DBhelper dBhelper;
    SQLiteDatabase db ;

    int AllPrice ;
    TextView tv;

    public AdapterForMenulist2(FragmentActivity context, List<ListClass2> list,int AllPrice,TextView tv) {
        this.context = context;
        this.list = list;
        this.AllPrice = AllPrice;
        this.tv = tv;
        runnablesList = new Runnable[list.size()];
        dBhelper = new DBhelper(context);
        db = dBhelper.getWritableDatabase();
    }


    @Override
    public AdapterForMenulist2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_for_menulist2,null);
        AdapterForMenulist2ViewHolder viewHolder = new AdapterForMenulist2ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterForMenulist2ViewHolder holder, final int position) {
        holder.img.setImageResource(list.get(position).getImg());
        holder.title.setText(list.get(position).getTitle());
//        if(list.get(position).getWeightSum() != null){
//            holder.weight.setText(list.get(position).getWeightSum());
//        }else
//            holder.weight.setText(list.get(position).getWeight());
        if(list.get(position).getCount()>0) {
            holder.basket.setVisibility(View.GONE);
            holder.count.setVisibility(View.VISIBLE);
        }
//        holder.countBas.setText(list.get(position).getCount()+"");
        holder.count.setText(list.get(position).getCount()+"");
        holder.price.setText(list.get(position).getPrice()+"");

        animation = AnimationUtils.loadAnimation(context,R.anim.animation_fade_in);
        final Handler handler = new Handler();
        holder.basket_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.plusBtn.setEnabled(true);
                holder.minusBtn.setEnabled(true);
                holder.plusBtn.setVisibility(View.VISIBLE);
                holder.minusBtn.setVisibility(View.VISIBLE);
                holder.basket.setVisibility(View.GONE);
                holder.count.setVisibility(View.VISIBLE);
                //                holder.basketClick.setVisibility(View.GONE);
//                holder.plus_mainus.startAnimation(animation);

                Runnable runnable = new Runnable() {
                    Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_fade_out);
                    @Override
                    public void run() {
                        Toast.makeText(context, "1 SECOND", Toast.LENGTH_SHORT).show();
                        holder.plusBtn.setEnabled(false);
                        holder.minusBtn.setEnabled(false);
                        holder.plusBtn.setVisibility(View.GONE);
                        holder.minusBtn.setVisibility(View.GONE);
                        if(Integer.parseInt(holder.count.getText().toString()) == 0){
                            holder.basket.setVisibility(View.VISIBLE);
                            holder.count.setVisibility(View.GONE);
                        }
//                        holder.plus_mainus.setVisibility(View.GONE);
//                        holder.basketClick.setVisibility(View.VISIBLE);
//                        holder.plus_mainus.startAnimation(animation);
                    }
                };
                handler.postDelayed(runnable,1000);
                runnablesList[position] = runnable;

            }
        });
        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.plusBtn.setEnabled(true);
                holder.minusBtn.setEnabled(true);
                AllPrice +=list.get(position).getPrice();
                tv.setText(AllPrice + "");
                holder.count.setText(Integer.parseInt(holder.count.getText().toString())+ 1 + "");
                ContentValues cv = new ContentValues();
                cv.put("count",holder.count.getText().toString());
                db.update("products",cv,"id=?",new String[]{""+list.get(position).getId()});

                handler.removeCallbacks(runnablesList[position]);
                Runnable runnable = new Runnable() {
                    Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_fade_out);
                    @Override
                    public void run() {
                        Toast.makeText(context, "1 SECOND", Toast.LENGTH_SHORT).show();
                        holder.plusBtn.setEnabled(false);
                        holder.minusBtn.setEnabled(false);
                        holder.plusBtn.setVisibility(View.GONE);
                        holder.minusBtn.setVisibility(View.GONE);
                        if(Integer.parseInt(holder.count.getText().toString()) == 0){
                            holder.basket.setVisibility(View.VISIBLE);
                            holder.count.setVisibility(View.GONE);
                        }
                        list.get(position).setCount(Integer.parseInt( holder.count.getText().toString()));

//                        holder.basketClick.setVisibility(View.VISIBLE);
//                        holder.plus_mainus.startAnimation(animation);
//                        holder.plus_mainus.setVisibility(View.GONE);
                    }
                };
                handler.postDelayed(runnable,1000);
                runnablesList[position] = runnable;
            }
        });
        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.plusBtn.setEnabled(true);
                holder.minusBtn.setEnabled(true);
                int current = Integer.parseInt(holder.count.getText().toString());
                if(current > 0){
                    holder.count.setText(current - 1 + "");
                    AllPrice -=list.get(position).getPrice();
                    tv.setText(AllPrice + "");
                }
                ContentValues cv = new ContentValues();
                cv.put("count",holder.count.getText().toString());
                db.update("products",cv,"id=?",new String[]{""+list.get(position).getId()});

                handler.removeCallbacks(runnablesList[position]);
                Runnable runnable = new Runnable() {
                    Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_fade_out);
                    @Override
                    public void run() {
                        Toast.makeText(context, "1 SECOND", Toast.LENGTH_SHORT).show();
                        holder.plusBtn.setEnabled(false);
                        holder.minusBtn.setEnabled(false);
                        holder.plusBtn.setVisibility(View.GONE);
                        holder.minusBtn.setVisibility(View.GONE);
                        if(Integer.parseInt(holder.count.getText().toString()) == 0){
                            holder.basket.setVisibility(View.VISIBLE);
                            holder.count.setVisibility(View.GONE);
                        }
                        list.get(position).setCount(Integer.parseInt( holder.count.getText().toString()));

//                        holder.basketClick.setVisibility(View.VISIBLE);
//                        holder.plus_mainus.startAnimation(animation);
//                        holder.plus_mainus.setVisibility(View.GONE);
                    }
                };

                handler.postDelayed(runnable,1000);
                runnablesList[position] = runnable;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuList3 menuList2 = new MenuList3(list.get(position));
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

    class AdapterForMenulist2ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout plusBtn,minusBtn,basketClick,plus_mainus,basket_layout;
        TextView title,weight,price,count,countBas;
        ImageView img,basket;

        public AdapterForMenulist2ViewHolder(View itemView) {
            super(itemView);

            plusBtn = (LinearLayout) itemView.findViewById(R.id.plus_item_menulist2);
            minusBtn = (LinearLayout) itemView.findViewById(R.id.minus_item_menulist2);
            title = (TextView) itemView.findViewById(R.id.title_item_menulist2);
//            weight = (TextView) itemView.findViewById(R.id.weight_item_menulist2);
            price = (TextView) itemView.findViewById(R.id.price_item_menulist2);
            count= (TextView) itemView.findViewById(R.id.count_item_menulist2);
//            countBas= (TextView) itemView.findViewById(R.id.count_basket_item_menulist2);
            img = (ImageView) itemView.findViewById(R.id.img_item_menulist2);
            basket = (ImageView) itemView.findViewById(R.id.basket_item_menulist2);
//            basketClick = (LinearLayout) itemView.findViewById(R.id.basket_click_item_menulist2);
            plus_mainus = (LinearLayout) itemView.findViewById(R.id.plus_minus_item_menulist2);
            basket_layout = (LinearLayout) itemView.findViewById(R.id.basket_layout_item_menulist2);
        }
    }
}
