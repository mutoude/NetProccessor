package com.rt.cxl.netprocessor.processor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by CXL on 2018/7/16.
 */

public class VolleyNetProcessor implements NetHttpProcessor {

    RequestQueue mRequstQue;

    public VolleyNetProcessor(Context context) {
        mRequstQue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, Object> parames, final ProcessorHttpCallback callback) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        });
        mRequstQue.add(request);
    }

    @Override
    public void post(String url, Map<String, Object> parames, final ProcessorHttpCallback callback) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        });
    }
}
