package com.example.inventory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.AppDatabase;
import com.example.data.Bagitem;
import com.example.data.Item;
import com.example.inventory.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class BagitemAdapter extends RecyclerView.Adapter<BagitemAdapter.BagitemViewHolder> {

    private List<Bagitem> items;
    public Context mContext;

    public void setItems(List<Bagitem> itemList){
        this.items = itemList;
        notifyDataSetChanged();
    }

    public BagitemAdapter(Context context) {
        this.mContext = context;
    }

    public class BagitemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView itemName;
        public TextView itemId;
        public TextView itemAmount;
        public Context mContext;
        public BagitemViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            itemName = v.findViewById(R.id.itemName);
            itemId = v.findViewById(R.id.itemId);
            itemAmount = v.findViewById(R.id.itemAmount);
            mContext = v.getContext();
        }

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @NotNull
    @Override
    public BagitemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new BagitemAdapter.BagitemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BagitemViewHolder holder, int position) {
        AppDatabase db = AppDatabase.getInstance(mContext);
        Item dbItem = db.itemDAO().getItemById(items.get(position).getItemId());
        holder.itemName.setText(dbItem.getName());
        holder.itemId.setText(dbItem.getId());
        holder.itemAmount.setText(items.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
