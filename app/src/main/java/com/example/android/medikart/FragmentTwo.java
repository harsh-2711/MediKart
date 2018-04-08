package com.example.android.medikart;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    public FragmentTwo() {
        // Required empty public constructor
    }

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,canc,check;

    ArrayList<String> listElements = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    static int counter = 0;
    Bundle b;
    String name, price;
    TextView[] str;
    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        tv1 = (TextView) view.findViewById(R.id.t1);
        tv2 = (TextView) view.findViewById(R.id.t2);
        tv3 = (TextView) view.findViewById(R.id.t3);
        tv4 = (TextView) view.findViewById(R.id.t4);
        tv5 = (TextView) view.findViewById(R.id.t5);
        tv6 = (TextView) view.findViewById(R.id.t6);
        tv7 = (TextView) view.findViewById(R.id.t7);
        tv8 = (TextView) view.findViewById(R.id.t8);
        tv9 = (TextView) view.findViewById(R.id.t9);
        tv10 = (TextView) view.findViewById(R.id.t10);
        tv11= (TextView) view.findViewById(R.id.t11);
        tv12= (TextView) view.findViewById(R.id.t12);
        tv13= (TextView) view.findViewById(R.id.t13);
        tv14= (TextView) view.findViewById(R.id.t14);
        check = (TextView) view.findViewById(R.id.checkout);
        canc = (TextView) view.findViewById(R.id.cancel);

        str = new TextView[14];
        if(counter <= 0)
        {
            tv1.setText(" ");
        }
        if(counter <= 1)
        tv2.setText(" ");
        if(counter <= 2)
        tv3.setText(" ");
        if(counter <= 3)
        tv4.setText(" ");
        if(counter <= 4)
        tv5.setText(" ");
        if(counter <= 5)
        tv6.setText(" ");
        if(counter <= 6)
        tv7.setText(" ");
        if(counter <= 7)
        tv8.setText(" ");
        if(counter <= 8)
        tv9.setText(" ");
        if(counter <= 9)
        tv10.setText(" ");
        if(counter <= 10)
        tv11.setText(" ");
        if(counter <= 11)
        tv12.setText(" ");
        if(counter <= 12)
        tv13.setText(" ");
        if(counter <= 13)
        tv14.setText(" ");

        if(counter <= 0)
        {
            canc.setText(" ");
            check.setText(" ");
        }


        str[0] = tv1;
        str[1] = tv2;
        str[2] = tv3;
        str[3] = tv4;
        str[4] = tv5;
        str[4] = tv5;
        str[6] = tv7;
        str[7] = tv8;
        str[8] = tv9;
        str[9] = tv10;
        str[10] = tv11;
        str[11] = tv12;
        str[12] = tv13;
        str[13] = tv14;

        b = this.getArguments();

        if(b != null)
        {
            name = b.getString("name");
            price = b.getString("price");
        }
        else
        {
            name = " ";
            price = " ";
        }

        str[counter].setText(name);
        str[counter+1].setText(price);
        check.setText("CHECKOUT");
        canc.setText("CANCEL");
        counter+=2;

        return view;
    }

}

