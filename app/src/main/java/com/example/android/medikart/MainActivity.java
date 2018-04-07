package com.example.android.medikart;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.medikart.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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