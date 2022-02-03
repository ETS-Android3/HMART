package com.huawei.grocery.adapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.grocery.ProductDetails;
import com.huawei.grocery.R;
import com.huawei.grocery.model.Cart;
import com.huawei.grocery.model.RecentlyViewed;

import java.io.Serializable;
import java.util.List;

public class RecentlyViewedAdapter extends RecyclerView.Adapter<RecentlyViewedAdapter.RecentlyViewedHolder> {

    Context context;
    List<RecentlyViewed> recentlyViewedList;
    List<Cart> cartList;
    Intent intent;

    public RecentlyViewedAdapter(Context context, List<RecentlyViewed> recentlyViewedList, List<Cart> cartList) {
        this.context = context;
        this.recentlyViewedList = recentlyViewedList;
        this.cartList = cartList;
        this.intent = ((Activity) context).getIntent();
    }

    @NonNull
    @Override
    public RecentlyViewedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_viewed_items,parent,false);
        return new RecentlyViewedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedHolder holder, final int position) {
        holder.name.setText(recentlyViewedList.get(position).getProduct().getName());
        holder.description.setText(recentlyViewedList.get(position).getProduct().getDescription());
        holder.price.setText("$ " + recentlyViewedList.get(position).getProduct().getPrice());
        holder.unit.setText(recentlyViewedList.get(position).getProduct().getUnit());
        holder.qty.setText("1");
        holder.imageView.setImageResource(recentlyViewedList.get(position).getProduct().getImageUrl());
        //holder.bg.setBackgroundResource(recentlyViewedList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context, ProductDetails.class);
                i.putExtra("item", recentlyViewedList.get(position).getProduct());
                i.putExtra("cart", (Serializable)cartList);
                i.putExtra("quantity",1);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recentlyViewedList.size();
    }

    public static class RecentlyViewedHolder extends RecyclerView.ViewHolder{
        TextView name,description,price,qty,unit;
        ImageView imageView;
        ConstraintLayout bg;
        public RecentlyViewedHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.lblProducrName);
            description= itemView.findViewById(R.id.lblDescr);
            price = itemView.findViewById(R.id.lblPrice);
            qty = itemView.findViewById(R.id.lblQty);
            unit = itemView.findViewById(R.id.lblUnit);
            imageView =itemView.findViewById(R.id.imageView5);
            //bg =  itemView.findViewById(R.id.recently_items);
        }
    }


}
