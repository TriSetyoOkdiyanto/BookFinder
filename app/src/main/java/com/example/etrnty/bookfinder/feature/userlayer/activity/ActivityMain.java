package com.example.etrnty.bookfinder.feature.userlayer.activity;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.adapter.pagination.AdapterPaginationPager;
import com.example.etrnty.bookfinder.feature.userlayer.fragment.FragmentAbout;
import com.example.etrnty.bookfinder.feature.userlayer.fragment.FragmentBooks;

public class ActivityMain extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tab_main);
        tabLayout.setupWithViewPager(viewPager);
        btnSearch = findViewById(R.id.find);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAction();
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterPaginationPager adapter = new AdapterPaginationPager(getSupportFragmentManager());
        adapter.addFrag(new FragmentBooks(), "Books");
        adapter.addFrag(new FragmentAbout(), "About");
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void searchAction() {
        Intent intentSearch = new Intent(ActivityMain.this, ActivitySearch.class);
        startActivity(intentSearch);
    }
}
