package com.zanaJmartAK.jmart_android.request;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Handle request when we want to register new account(s)
 * @author Zana Niswah Awahita
 */

public class RegisterRequest extends StringRequest {
    private static final String URL =  "http://10.0.2.2:7593/account/register";
    private final Map<String , String> params;

    public RegisterRequest(String name, String email, String password,
                           Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    public Map<String , String> getParams() {
        return params;
    }
}

