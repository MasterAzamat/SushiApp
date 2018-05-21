package com.example.azaat.sushiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Azaat on 11.04.2018.
 */

public class AdapterForBasket  extends RecyclerView.Adapter<AdapterForBasket.AdapterForBasketViewHolder>{
    Context context;
    List<BasketClass> list;
    Runnable []runnablesList;
    int AllPrice ;
    TextView tv;

    DBhelper dBhelper;
    SQLiteDatabase db ;




    public AdapterForBasket(Context context, List<BasketClass> list,int AllPrice,TextView tv) {
        this.context = context;
        this.list = list;
        this.AllPrice = AllPrice;
        this.tv = tv;
        runnablesList = new Runnable[list.size()];
        dBhelper = new DBhelper(context);
        db = dBhelper.getWritableDatabase();
    }



    @Override
    public AdapterForBasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_for_basket,null);
        AdapterForBasket.AdapterForBasketViewHolder viewHolder = new AdapterForBasket.AdapterForBasketViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterForBasketViewHolder holder, final int position) {
        holder.img.setImageResource(list.get(holder.getPosition()).getImg());
        holder.title.setText(list.get(holder.getPosition()).getTitle());
        holder.price.setText(list.get(holder.getPosition()).getPrice()*list.get(position).getCount()+"");
        holder.count.setText(list.get(holder.getPosition()).getCount()+"");

        final Handler handler = new Handler();
        holder.count_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.plus.setEnabled(true);
                holder.minus.setEnabled(true);
                holder.plus.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
                Runnable runnable = new Runnable() {
                    Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_fade_out);
                    @Override
                    public void run() {
                        Toast.makeText(context, "1 SECOND", Toast.LENGTH_SHORT).show();
                        holder.plus.setEnabled(false);
                        holder.minus.setEnabled(false);
                        holder.plus.setVisibility(View.GONE);
                        holder.minus.setVisibility(View.GONE);
                    }
                };
                handler.postDelayed(runnable,1000);
                runnablesList[holder.getPosition()] = runnable;

            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.plus.setEnabled(true);
                holder.minus.setEnabled(true);
                AllPrice +=list.get(holder.getPosition()).getPrice();
                tv.setText(AllPrice + "");

                holder.count.setText(Integer.parseInt(holder.count.getText().toString())+ 1 + "");
                ContentValues cv = new ContentValues();
                cv.put("count",holder.count.getText().toString());
                db.update("products",cv,"id=?",new String[]{""+list.get(holder.getPosition()).getId()});

                handler.removeCallbacks(runnablesList[holder.getPosition()]);
                Runnable runnable = new Runnable() {
                    Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_fade_out);
                    @Override
                    public void run() {
                        holder.plus.setEnabled(false);
                        holder.minus.setEnabled(false);
                        Toast.makeText(context, "1 SECOND", Toast.LENGTH_SHORT).show();
                        holder.plus.setVisibility(View.GONE);
                        holder.minus.setVisibility(View.GONE);
                        holder.price.setText(list.get(holder.getPosition()).getPrice() *
                                Integer.parseInt( holder.count.getText().toString()) + "");
                        list.get(holder.getPosition()).setCount(Integer.parseInt( holder.count.getText().toString()));

                    }
                };
                handler.postDelayed(runnable,1000);
                runnablesList[holder.getPosition()] = runnable;
            }
        });
        holder.minus.setTag(holder.getPosition());
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.plus.setEnabled(true);
                holder.minus.setEnabled(true);
                int current = Integer.parseInt(holder.count.getText().toString());
                if(current > 0){
                    holder.count.setText(current - 1 + "");
                    AllPrice -=list.get(holder.getPosition()).getPrice();
                    tv.setText(AllPrice + "");
                }
                ContentValues cv = new ContentValues();
                cv.put("count",holder.count.getText().toString());
                db.update("products",cv,"id=?",new String[]{""+list.get(holder.getPosition()).getId()});
                handler.removeCallbacks(runnablesList[holder.getPosition()]);
                if(current-1 > 0){
                    Runnable runnable = new Runnable() {
                        Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_fade_out);
                        @Override
                        public void run() {
                            holder.plus.setEnabled(false);
                            holder.minus.setEnabled(false);
                            Toast.makeText(context, "1 SECOND", Toast.LENGTH_SHORT).show();
                            holder.plus.setVisibility(View.GONE);
                            holder.minus.setVisibility(View.GONE);
                            holder.price.setText(list.get(holder.getPosition()).getPrice() *
                                    Integer.parseInt( holder.count.getText().toString()) + "");
                            list.get(holder.getPosition()).setCount(Integer.parseInt( holder.count.getText().toString()));

                        }
                    };
                    handler.postDelayed(runnable,1000);
                    runnablesList[holder.getPosition()] = runnable;
                }else{
                    view.setEnabled(false);
                    list.remove(holder.getPosition());
                    notifyItemRemoved(holder.getPosition());

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  AdapterForBasketViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title,count,price;
        LinearLayout minus,plus,count_layout;
        public AdapterForBasketViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.basket_img);
            title = (TextView) itemView.findViewById(R.id.basket_title);
            price = (TextView) itemView.findViewById(R.id.price_basket);
            count = (TextView) itemView.findViewById(R.id.count_basket);
            minus = (LinearLayout) itemView.findViewById(R.id.minus_basket);
            plus = (LinearLayout) itemView.findViewById(R.id.plus_basket);
            count_layout = (LinearLayout)itemView.findViewById(R.id.count_basket_layout);
        }
    }
}
