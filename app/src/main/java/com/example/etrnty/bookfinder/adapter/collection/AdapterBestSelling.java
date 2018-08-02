package com.example.etrnty.bookfinder.adapter.collection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.holder.HolderViewBestSelling;
import com.example.etrnty.bookfinder.model.books.ModelItem;

import java.util.List;

public class AdapterBestSelling extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ModelItem> itemBestSelling;
    private Context         context;

    public AdapterBestSelling(List<ModelItem> itemBestSelling, Context context) {
        this.itemBestSelling = itemBestSelling;
        this.context         = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bestselling, parent, false);
        return new HolderViewBestSelling(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HolderViewBestSelling)holder).initLayout(itemBestSelling.get(position),context);
    }

    @Override
    public int getItemCount() {
        return itemBestSelling == null ? 0: itemBestSelling.size();
    }
}
