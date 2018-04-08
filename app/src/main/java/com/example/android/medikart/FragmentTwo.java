package com.example.android.medikart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    String[] generic_name;
    String[] generic_prize;
    int lengthOfArr;
    private ArrayAdapter<String> listAdapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            JSONArray arr_gen = new JSONArray(loadJSONFromAssetGeneric());
            //    ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            //    HashMap<String, String> m_li;
            generic_name=new String[arr_gen.length()];
            generic_prize=new String[arr_gen.length()];
            lengthOfArr=arr_gen.length();
            for(int i=0;i<arr_gen.length();i++){
                JSONObject j_obj=arr_gen.getJSONObject(i);
                generic_name[i] = j_obj.getString("Name");
                generic_prize[i] = j_obj.getString("MRP");
            }

        } catch (JSONException e) {

            e.printStackTrace();
            Toast.makeText(getActivity(),String.valueOf(e), Toast.LENGTH_SHORT).show();
        }
        View view=inflater.inflate(R.layout.fragment_two, container, false);
        ListView listView=(ListView)view.findViewById(R.id.listView1);

        // Create and populate a List of planet names.
    //    String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
    //            "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(generic_name) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.column_row, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add( "Ceres" );
       // listAdapter.add( "Pluto" );
        //listAdapter.add( "Haumea" );
        //listAdapter.add( "Makemake" );
        //listAdapter.add( "Eris" );

        // Set the ArrayAdapter as the ListView's adapter.
        listView.setAdapter( listAdapter );
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
}
