package com.example.inventory.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
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
import com.example.inventory.activity.AddBagActivity;
import com.example.inventory.activity.ItemDetailActivity;
import com.example.inventory.activity.ItemOverviewActivity;
import com.example.inventory.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> items;
    public Context mContext;

    public ItemAdapter(Context context) {
        this.mContext = context;
    }

    public void setItems(List<Item> itemList){
        this.items = itemList;
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView itemName;
        public TextView itemId;
        public Item item;
        public Context mContext;

        public ItemViewHolder(View v){
            super(v);
            v.setOnClickListener((View.OnClickListener) this);
            itemName = v.findViewById(R.id.itemName);
            itemId = v.findViewById(R.id.itemId);
            mContext = v.getContext();
        }

        public void setItem(Item item) {
            this.item = item;
        }

        @Override
        public void onClick( View v) {
            Intent intent = new Intent(mContext, ItemDetailActivity.class);
            Log.d("test", (String) itemId.getText());
            intent.putExtra("itemId", itemId.getText());
            mContext.startActivity(intent);
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
        holder.itemId.setText(String.valueOf(items.get(position).getId()));

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
