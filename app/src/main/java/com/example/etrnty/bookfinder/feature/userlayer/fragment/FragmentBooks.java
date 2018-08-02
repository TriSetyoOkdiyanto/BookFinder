package com.example.etrnty.bookfinder.feature.userlayer.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.adapter.pagination.AdapterBooks;
import com.example.etrnty.bookfinder.controller.ControllerRest;
import com.example.etrnty.bookfinder.custom.CustomPaddingDecorationItem;
import com.example.etrnty.bookfinder.model.ModelSectionData;
import com.example.etrnty.bookfinder.model.books.ModelBooks;
import com.example.etrnty.bookfinder.model.books.ModelItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBooks extends Fragment {

    private final static String kategori_best         = "android";
    private final static String kategori_programming  = "programming";

    private AdapterBooks mAdapter;
    private RecyclerView mRecyclerView;
    private List<ModelSectionData> listData;

    private CustomPaddingDecorationItem padding;
    private SwipeRefreshLayout doRefresh;

    public FragmentBooks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerBooks);

        listData  = new ArrayList<>();

        new BookAsyncTask("Android Books").execute();
        new BookAsyncTask("Programming Books").execute();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        doRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        doRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh.setRefreshing(true);
                listData.clear();
                new BookAsyncTask("Android Books").execute();
                new BookAsyncTask("Programming Books").execute();
            }

        });
    }

    public class BookAsyncTask extends AsyncTask<Void,Void,Void> {

        List<ModelItem> itemList;
        String headerTitle;

        public BookAsyncTask(String headerTitle) {
            super();
            this.headerTitle = headerTitle;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (headerTitle == "Android Books") {
                Call<ModelBooks> call = ControllerRest.ServiceApi(getActivity().getApplication()).getRequestBook(kategori_best);
                call.enqueue(new Callback<ModelBooks>() {
                    @Override
                    public void onResponse(Call<ModelBooks> call, Response<ModelBooks> response) {

                        if (response.body() != null && response.isSuccessful()) {
                            itemList = response.body().getItems();

                            ModelSectionData dm = new ModelSectionData();
                            dm.setHeaderTitle(headerTitle);
                            dm.setAllItemsInSection(itemList);

                            listData.add(dm);

                            mAdapter = new AdapterBooks(listData, getContext(), getActivity());
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            padding = new CustomPaddingDecorationItem(getActivity());
                            mRecyclerView.addItemDecoration(padding);

                            mRecyclerView.setAdapter(mAdapter);


                            Log.e("LoadBooks", "onData: " + itemList);
                        } else {
                            Toast.makeText(getContext(), "Database Error", Toast.LENGTH_SHORT).show();
                        }
                        doRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<ModelBooks> call, Throwable t) {
                        Log.e("LoadNews", "onFailure: " + t.getMessage());
                        doRefresh.setRefreshing(false);
                    }
                });
            } else if (headerTitle == "Programming Books") {
                Call<ModelBooks> call = ControllerRest.ServiceApi(getActivity().getApplication()).getRequestBook(kategori_programming);
                call.enqueue(new Callback<ModelBooks>() {
                    @Override
                    public void onResponse(Call<ModelBooks> call, Response<ModelBooks> response) {

                        if (response.body() != null && response.isSuccessful()) {
                            itemList = response.body().getItems();

                            ModelSectionData dm = new ModelSectionData();
                            dm.setHeaderTitle(headerTitle);
                            dm.setAllItemsInSection(itemList);

                            listData.add(dm);

                            mAdapter = new AdapterBooks(listData, getContext(), getActivity());
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            padding = new CustomPaddingDecorationItem(getActivity());
                            mRecyclerView.addItemDecoration(padding);

                            mRecyclerView.setAdapter(mAdapter);


                            Log.e("LoadBooks", "onData: " + itemList);
                        } else {
                            Toast.makeText(getContext(), "Database Error", Toast.LENGTH_SHORT).show();
                        }
                        doRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<ModelBooks> call, Throwable t) {
                        Log.e("LoadNews", "onFailure: " + t.getMessage());
                        doRefresh.setRefreshing(false);
                    }
                });
            }
            return null;
        }

    }
}
