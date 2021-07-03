package com.example.inventory.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.inventory.activity.ItemDetailActivity;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class BagitemAdapter extends RecyclerView.Adapter<BagitemAdapter.BagitemViewHolder> {

    private List<Bagitem> items;
    private int bagId;
    public Context mContext;

    public void setItems(List<Bagitem> itemList){
        this.items = itemList;
        notifyDataSetChanged();
    }

    public BagitemAdapter(Context context, int bagId) {
        this.mContext = context;
        this.bagId = bagId;
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
            Intent intent = new Intent(mContext, ItemDetailActivity.class);
            intent.putExtra("itemId", itemId.getText());
            intent.putExtra("bag_id", String.valueOf(bagId));
            mContext.startActivity(intent);
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
        holder.itemId.setText(String.valueOf(dbItem.getId()));
        holder.itemAmount.setText(String.valueOf(items.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
