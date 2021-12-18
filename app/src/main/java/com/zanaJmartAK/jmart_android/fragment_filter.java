package com.zanaJmartAK.jmart_android;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zanaJmartAK.jmart_android.model.Product;
import com.zanaJmartAK.jmart_android.request.RequestFactory;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class fragment_filter extends Fragment {

    private static final Gson gson = new Gson();
    public static int status = 0;
    public static ArrayList<Product> listFiltered = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_filter, container, false);
        EditText name = (EditText) inf.findViewById(R.id.nameInputFilter);
        EditText lowest = (EditText) inf.findViewById(R.id.lowestInputFilter);
        EditText highest = (EditText) inf.findViewById(R.id.highestInputFilter);
        CheckBox newCheck = (CheckBox) inf.findViewById(R.id.newFilter);
        CheckBox usedCheck = (CheckBox) inf.findViewById(R.id.usedFilter);
        Spinner category = (Spinner) inf.findViewById(R.id.categoryDrawFilter);
        Button apply = (Button) inf.findViewById(R.id.applyFilter);
        Button clear = (Button) inf.findViewById(R.id.clearFilter);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
                Response.Listener<String> listenerFiltered = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray object = new JSONArray(response);
                            if(object != null){
                                listFiltered = gson.fromJson(object.toString(),new TypeToken<ArrayList<Product>>(){}.getType());
                                System.out.println(listFiltered);
                                Toast.makeText(getActivity(),"Filtered",Toast.LENGTH_SHORT).show();
                                status = 1;
                            }else{
                                Toast.makeText(getActivity(),"No Data",Toast.LENGTH_SHORT).show();
                            }
                            getActivity().finish();
                            getActivity().overridePendingTransition(0,0);
                            getActivity().startActivity(getActivity().getIntent());
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                requestQueue.add(RequestFactory.getProduct(fragment_product.page,fragment_product.pageSize,LoginActivity.getLoggedAccount().id,name.getText().toString(),lowest.getText().toString(),highest.getText().toString(),category.getSelectedItem().toString(),String.valueOf(usedCheck.isChecked()),listenerFiltered,null));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = 0;
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        return inf;
    }
}