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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;
import ir.mirrajabi.searchdialog.core.Searchable;

public class MainActivity extends AppCompatActivity {


    String[] name;
    String[] weight;
    String[] generic;
    String[] price;
    String[] quantity;
    String[] link;
    String[] med_type;
    String[] manufacturer;

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

        //Database

        try {
            JSONArray arr = new JSONArray(loadJSONFromAsset());
            //    ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            //    HashMap<String, String> m_li;
            name=new String[arr.length()];
            weight=new String[arr.length()];
            generic=new String[arr.length()];
            price=new String[arr.length()];
            quantity=new String[arr.length()];
            link=new String[arr.length()];
            med_type=new String[arr.length()];
            manufacturer=new String[arr.length()];
            for(int i=0;i<arr.length();i++){
                JSONObject j_obj=arr.getJSONObject(i);
                name[i] = j_obj.getString("name");
                weight[i] = j_obj.getString("weight");
                generic[i] = j_obj.getString("generic");
                price[i] = j_obj.getString("price");
                price[i]=String.valueOf(10*Integer.parseInt(price[i])+5);
                quantity[i] = j_obj.getString("quantitity");
                link[i] = j_obj.getString("med_type");
                med_type[i] = j_obj.getString("manufacturer");

            }

        } catch (JSONException e) {

            e.printStackTrace();
            Toast.makeText(getApplicationContext(),String.valueOf(e), Toast.LENGTH_SHORT).show();
        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("medIndia.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(), "LOL2", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private ArrayList<SearchModel> initData() {
        ArrayList<SearchModel> items = new ArrayList<>();
        for(int i = 0; i < name.length; i++)
        {
            items.add(new SearchModel(name[i]));
        }
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