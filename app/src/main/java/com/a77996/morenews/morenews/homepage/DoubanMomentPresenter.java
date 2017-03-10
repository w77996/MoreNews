package com.a77996.morenews.morenews.homepage;

import android.content.Context;
import android.content.Intent;

import com.a77996.morenews.morenews.bean.BeanType;
import com.a77996.morenews.morenews.bean.DoubanMomentNews;
import com.a77996.morenews.morenews.bean.StringModelImpl;
import com.a77996.morenews.morenews.detail.DetailActivity;
import com.a77996.morenews.morenews.interfaze.onStringListener;
import com.a77996.morenews.morenews.util.Api;
import com.a77996.morenews.morenews.util.DateFormatter;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/10.
 */
public class DoubanMomentPresenter implements DoubanMomentContract.Presenter {
    private Context context;
    private StringModelImpl stringModel;
    DoubanMomentContract.View view;
    DateFormatter dateFormatter =new DateFormatter();
    Gson gson = new Gson();
    ArrayList<DoubanMomentNews.posts> list = new ArrayList<>();
    public DoubanMomentPresenter(Context context,DoubanMomentContract.View view){
        this.context =context;
        this.view = view;
        this.view.setPresenter(this);
        stringModel = new StringModelImpl(context);
    }



    @Override
    public void loadPosts(long date, boolean clearing) {
        view.showLoading();
        stringModel.load(Api.DOUBAN_MOMENT + dateFormatter.DoubanDateFormat(date), new onStringListener() {
            @Override
            public void onSuccess(String result) {
                DoubanMomentNews doubanMomentNews = gson.fromJson(result,DoubanMomentNews.class);
                for(DoubanMomentNews.posts p:doubanMomentNews.getPosts()){
                    list.add(p);
                }
                view.showResult(list);
            }

            @Override
            public void onError(VolleyError error) {
                view.stopLoading();
                view.showLoadingError();
            }
        });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void startReading(int position) {
        DoubanMomentNews.posts item = list.get(position);
        Intent intent = new Intent(context, DetailActivity.class);

        intent.putExtra("type", BeanType.TYPE_DOUBAN);
        intent.putExtra("id", item.getId());
        intent.putExtra("title", item.getTitle());
        if (item.getThumbs().size() == 0){
            intent.putExtra("coverUrl", "");
        } else {
            intent.putExtra("coverUrl", item.getThumbs().get(0).getMedium().getUrl());
        }
        context.startActivity(intent);
    }

    @Override
    public void loadMore(long date) {
        loadPosts(date, false);
    }

    @Override
    public void start() {

    }
}
