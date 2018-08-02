package com.example.etrnty.bookfinder.holder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.adapter.collection.AdapterBestSelling;
import com.example.etrnty.bookfinder.custom.CustomPaddingDecorationItem;
import com.example.etrnty.bookfinder.model.ModelSectionData;
import com.example.etrnty.bookfinder.model.books.ModelBooks;

public class HolderBoxBestSelling extends RecyclerView.ViewHolder{
    TextView textViewType;
    AdapterBestSelling mAdapter;
    RecyclerView recyclerView;
    private CustomPaddingDecorationItem padding;


    public HolderBoxBestSelling(View itemView) {
        super(itemView);

        textViewType = (TextView) itemView.findViewById(R.id.textBooks);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerBest);
        recyclerView.setHasFixedSize(true);
    }

    public void initLayout(ModelSectionData model, Context context, Activity activity) {
        textViewType.setText(model.getHeaderTitle());
        padding = new CustomPaddingDecorationItem(activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(padding);
        mAdapter = new AdapterBestSelling(model.getAllItemsInSection(), context);
        recyclerView.setAdapter(mAdapter);
    }
}
