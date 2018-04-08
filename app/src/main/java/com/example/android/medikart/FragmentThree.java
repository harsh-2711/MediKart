package com.example.android.medikart;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {

    public FragmentThree() {
        // Required empty public constructor
    }

    CardView cv1,cv2,cv3,cv4,cv5,cv6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {


        //   String[][] array = gson.fromJson(json,String[][].class);
        //   Toast.makeText(getContext(), array[0][0], Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        cv1=(CardView)view.findViewById(R.id.card_view);
        cv2=(CardView)view.findViewById(R.id.card_view1);
        cv3=(CardView)view.findViewById(R.id.card_view2);
        cv4=(CardView)view.findViewById(R.id.card_view3);
        cv5=(CardView)view.findViewById(R.id.card_view5);
        cv6=(CardView)view.findViewById(R.id.card_view6);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),WebViewActivity.class);
                i.putExtra("Webpage",1);
                startActivity(i);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),WebViewActivity.class);
                i.putExtra("Webpage",2);
                startActivity(i);
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),WebViewActivity.class);
                i.putExtra("Webpage",3);
                startActivity(i);
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),WebViewActivity.class);
                i.putExtra("Webpage",4);
                startActivity(i);
            }
        });
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),WebViewActivity.class);
                i.putExtra("Webpage",1);
                startActivity(i);
            }
        });
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),WebViewActivity.class);
                i.putExtra("Webpage",2);
                startActivity(i);
            }
        });

        return view;
    }

}
