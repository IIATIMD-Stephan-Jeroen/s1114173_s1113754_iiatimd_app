package com.example.inventory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.BagViewHolder> {

    private String[] names;
    private String[] descriptions;

    public BagAdapter(String[] names, String[] descriptions){
        this.names = names;
        this.descriptions = descriptions;
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
        BagViewHolder bagViewHolder = new BagViewHolder(v);
        return bagViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BagAdapter.BagViewHolder holder, int position) {
        holder.bagName.setText(names[position]);
        holder.bagDesc.setText(descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }


}
