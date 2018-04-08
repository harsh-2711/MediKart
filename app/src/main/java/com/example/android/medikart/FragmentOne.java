package com.example.android.medikart;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    public FragmentOne() {
        // Required empty public constructor
    }

    Button add;
    String[] generic_name;
    String[] generic_prize;
    String[] generic_size;
    String[] name;
    String[] weight;
    String[] generic;
    String[] price;
    String[] quantity;
    String[] link;
    String[] med_type;
    String[] manufacturer;

    TextView branded_heading, generic_heading, branded_name1, generic_name1, branded_weight1, generic_weight1, branded_price1, generic_price1,
            branded_type1, branded_manufacturer1, saved1, profit1;

    int l;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_one, container, false);

        branded_heading = (TextView) view.findViewById(R.id.branded_heading);
        generic_heading = (TextView) view.findViewById(R.id.generic_heading);
        branded_name1 = (TextView) view.findViewById(R.id.branded_name);
        generic_name1 = (TextView) view.findViewById(R.id.generic_name);
        branded_weight1 = (TextView) view.findViewById(R.id.branded_weight);
        generic_weight1 = (TextView) view.findViewById(R.id.generic_weight);
        branded_price1 = (TextView) view.findViewById(R.id.branded_price);
        generic_price1 = (TextView) view.findViewById(R.id.generic_price);
        branded_type1 = (TextView) view.findViewById(R.id.branded_type);
        branded_manufacturer1 = (TextView) view.findViewById(R.id.branded_manufacturer);
        saved1 = (TextView) view.findViewById(R.id.saved);
        profit1 = (TextView) view.findViewById(R.id.profit);

        branded_heading.setText(" ");
        generic_heading.setText(" ");
        branded_name1.setText(" ");
        generic_name1.setText(" ");
        branded_weight1.setText(" ");
        generic_weight1.setText(" ");
        branded_price1.setText(" ");
        generic_price1.setText(" ");
        branded_type1.setText(" ");
        branded_manufacturer1.setText(" ");
        saved1.setText(" ");
        profit1.setText(" ");

        add = (Button) view.findViewById(R.id.addToCart);
        try {
            JSONArray arr_gen = new JSONArray(loadJSONFromAssetGeneric());
            //    ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            //    HashMap<String, String> m_li;
            generic_name=new String[arr_gen.length()];
            generic_prize=new String[arr_gen.length()];
            generic_size=new String[arr_gen.length()];
            price=new String[arr_gen.length()];
            l=arr_gen.length();
            for(int i=0;i<arr_gen.length();i++){
                JSONObject j_obj=arr_gen.getJSONObject(i);
                generic_name[i] = j_obj.getString("Name");
                generic_prize[i] = j_obj.getString("MRP");
                generic_size[i] = j_obj.getString("Unit Size");
            }

        } catch (JSONException e) {

            e.printStackTrace();
            Toast.makeText(getActivity(),String.valueOf(e), Toast.LENGTH_SHORT).show();
        }


        view.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new SimpleSearchDialogCompat<>(getActivity(), "Search Medicine here..", "What are u looking for..", null,
                        initData(), new SearchResultListener<SearchModel>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, SearchModel searchModel, int i) {
                        final String brand_name = searchModel.getTitle();

                        int index = 0;
                        String branded_price = "";
                        String branded_type = "";
                        String branded_manufacturer = "";
                        String branded_weight = "";
                        for(int j= 0; j < name.length; j++)
                        {
                            if(name[j].equals(brand_name))
                            {
                                index = j;
                                break;
                            }
                        }
                        double profit;
                        String gen = generic[index];
                        branded_price = price[index];
                        branded_type = med_type[index];
                        branded_manufacturer = manufacturer[index];
                        branded_weight = weight[index];
                        int temp = -1;
                        for(int j = 0; j < l; j++)
                        {
                            if(generic_name[j].toLowerCase().contains(gen.toLowerCase()))
                            {
                                temp = j;
                                break;
                            }
                        }
                        String gen_price = "";
                        String gen_weight = "";
                        if(temp == -1)
                            gen = gen;
                        else
                        {
                            gen_price = generic_prize[temp];
                            gen_weight = generic_size[temp];
                        }

                        //Final Print
                        if(temp == -1)
                        {
                            branded_heading.setText("Branded Name");
                            generic_heading.setText("Generic Name");
                            branded_name1.setText(brand_name);
                            generic_name1.setText(gen);
                            branded_weight1.setText(branded_weight);
                            branded_price1.setText("Rs. "+branded_price);
                            branded_type1.setText(branded_type);
                            branded_manufacturer1.setText(branded_manufacturer);
                            generic_weight1.setText(" ");
                            generic_price1.setText("N/A");
                            saved1.setText(" ");
                            profit1.setText(" ");
                        }
                        else
                        {
                            branded_heading.setText("Branded Name");
                            generic_heading.setText("Generic Name");
                            branded_name1.setText(brand_name);
                            generic_name1.setText(gen);
                            branded_weight1.setText(branded_weight);
                            generic_weight1.setText(gen_weight);
                            branded_price1.setText("Rs. "+branded_price);
                            generic_price1.setText("Rs. "+gen_price);
                            branded_type1.setText(branded_type);
                            branded_manufacturer1.setText(branded_manufacturer);
                            profit = Float.parseFloat(branded_price) - Float.parseFloat(gen_price);
                            profit = (double)Math.round(profit * 1000d) / 1000d;
                            if(profit < 0)
                            {
                                saved1.setText("You Lose : ");
                                saved1.setTextColor(Color.parseColor("#FF0000"));
                                profit1.setTextColor(Color.parseColor("#FF0000"));
                            }
                            else
                            {
                                saved1.setText("You Saved : ");
                                saved1.setTextColor(Color.parseColor("#00FF00"));
                                profit1.setTextColor(Color.parseColor("#00FF00"));
                            }
                            profit1.setText(String.valueOf("Rs. "+profit));
                        }
                        final int flag = temp;
                        final String price = branded_price;
                        final String generic_name = gen;
                        final String generic_price = gen_price;
                        add.setVisibility(View.VISIBLE);

                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FragmentTwo fragment = new FragmentTwo();
                                Bundle args = new Bundle();
                                if(flag == -1)
                                {
                                    args.putString("name",brand_name);
                                    args.putString("price",price);
                                }
                                else
                                {
                                    args.putString("name",generic_name);
                                    args.putString("price",generic_price);
                                }
                                fragment.setArguments(args);

                                getFragmentManager().beginTransaction().add(R.id.frag2, fragment).commit();
                            }
                        });

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
            Toast.makeText(getActivity(),String.valueOf(e), Toast.LENGTH_SHORT).show();
        }

        return view;
    }
    private String loadJSONFromAssetGeneric() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("Generic_med.json");
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

    private ArrayList<SearchModel> initData() {
        ArrayList<SearchModel> items = new ArrayList<>();
        for(int i = 0; i < name.length; i++)
        {
            items.add(new SearchModel(name[i]));
        }
        return items;
    }



}
