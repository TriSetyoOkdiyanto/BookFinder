package com.example.etrnty.bookfinder.adapter.pagination;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.holder.HolderBoxBestSelling;
import com.example.etrnty.bookfinder.model.ModelSectionData;

import java.util.List;

public class AdapterBooks extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_BESTSELLING = 0;
    public static final int TYPE_NEW = 1;

    private List<ModelSectionData> itemBooks;
    private Context context;
    private Activity activity;

    public AdapterBooks(List<ModelSectionData> itemBooks, Context context, Activity activity) {
        this.itemBooks = itemBooks;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_box_bestselling, parent, false);
        return new HolderBoxBestSelling(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HolderBoxBestSelling) holder).initLayout(itemBooks.get(position), context, activity);
    }

    @Override
    public int getItemCount() {
        return itemBooks == null ? 0 : itemBooks.size();
    }

}
