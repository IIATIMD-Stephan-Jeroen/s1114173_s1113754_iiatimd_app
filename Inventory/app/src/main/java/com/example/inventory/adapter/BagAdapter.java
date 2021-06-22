package com.example.inventory.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.BagViewHolder> {

    public static class BagViewHolder extends RecyclerView.ViewHolder{

        public TextView bagName;
        public TextView bagDesc;

        public BagViewHolder(View v){
            super(v);
            bagName = v.findViewById(R.id.bagName);
            bagDesc = v.findViewById(R.id.bagDescription);
        }
    }
}
