package com.a77996.morenews.morenews.homepage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.a77996.morenews.morenews.bean.BeanType;
import com.a77996.morenews.morenews.bean.StringModelImpl;
import com.a77996.morenews.morenews.bean.ZhihuDailyNews;
import com.a77996.morenews.morenews.detail.DetailActivity;
import com.a77996.morenews.morenews.interfaze.onStringListener;
import com.a77996.morenews.morenews.util.Api;
import com.a77996.morenews.morenews.util.DateFormatter;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */
public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {

    private Context context;
    private ZhihuDailyContract.View view;
    private StringModelImpl stringModel;
    DateFormatter formatter = new DateFormatter();
   private  Gson gson = new Gson();
    ArrayList<ZhihuDailyNews.StoriesBean> list = new ArrayList<>();

    public ZhihuDailyPresenter(Context context, ZhihuDailyContract.View view) {
        this.context =context;
        this.view =view;
        this.view.setPresenter(this);
        this.stringModel = new StringModelImpl(context);
    }

    @Override
    public void loadPosts(long date, final boolean clearing) {
        if(clearing){
            view.showLoading();
        }
        stringModel.load(Api.ZHIHU_HISTORY + formatter.ZhihuDailyDateFormat(date), new onStringListener() {
            @Override
            public void onSuccess(String result) {
                try {
                    ZhihuDailyNews zhihuDailyNews =gson.fromJson(result,ZhihuDailyNews.class);
                    if(clearing){
                        list.clear();
                    }
                    for(ZhihuDailyNews.StoriesBean storiesBean:zhihuDailyNews.getStories()){
                        list.add(storiesBean);
                    }
                    view.showResult(list);
                }catch (JsonSyntaxException e) {
                    view.showError();
                    Logger.e("ee",e.toString());
                }
                view.stopLoading();
            }

            @Override
            public void onError(VolleyError error) {
                view.stopLoading();
                view.showError();
            }
        });
    }

    @Override
    public void refresh() {
        loadPosts(Calendar.getInstance().getTimeInMillis(),true);
    }

    @Override
    public void loadMore(long date) {
        loadPosts(date,false);
    }

    @Override
    public void startReading(int position) {
        context.startActivity(new Intent(context, DetailActivity.class)
                .putExtra("type", BeanType.TYPE_ZHIHU)
                .putExtra("id", list.get(position).getId())
                .putExtra("title", list.get(position).getTitle())
                .putExtra("coverUrl", list.get(position).getImages().get(0)));
        Logger.d(list.get(position).toString());
    }

    @Override
    public void start() {

    }
}
