package com.example.android.medikart;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.medikart.R;

import java.util.ArrayList;
import java.util.List;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;
import ir.mirrajabi.searchdialog.core.Searchable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add Fragments to adapter one by one

        adapter.addFragment(new FragmentOne(), "DASHBOARD");
        adapter.addFragment(new FragmentTwo(), "MEDCART");
        adapter.addFragment(new FragmentThree(),"NEWSFEED");
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SimpleSearchDialogCompat<>(MainActivity.this, "Search Medicine here..", "What are u looking for..", null,
                        initData(), new SearchResultListener<SearchModel>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, SearchModel searchModel, int i) {
                        Toast.makeText(MainActivity.this,"Selected",Toast.LENGTH_LONG).show();
                        baseSearchDialogCompat.dismiss();
                    }
                }).show();
            }
        });

    }

    private ArrayList<SearchModel> initData() {
        ArrayList<SearchModel> items = new ArrayList<>();
        items.add(new SearchModel("Metacin"));
        items.add(new SearchModel("Crocin"));
        items.add(new SearchModel("Relispray"));
        items.add(new SearchModel("Vicks"));
        items.add(new SearchModel("Strepsils"));

        return items;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    protected void onStart(){
        super.onStart();

        if(!isNetworkAvailable())
        {
            Intent i = new Intent(MainActivity.this, NoInternetActivity.class);
            startActivity(i);
            finish();
        }
    }

    protected void onResume(){
        super.onResume();

        if(!isNetworkAvailable())
        {
            Intent i = new Intent(MainActivity.this, NoInternetActivity.class);
            startActivity(i);
            finish();
        }
    }

    protected void onPause(){
        super.onPause();
    }

    protected void onStop(){
        super.onStop();
    }

    protected void onRestart(){
        super.onRestart();

        if(!isNetworkAvailable())
        {
            Intent i = new Intent(MainActivity.this, NoInternetActivity.class);
            startActivity(i);
            finish();
        }
    }

    protected void onDestroy(){
        super.onDestroy();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}