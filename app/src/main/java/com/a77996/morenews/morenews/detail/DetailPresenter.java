package com.a77996.morenews.morenews.detail;

import android.content.Context;
import android.webkit.WebView;

import com.a77996.morenews.morenews.bean.BeanType;
import com.a77996.morenews.morenews.bean.DoubanMomentStory;
import com.a77996.morenews.morenews.bean.StringModelImpl;
import com.a77996.morenews.morenews.bean.ZhihuDailyStory;
import com.a77996.morenews.morenews.interfaze.onStringListener;
import com.a77996.morenews.morenews.util.Api;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/3/8.
 */
public class DetailPresenter implements DetailContract.Presenter {

    private Context context;
    private StringModelImpl stringModel;
    private DetailContract.View view;
    private Gson gson;
    ZhihuDailyStory zhihuDailyStory;
    public DetailPresenter(Context context,DetailContract.View view){
        this.context =context;
        stringModel = new StringModelImpl(context);
        this.view =view;
        this.view.setPresenter(this);
        gson =new Gson();
    }

    private BeanType type;
    private int id;
    private String title;
    private String coverUrl;

    public void setType(BeanType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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
        view.setTitle(title);
        view.showLoading();
        switch (type) {
            case TYPE_ZHIHU:
                stringModel.load(Api.ZHIHU_NEWS + id, new onStringListener() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        try {
                            zhihuDailyStory = gson.fromJson(result, ZhihuDailyStory.class);

                            view.showResult(zhihuDailyStory.getShare_url());

                        } catch (JsonSyntaxException e) {
                            view.showLoadingError();
                        }
                        view.stopLoading();
                    }
                    @Override
                    public void onError(VolleyError error) {
                     view.stopLoading();
                         view.showLoadingError();
                    }
                });
                break;
            case TYPE_GUOKR:
                stringModel.load(Api.GUOKR_ARTICLE_LINK_V1 + id, new onStringListener() {
                    @Override
                    public void onSuccess(String result) {
                       // convertGuokrContent(result);
                       // view.showResult(guokrStory);
                        view.showHtml(result);
                        Logger.d(result);
                        view.stopLoading();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        view.showLoadingError();
                        view.stopLoading();
                    }
                });
                break;
            case TYPE_DOUBAN:
                stringModel.load(Api.DOUBAN_ARTICLE_DETAIL + id, new onStringListener() {
                    @Override
                    public void onSuccess(String result) {
                        // convertGuokrContent(result);
                        // view.showResult(guokrStory);
                      DoubanMomentStory doubanMomentStory = gson.fromJson(result, DoubanMomentStory.class);
                        view.showResult(doubanMomentStory.getShort_url());
                      //  view.showHtml(result);
                        Logger.d(result);
                        Logger.d(doubanMomentStory.getShort_url());
                        view.stopLoading();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        view.showLoadingError();
                        view.stopLoading();
                    }
                });
                break;
        }

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
