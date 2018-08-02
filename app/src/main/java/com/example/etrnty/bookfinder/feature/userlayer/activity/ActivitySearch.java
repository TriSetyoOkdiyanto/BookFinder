package com.example.etrnty.bookfinder.feature.userlayer.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.adapter.collection.AdapterSearch;
import com.example.etrnty.bookfinder.adapter.pagination.AdapterBooks;
import com.example.etrnty.bookfinder.controller.ControllerRest;
import com.example.etrnty.bookfinder.custom.CustomPaddingDecorationItem;
import com.example.etrnty.bookfinder.model.ModelSectionData;
import com.example.etrnty.bookfinder.model.books.ModelBooks;
import com.example.etrnty.bookfinder.model.books.ModelItem;
import com.example.etrnty.bookfinder.util.UtilInput;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySearch extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, TextView.OnEditorActionListener {

    private EditText mSearchKeywordET;
    private Menu    mMainMenu;
    private RecyclerView mSearchResultListRV;
    private SwipeRefreshLayout mRefreshSearch;
    private AdapterSearch mAdapterSearch;
    private List<Object> itemList;
    private CustomPaddingDecorationItem padding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearchKeywordET = findViewById(R.id.search_keyword_edittext);
        mSearchResultListRV = findViewById(R.id.list_recyclerSearch);
        mRefreshSearch = findViewById(R.id.list_swiperefresh);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRefreshSearch.setOnRefreshListener(this);
        mSearchResultListRV.setHasFixedSize(true);
        mSearchResultListRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        padding = new CustomPaddingDecorationItem(this);
        mSearchResultListRV.addItemDecoration(padding);

        itemList = new ArrayList<>();

        initSearchBox();

    }

    private void initSearchBox(){
        mSearchKeywordET.clearFocus();
        mSearchKeywordET.setOnEditorActionListener(this);
        mSearchKeywordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(mMainMenu != null) {
                    mMainMenu.findItem(R.id.action_clear).setVisible((editable.length() > 0));
                }
            }
        });

        mSearchKeywordET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mSearchKeywordET.setCursorVisible(true);
                return false;
            }
        });
    }

    public void searchByKeyword(String keyword) {
        if(keyword == null){
            Toast.makeText(this, "Masukkan Input", Toast.LENGTH_SHORT).show();
            return;
        }
        UtilInput.hideKeyboard(this, mSearchKeywordET);
        mSearchKeywordET.clearFocus();
        mSearchKeywordET.requestFocusFromTouch();
        mSearchKeywordET.setText(keyword);
        mSearchKeywordET.setCursorVisible(false);
        resultLoaded(keyword);
    }

    public void resultLoaded(String mKeyword) {
        Call<ModelBooks> call = ControllerRest.ServiceApi(getApplication()).getRequestBook(mKeyword);
        call.enqueue(new Callback<ModelBooks>() {
            @Override
            public void onResponse(Call<ModelBooks> call, Response<ModelBooks> response) {
                if (response.body() != null && response.isSuccessful()) {
                    itemList.clear();
                    itemList.addAll(response.body().getItems());
                    mAdapterSearch = new AdapterSearch(itemList, getApplicationContext());
                    mSearchResultListRV.setAdapter(mAdapterSearch);
                    showLoadingProcess();
                    Log.e("LoadBooks", "onData: " + itemList);
                } else {
                    Toast.makeText(getApplication(), "Database Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelBooks> call, Throwable t) {
                Log.e("LoadBooks", "onFailure: " + t.getMessage());
            }
        });
    }

    public void showLoadingProcess() {
        mSearchResultListRV.post(new Runnable() {
            public void run() {
                mAdapterSearch.notifyItemChanged(mAdapterSearch.getItemCount() - 1);
                mAdapterSearch.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onRefresh() {
        itemList.clear();
        mRefreshSearch.setRefreshing(false);
        mAdapterSearch.notifyDataSetChanged();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchByKeyword(textView.getText().toString());
        }
        return false;
    }

    private void onClearSearchKeyword() {
        mSearchKeywordET.getText().clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        mMainMenu = menu;
        getMenuInflater().inflate(R.menu.menu_clear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_clear:
                onClearSearchKeyword();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
