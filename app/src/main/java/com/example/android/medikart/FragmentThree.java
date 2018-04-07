package com.example.android.medikart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    String[] name;
    String[] weight;
    String[] generic;
    String[] price;
    String[] quantity;
    String[] link;
    String[] med_type;
    String[] manufacturer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


     //   String[][] array = gson.fromJson(json,String[][].class);
     //   Toast.makeText(getContext(), array[0][0], Toast.LENGTH_SHORT).show();
        View view=inflater.inflate(R.layout.fragment_three, container, false);

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
            Toast.makeText(getActivity(),String.valueOf(e), Toast.LENGTH_SHORT).show();
        }
        return view;
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("medIndia.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Toast.makeText(getActivity(), "LOL2", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
