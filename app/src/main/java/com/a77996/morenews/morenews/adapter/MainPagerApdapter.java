package com.a77996.morenews.morenews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.a77996.morenews.morenews.homepage.DoubanMomentFragment;
import com.a77996.morenews.morenews.homepage.ZhihuDailyFragment;

/**
 * Created by Administrator on 2017/3/7.
 */
public class MainPagerApdapter extends FragmentPagerAdapter{
    private  DoubanMomentFragment doubanMomentFragment;
    private  ZhihuDailyFragment zhihuDailyFragment;
    private Context context;
    private String[] titles;
    public MainPagerApdapter(FragmentManager fm, Context context, ZhihuDailyFragment zhihuDailyFragment, DoubanMomentFragment doubanMomentFragment) {
        super(fm);
        this.context = context;
        titles = new String[]{"知乎日报","豆瓣一刻"};

        this.zhihuDailyFragment = zhihuDailyFragment;
        this.doubanMomentFragment = doubanMomentFragment;
        //this.guokrFragment = guokrFragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return zhihuDailyFragment;
        } else if (position == 2){
            return doubanMomentFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}