package com.example.inventory.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.Bag;
import com.example.data.Item;
import com.example.inventory.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> items;
    private Context context;
    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<Item> itemList){
        this.items = itemList;
        notifyDataSetChanged();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public ItemViewHolder(View v){
            super(v);
            itemName = v.findViewById(R.id.itemName);
        }
    }

    @NonNull
    @NotNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ItemAdapter.ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemAdapter.ItemViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filterList(List<Item> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }
}
