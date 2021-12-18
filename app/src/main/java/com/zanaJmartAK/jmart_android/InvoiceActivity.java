package com.zanaJmartAK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zanaJmartAK.jmart_android.model.Payment;
import com.zanaJmartAK.jmart_android.model.Product;
import com.zanaJmartAK.jmart_android.request.RequestFactory;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class InvoiceActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    public static ArrayList<Payment> paymentList = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    static int pageSize = 20;
    static Integer page = 0;
    static Product paymentClicked = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice_activity);

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray object = new JSONArray(response);
                    if (object != null) {
                        paymentList = gson.fromJson(object.toString(), new TypeToken<ArrayList<Payment>>() {
                        }.getType());
                        System.out.println(paymentList);
                        convertPayment();
                        System.out.println(products);
                        ArrayAdapter<Product> listViewAdapter = new ArrayAdapter<Product>(
                                InvoiceActivity.this, android.R.layout.simple_list_item_1, products
                        );

                        ListView lv = (ListView) findViewById(R.id.storeHistory);

                        lv.setAdapter(listViewAdapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(InvoiceActivity.this);
        requestQueue.add(RequestFactory.getPage("payment", page, pageSize, listener, null));
    }

    private void convertPayment() {
        int i = 0;
        //products.clear();
        for (Payment each : paymentList) {
            if(each.buyerId == LoginActivity.loggedAccount.id){
                Response.Listener<String> listenerConvert = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objectConvert = new JSONObject(response);
                            if (objectConvert != null) {
                                Product temp = gson.fromJson(objectConvert.toString(),Product.class);
                                products.add(temp);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(InvoiceActivity.this);
                requestQueue.add(RequestFactory.getById("product", each.productId, listenerConvert, null));
            }
        }
    }
}