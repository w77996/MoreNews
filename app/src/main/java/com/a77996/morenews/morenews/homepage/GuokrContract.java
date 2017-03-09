package com.a77996.morenews.morenews.homepage;

import com.a77996.morenews.morenews.BasePresenter;
import com.a77996.morenews.morenews.BaseView;
import com.a77996.morenews.morenews.bean.GuokrHandpickNews;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/9.
 */
interface GuokrContract {
    interface  View extends BaseView<Presenter>{
        void showLoading();
        void stopLoading();
        void showError();
        void showResult(ArrayList<GuokrHandpickNews.result> list);

    }
    interface Presenter extends BasePresenter{
        void loadPosts();
        void refresh();
        void startReading(int positon);
    }
}
