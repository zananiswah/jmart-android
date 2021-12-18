package com.zanaJmartAK.jmart_android.request;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.zanaJmartAK.jmart_android.LoginActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Handle request when we want to do Top Up or adding Balance
 * @author Zana Niswah Awahita
 */


public class TopUpRequest extends StringRequest {
    private static final String URL =  "http://10.0.2.2:7593/account/" + LoginActivity.getLoggedAccount().id + "/topUp";
    private final Map<String , String> params;

    public TopUpRequest
            (
                    int id,
                    double balance,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);

        params = new HashMap<>();
        params.put("id", Integer.toString(id));
        params.put("balance", Double.toString(balance));
    }

    public Map<String , String> getParams() {
        return params;
    }
}
