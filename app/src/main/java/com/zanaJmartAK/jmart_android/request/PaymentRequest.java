package com.zanaJmartAK.jmart_android.request;

import androidx.annotation.Nullable;

import com.zanaJmartAK.jmart_android.LoginActivity;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.zanaJmartAK.jmart_android.fragment_product;

import java.util.HashMap;
import java.util.Map;

/**
 * Handle request when we want to do the payment activity
 * @author Zana Niswah Awahita
 */

public class PaymentRequest extends StringRequest {
    public static final String URL = "http://10.0.2.2:8080/payment/create";
    public final Map<String,String> params;

    public PaymentRequest(String productCount, String shipmentAddress, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId",String.valueOf(LoginActivity.loggedAccount.id));
        params.put("productId",String.valueOf(fragment_product.productClicked.id));
        params.put("productCount",productCount);
        params.put("shipmentAddress",shipmentAddress);
        params.put("shipmentPlan",String.valueOf(fragment_product.productClicked.shipmentPlans));
    }

    public Map<String,String> getParams(){
        return params;
    }
}