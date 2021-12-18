package com.zanaJmartAK.jmart_android.request;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Handle any request that use id or page
 * @author Zana Niswah Awahita
 */

public class RequestFactory {


    private static final String URL_FORMAT_ID = "http://10.0.2.2:7593/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:7593/%s/page?page=%s&pageSize=%s";
    private static final String URL_FORMAT_PRODUCT = "http://10.0.2.2:7593/product/getFiltered?page=%s&pageSize=%s&search=%s&minPrice=%s&maxPrice=%s&category=%s&conditionUsed=%s";

    public static StringRequest getById
            (
                    String parentURL,
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_ID, parentURL, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getPage
            (
                    String parentURL,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURL, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getProduct
            (
                    int i, int page,
                    int pageSize,
                    String search,
                    String minPrice,
                    String maxPrice,
                    String category,
                    String conditionUsed,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PRODUCT,page,pageSize,search,minPrice,maxPrice,category,conditionUsed);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
}
