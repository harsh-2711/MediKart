package com.example.android.medikart;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerActivity extends AppCompatActivity {
    Spinner spinner;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.android_action_bar_spinner_menu, menu);



        MenuItem item = menu.findItem(R.id.spinner);

        spinner = (Spinner) MenuItemCompat.getActionView(item);
        a=spinner.getSelectedItem().toString();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,

                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spinner.setAdapter(adapter);

        return true;

    }


}
