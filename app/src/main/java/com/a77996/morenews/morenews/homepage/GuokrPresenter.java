package com.a77996.morenews.morenews.homepage;

import android.content.Context;
import android.content.Intent;

import com.a77996.morenews.morenews.bean.BeanType;
import com.a77996.morenews.morenews.bean.GuokrHandpickNews;
import com.a77996.morenews.morenews.bean.StringModelImpl;
import com.a77996.morenews.morenews.detail.DetailActivity;
import com.a77996.morenews.morenews.interfaze.onStringListener;
import com.a77996.morenews.morenews.util.Api;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/9.
 */
public class GuokrPresenter implements GuokrContract.Presenter {
    private final Context context;
    StringModelImpl stringModel;
    Gson gson =new Gson();
    private ArrayList<GuokrHandpickNews.result> list = new ArrayList<GuokrHandpickNews.result>();
    GuokrContract.View view;
    public GuokrPresenter(Context context,GuokrContract.View view){
        this.context =context;
        this.view =view;
        this.view.setPresenter(this);
        this.stringModel = new StringModelImpl(context);
    }


    @Override
    public void loadPosts() {
        view.showLoading();
        stringModel.load(Api.GUOKR_ARTICLES, new onStringListener() {
            @Override
            public void onSuccess(String result) {
                list.clear();
                GuokrHandpickNews result1 = gson.fromJson(result,GuokrHandpickNews.class);
                for(GuokrHandpickNews.result result2 :result1.getResult()){
                    list.add(result2);
                }
                view.stopLoading();
                view.showResult(list);

            }

            @Override
            public void onError(VolleyError error) {
                view.showError();
                view.stopLoading();
            }
        });
    }

    @Override
    public void refresh() {
        loadPosts();
    }

    @Override
    public void startReading(int positon) {
        GuokrHandpickNews.result item = list.get(positon);
        context.startActivity(new Intent(context, DetailActivity.class)
                .putExtra("type", BeanType.TYPE_GUOKR)
                .putExtra("id", item.getId())
                .putExtra("coverUrl", item.getHeadline_img())
                .putExtra("title", item.getTitle())
        );
    }

    @Override
    public void start() {

    }
}
