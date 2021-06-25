package com.example.inventory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.Bag;
import com.example.inventory.R;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.BagViewHolder> {

    private Bag[] bags;

    public BagAdapter(Bag[] bags){
        this.bags = bags;
    }

    public static class BagViewHolder extends RecyclerView.ViewHolder{

        public TextView bagName;
        public TextView bagDesc;

        public BagViewHolder(View v){
            super(v);
            bagName = v.findViewById(R.id.bagName);
            bagDesc = v.findViewById(R.id.bagDescription);
        }
    }

    @NonNull
    @Override
    public BagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.bag_card, parent, false);
        return new BagViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BagAdapter.BagViewHolder holder, int position) {
        holder.bagName.setText(bags[position].getName());
        holder.bagDesc.setText(bags[position].getDescription());
    }

    @Override
    public int getItemCount() {
        return bags.length;
    }


}
