package com.example.inventory.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.Bag;
import com.example.inventory.R;

import java.util.List;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.BagViewHolder> {

    private Context context;
    private List<Bag> bagList;

    private OnBagListener mOnBagListener;

    public BagAdapter(Context context, OnBagListener onBagListener){
        this.context = context;
        this.mOnBagListener = onBagListener;
    }

    public void setBagList(List<Bag> bagList){
        this.bagList = bagList;
        notifyDataSetChanged();
    }

    public Bag GetBagAt(int position){
        return bagList.get(position);
    }

    @NonNull
    @Override
    public BagAdapter.BagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.bag_card, parent, false);
        return new BagViewHolder(v, mOnBagListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BagAdapter.BagViewHolder holder, int position) {
        holder.bagName.setText(bagList.get(position).getName());
        holder.bagDesc.setText(bagList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        //make sure the app does not crash when no bags are present in the database
        try {
            return this.bagList.size();
        } catch(Exception e){
            Log.e("getItemCountError",e.toString());
            return 0;
        }
    }

    public static class BagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView bagName;
        public TextView bagDesc;

        OnBagListener onBagListener;

        public BagViewHolder(View v, OnBagListener onBagListener){
            super(v);
            bagName = v.findViewById(R.id.bagName);
            bagDesc = v.findViewById(R.id.bagDescription);
            this.onBagListener = onBagListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBagListener.onBagClick(getAdapterPosition());
        }
    }

    public interface OnBagListener{
        void onBagClick(int position);
    }


}
