package com.a77996.morenews.morenews.bean;

import android.content.Context;

import com.a77996.morenews.morenews.app.VolleySingleton;
import com.a77996.morenews.morenews.interfaze.onStringListener;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Administrator on 2017/3/8.
 */
public class StringModelImpl {
    private Context context;
    public StringModelImpl(Context context){
        this.context =context;
    }
    public void load(String url, final onStringListener listener){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        VolleySingleton.getVolleySingleton(context).addRequestQueue(stringRequest);
    }
}
