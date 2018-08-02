package com.example.etrnty.bookfinder.adapter.collection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.holder.HolderSearch;
import com.example.etrnty.bookfinder.holder.HolderViewBestSelling;
import com.example.etrnty.bookfinder.model.books.ModelItem;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> itemNew;
    private Context context;

    public AdapterSearch(List<Object> itemNew, Context context) {
        this.itemNew = itemNew;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search, parent, false);
        return new HolderSearch(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HolderSearch)holder).initLayout((ModelItem) itemNew.get(position),context);
    }

    @Override
    public int getItemCount() {
        return itemNew == null ? 0: itemNew.size();
    }
}
