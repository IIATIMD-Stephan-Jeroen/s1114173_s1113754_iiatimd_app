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

    public BagAdapter(Context context){
        this.context = context;
    }

    public void setBagList(List<Bag> bagList){
        this.bagList = bagList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BagAdapter.BagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.bag_card, parent, false);
        return new BagViewHolder(v);
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
