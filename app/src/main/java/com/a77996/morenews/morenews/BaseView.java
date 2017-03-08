package com.a77996.morenews.morenews;

import android.view.View;

/**
 * Created by Administrator on 2017/3/8.
 */
public interface BaseView <T>{
    void setPresenter(T presenter);
    void initViews(View view);
}
