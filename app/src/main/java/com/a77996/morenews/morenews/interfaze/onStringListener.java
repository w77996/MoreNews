package com.a77996.morenews.morenews.interfaze;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2017/3/8.
 */
public interface onStringListener {
    void onSuccess(String result);
    void onError(VolleyError error);
}
