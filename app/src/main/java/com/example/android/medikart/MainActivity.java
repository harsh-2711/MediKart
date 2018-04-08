package com.example.android.medikart;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.medikart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;
import ir.mirrajabi.searchdialog.core.Searchable;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    String itemSelected;
    private static int RESULT_LOAD_IMAGE = 1;
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
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.android_action_bar_spinner_menu, menu);



        MenuItem item = menu.findItem(R.id.spinner);

        spinner = (Spinner) MenuItemCompat.getActionView(item);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,

                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = parent.getItemAtPosition(position).toString();
                if(itemSelected.equals("Contact Us")){
                    startActivity(new Intent(MainActivity.this,ContactUs.class));
                }
                if(itemSelected.equals("Terms of Use")){
                    Intent i=new Intent(MainActivity.this,WebViewActivity.class);
                    i.putExtra("Webpage",5);
                    startActivity(i);
                }
                if(itemSelected.equals("Upload Image")){
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                }
                // Showing selected spinner item

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return true;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            Intent i= new Intent(MainActivity.this,ImageActivity.class);
            i.putExtra("Image",selectedImage.toString());
            startActivity(i);
           /* String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));*/

        }


    }
}