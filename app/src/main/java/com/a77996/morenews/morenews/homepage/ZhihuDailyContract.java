package com.a77996.morenews.morenews.homepage;

import com.a77996.morenews.morenews.BasePresenter;
import com.a77996.morenews.morenews.BaseView;
import com.a77996.morenews.morenews.bean.ZhihuDailyNews;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/8.
 */
public interface ZhihuDailyContract {
    interface View extends BaseView<Presenter>{
        void showError();
        void showLoading();
        void stopLoading();
        void showResult(ArrayList<ZhihuDailyNews.StoriesBean> list);
        void showPickDialog();
    }
    interface Presenter extends BasePresenter{
        void loadPosts(long date,boolean clearing);
        void refresh();
        void loadMore(long date);
        void startReading(int positon);
    }
}
