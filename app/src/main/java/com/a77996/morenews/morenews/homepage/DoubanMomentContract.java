package com.a77996.morenews.morenews.homepage;

import com.a77996.morenews.morenews.BasePresenter;
import com.a77996.morenews.morenews.BaseView;
import com.a77996.morenews.morenews.bean.DoubanMomentNews;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/10.
 */
interface DoubanMomentContract {
    interface View extends BaseView<Presenter>{
        void showLoading();
        void stopLoading();
        void showLoadingError();
        void showResult(ArrayList<DoubanMomentNews.posts> list);
        void showPickDialog();
    }
    interface Presenter extends BasePresenter{
        void loadPosts(long date,boolean clearing);
        void refresh();
        void startReading(int position);
        void loadMore(long date);


    }
}
