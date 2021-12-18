package com.zanaJmartAK.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.zanaJmartAK.jmart_android.LoginActivity;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

/**
 * Handle request when we want to create new product
 * @author Zana Niswah Awahita
 */

public class CreateProductRequest extends StringRequest {

    public static final String URL = "http://10.0.2.2:7593/product/create";
    public final Map<String,String> params;

    public CreateProductRequest
            (
                    String name,
                    String weight,
                    String conditionUsed,
                    String price,
                    String discount,
                    String category,
                    String shipmentPlans,
                    Response.Listener<String> listener,
                    @Nullable Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        Integer i = LoginActivity.getLoggedAccount().id;
        params.put("accountId", i.toString());
        params.put("name",name);
        params.put("weight",weight);
        params.put("conditionUsed",conditionUsed);
        params.put("price", price);
        params.put("discount", discount);
        params.put("category", category);
        params.put("shipmentPlans", shipmentPlans);
    }
    public Map<String,String> getParams(){return params;}
}