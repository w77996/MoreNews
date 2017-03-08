package com.a77996.morenews.morenews.detail;

import android.content.Context;
import android.webkit.WebView;

import com.a77996.morenews.morenews.bean.StringModelImpl;

/**
 * Created by Administrator on 2017/3/8.
 */
public class DetailPresenter implements DetailContract.Presenter {

    private StringModelImpl stringModel;
    private DetailContract.View view;
    public DetailPresenter(Context context,DetailContract.View view){
        stringModel = new StringModelImpl(context);
        this.view =view;
    }
    @Override
    public void openInBrowser() {

    }

    @Override
    public void shareAsText() {

    }

    @Override
    public void openUrl(WebView webView, String url) {

    }

    @Override
    public void requestData() {

    }

    @Override
    public void copyText() {

    }

    @Override
    public void copyLink() {

    }

    @Override
    public void start() {

    }
}
